package com.sixbynine.card.ui.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.sixbynine.card.R;
import com.sixbynine.card.model.LogoSize;
import com.sixbynine.card.model.SocialNetwork;

/**
 * Created by steviekideckel on 11/9/14.
 */
public class SocialNetworkPickerDialog extends DialogFragment{

    private GridView mGridView;
    private SocialNetworkAdapter mAdapter;
    private SocialNetworkPickerDialogListener mListener;
    private SocialNetwork mSelectedNetwork;

    public interface SocialNetworkPickerDialogListener{
        public void onCancel();
        public void onSocialNetworkPicked(SocialNetwork network);
    }

    public static SocialNetworkPickerDialog newInstance(SocialNetworkPickerDialogListener listener){
        SocialNetworkPickerDialog frag = new SocialNetworkPickerDialog();
        frag.setOnPickListener(listener);
        return frag;
    }

    public SocialNetworkPickerDialog(){

    }

    protected void setOnPickListener(SocialNetworkPickerDialogListener l){
        mListener = l;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_social_network_pick, null);
        mGridView = (GridView) view.findViewById(R.id.grid_view);
        mAdapter = new SocialNetworkAdapter(getActivity());
        mGridView.setAdapter(mAdapter);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mSelectedNetwork = SocialNetwork.values()[position];
            }
        });


        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.select_a_network)
                .setView(view)
                .setPositiveButton(R.string.choose, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (mSelectedNetwork == null) {
                            Toast.makeText(getActivity(), R.string.please_select_network, Toast.LENGTH_SHORT).show();
                        } else {
                            mListener.onSocialNetworkPicked(mSelectedNetwork);
                        }
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mListener.onCancel();
                    }
                }).create();

    }

    private static class SocialNetworkAdapter extends BaseAdapter{
        private Context mContext;
        private int mImageViewWidth;
        private int mImageViewHeight;
        private int mPadding;

        public SocialNetworkAdapter(Context c) {
            this.mContext = c;
            mImageViewWidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 46, c.getResources().getDisplayMetrics());
            mImageViewHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 46, c.getResources().getDisplayMetrics());
            mPadding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, c.getResources().getDisplayMetrics());

        }

        @Override
        public int getCount() {
            return SocialNetwork.values().length;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            if(convertView == null){
                imageView = new ImageView(mContext);
                imageView.setLayoutParams(new GridView.LayoutParams(mImageViewWidth, mImageViewHeight));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setPadding(mPadding,mPadding,mPadding,mPadding);
            }else{
                imageView = (ImageView) convertView;
            }

            imageView.setImageResource(SocialNetwork.values()[position].getLogo(LogoSize.MEDIUM));
            return imageView;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }
    }
}
