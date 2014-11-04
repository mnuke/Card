package com.sixbynine.card.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.pkmmte.view.CircularImageView;
import com.sixbynine.card.R;
import com.sixbynine.card.object.Contact;

import java.util.ArrayList;

/**
 * Created by steviekideckel on 10/26/14.
 */
public class ContactsArrayAdapter extends ArrayAdapter<Contact> {

    private static class ViewHolder{
        public CircularImageView mCircularImageView;
        public TextView mHeader;
        public TextView mSubHeader;
        public ViewHolder(View view){
            mCircularImageView = (CircularImageView) view.findViewById(R.id.image_view);
            mHeader = (TextView) view.findViewById(R.id.header);
            mSubHeader = (TextView) view.findViewById(R.id.subHeader);
        }
    }

    private ArrayList<Contact> mContacts;

    public ContactsArrayAdapter(Context context, ArrayList<Contact> contacts) {
        super(context, R.layout.item_contact, contacts);
        mContacts = contacts;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact, null);
            convertView.setTag(new ViewHolder(convertView));
        }

        Contact contact = mContacts.get(position);
        ViewHolder viewHolder = (ViewHolder) convertView.getTag();
        viewHolder.mHeader.setText(contact.getName());
        viewHolder.mSubHeader.setText(contact.getTag());

        return convertView;
    }

    @Override
    public int getCount() {
        if(mContacts == null){
            return 0;
        }else{
            return mContacts.size();
        }
    }

}
