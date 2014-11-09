package com.sixbynine.card.manager;

import android.os.AsyncTask;

import com.sixbynine.card.object.Contact;
import com.sixbynine.card.persistent.ContactDataSource;

/**
 * Created by steviekideckel on 11/6/14.
 */
public class DatabaseManager extends Manager{

    private static DatabaseManager instance;

    public static DatabaseManager getInstance(){
        if(instance == null){
            synchronized (Manager.class){
                if(instance == null){
                    instance = new DatabaseManager();
                }
            }
        }
        return instance;
    }

    public void saveContact(final Contact contact){
        new AsyncTask<Void, Void, Long>(){
            @Override
            protected Long doInBackground(Void... params) {
                ContactDataSource dataSource = new ContactDataSource();
                return dataSource.saveContact(contact);
            }

            @Override
            protected void onPostExecute(Long aVoid) {
                super.onPostExecute(aVoid);
                if(aVoid == -1){
                    publish(UpdateEvent.CONTACT_ERROR_SAVING, contact);
                }else{
                    publish(UpdateEvent.CONTACT_SAVED);
                }
            }
        }.execute();
    }

}
