package com.sixbynine.card.home;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pkmmte.view.CircularImageView;
import com.sixbynine.card.R;
import com.sixbynine.card.object.Contact;

/**
 * Created by steviekideckel on 10/26/14.
 */
public class ContactsCursorAdapter extends CursorAdapter {

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

    public ContactsCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_contact, viewGroup, false);
        view.setTag(new ViewHolder(view));
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        Contact contact = Contact.fromCursor(cursor);
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        viewHolder.mHeader.setText(contact.getName());
        viewHolder.mSubHeader.setText(contact.getTag());
    }



}
