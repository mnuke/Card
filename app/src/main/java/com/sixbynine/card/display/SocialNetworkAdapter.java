package com.sixbynine.card.display;

import android.content.Context;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.sixbynine.card.R;
import com.sixbynine.card.model.SocialNetwork;
import com.sixbynine.card.object.SocialNetworkMap;
import com.sixbynine.card.ui.view.SocialNetworkDisplayView;

import java.util.ArrayList;

/**
 * Created by steviekideckel on 11/9/14.
 */
public class SocialNetworkAdapter extends ArrayAdapter<Pair<SocialNetwork, String>>{

    private Context mContext;
    private ArrayList<Pair<SocialNetwork, String>> mData;

    public static class ViewHolder{
        public SocialNetworkDisplayView mDisplayView;
    }

    public SocialNetworkAdapter(Context context, SocialNetworkMap map){
        this(context, map == null? null : map.toPairArrayList());
    }

    public SocialNetworkAdapter(Context context, ArrayList<Pair<SocialNetwork, String>> list){
        super(context, R.layout.item_network_display, list);
        mData = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if(convertView == null){
            view = LayoutInflater.from(mContext).inflate(R.layout.item_network_display, null);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.mDisplayView = (SocialNetworkDisplayView) view.findViewById(R.id.display_view);
            view.setTag(viewHolder);
        }else{
            view = convertView;
        }
        Pair<SocialNetwork, String> pair = mData.get(position);

        ViewHolder viewHolder = (ViewHolder) view.getTag();
        viewHolder.mDisplayView.setNetwork(pair.first);
        viewHolder.mDisplayView.setText(pair.second);


        return view;
    }

    @Override
    public int getCount() {
        if(mData == null){
            return 0;
        }else{
            return mData.size();
        }
    }
}
