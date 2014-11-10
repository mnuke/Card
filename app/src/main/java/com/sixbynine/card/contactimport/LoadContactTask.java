package com.sixbynine.card.contactimport;

import android.database.Cursor;
import android.os.AsyncTask;
import android.provider.ContactsContract;

import com.sixbynine.card.CardApplication;
import com.sixbynine.card.object.Contact;

import java.util.ArrayList;

/**
 * Created by steviekideckel on 11/9/14.
 */
public class LoadContactTask extends AsyncTask<Void, Void, ArrayList<Contact>>{

    private Callback mCallback;

    public LoadContactTask(Callback callback){
        mCallback = callback;
    }

    public interface Callback{
        public void onError();
        public void onContactsLoaded(ArrayList<Contact> contacts);
    }

    @Override
    protected ArrayList<Contact> doInBackground(Void... params) {
        Cursor phones = CardApplication.getInstance().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        ArrayList<Contact> list = new ArrayList<Contact>(phones.getCount());

        int nameIndex = phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME_PRIMARY);
        int phoneIndex = phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
        while (phones.moveToNext())
        {
            String name=phones.getString(nameIndex);
            String phoneNumber = phones.getString(phoneIndex);


        }
        phones.close();
        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<Contact> result) {
        super.onPostExecute(result);
        if(result == null){
            mCallback.onError();
        }else{
            mCallback.onContactsLoaded(result);
        }
    }
}
