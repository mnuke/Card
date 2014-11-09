package com.sixbynine.card.persistent;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sixbynine.card.CardApplication;
import com.sixbynine.card.object.Contact;
import com.sixbynine.card.object.SocialNetworkMap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by steviekideckel on 10/26/14.
 */
public class ContactDataSource {
    private ContactDbHelper mHelper;

    private static final String[] CONTACT_PROJECTION = {
            ContactContract.Contact._ID,
            ContactContract.Contact.COLUMN_NAME_CONTACT_ID,
            ContactContract.Contact.COLUMN_NAME_CONTACT_NAME,
            ContactContract.Contact.COLUMN_NAME_TAG,
            ContactContract.Contact.COLUMN_NAME_CONTACT_PHOTO,
            ContactContract.Contact.COLUMN_NAME_CONTACT_SOCIAL_NETWORKS};



    public ContactDataSource(){
        mHelper = new ContactDbHelper(CardApplication.getInstance().getApplicationContext());
    }

    public long saveContact(Contact contact){
        SQLiteDatabase db = mHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ContactContract.Contact.COLUMN_NAME_CONTACT_ID, contact.getContactId());
        values.put(ContactContract.Contact.COLUMN_NAME_CONTACT_NAME, contact.getName());
        values.put(ContactContract.Contact.COLUMN_NAME_TAG, contact.getTag());
        values.put(ContactContract.Contact.COLUMN_NAME_CONTACT_PHOTO, contact.getPhotoUrl());
        values.put(ContactContract.Contact.COLUMN_NAME_CONTACT_SOCIAL_NETWORKS, contact.getSocialNetworkMap().toJsonArrayString());

        return db.insert(
                ContactContract.Contact.TABLE_NAME,
                null,
                values);
    }

    public Contact getContactById(int id){
        SQLiteDatabase db = mHelper.getReadableDatabase();
        Cursor c = db.query(
                ContactContract.Contact.TABLE_NAME,
                CONTACT_PROJECTION,
                ContactContract.Contact._ID + "=?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null
        );
        return cursorToContact(c);
    }

    public List<Contact> getAllContacts(){
        SQLiteDatabase db = mHelper.getReadableDatabase();
        Cursor cursor = db.query(
                ContactContract.Contact.TABLE_NAME,
                CONTACT_PROJECTION,
                null,
                null,
                null,
                null,
                null
        );
        int idIndex = cursor.getColumnIndexOrThrow(ContactContract.Contact._ID);
        int contactIdIndex = cursor.getColumnIndexOrThrow(ContactContract.Contact.COLUMN_NAME_CONTACT_ID);
        int nameIndex = cursor.getColumnIndexOrThrow(ContactContract.Contact.COLUMN_NAME_CONTACT_NAME);
        int tagIndex = cursor.getColumnIndexOrThrow(ContactContract.Contact.COLUMN_NAME_TAG);
        int photoIndex = cursor.getColumnIndexOrThrow(ContactContract.Contact.COLUMN_NAME_CONTACT_PHOTO);
        int networksIndex = cursor.getColumnIndexOrThrow(ContactContract.Contact.COLUMN_NAME_CONTACT_SOCIAL_NETWORKS);

        List<Contact> contacts = new ArrayList<Contact>(cursor.getCount());

        while(cursor.moveToNext()){
            long itemId = cursor.getLong(idIndex);
            long itemContactId = cursor.getLong(contactIdIndex);
            String itemName = cursor.getString(nameIndex);
            String itemTag = cursor.getString(tagIndex);
            String itemPhoto = cursor.getString(photoIndex);
            String itemNetworks = cursor.getString(networksIndex);

            Contact contact = new Contact();
            contact.setId(itemId);
            contact.setContactId(itemContactId);
            contact.setName(itemName);
            contact.setTag(itemTag);
            contact.setPhotoUrl(itemPhoto);
            contact.setSocialNetworkMap(SocialNetworkMap.fromJsonArray(itemNetworks));
            contacts.add(contact);
        }
        return contacts;
    }

    public static Contact cursorToContact(Cursor cursor){
        if(cursor.moveToNext()){
            long itemId = cursor.getLong(
                    cursor.getColumnIndexOrThrow(ContactContract.Contact._ID)
            );
            long itemContactId = cursor.getLong(
                    cursor.getColumnIndexOrThrow(ContactContract.Contact.COLUMN_NAME_CONTACT_ID)
            );
            String itemName = cursor.getString(
                    cursor.getColumnIndexOrThrow(ContactContract.Contact.COLUMN_NAME_CONTACT_NAME)
            );
            String itemTag = cursor.getString(
                    cursor.getColumnIndexOrThrow(ContactContract.Contact.COLUMN_NAME_TAG)
            );
            String itemPhoto = cursor.getString(
                    cursor.getColumnIndexOrThrow(ContactContract.Contact.COLUMN_NAME_CONTACT_PHOTO)
            );
            String itemNetworks = cursor.getString(
                    cursor.getColumnIndexOrThrow(ContactContract.Contact.COLUMN_NAME_CONTACT_SOCIAL_NETWORKS)
            );
            Contact contact = new Contact();
            contact.setId(itemId);
            contact.setContactId(itemContactId);
            contact.setName(itemName);
            contact.setTag(itemTag);
            contact.setPhotoUrl(itemPhoto);
            contact.setSocialNetworkMap(SocialNetworkMap.fromJsonArray(itemNetworks));
            return contact;
        }
        return null;
    }

    public Cursor queryAllContacts(){
        SQLiteDatabase db = mHelper.getReadableDatabase();
        Cursor cursor = db.query(
                ContactContract.Contact.TABLE_NAME,
                CONTACT_PROJECTION,
                null,
                null,
                null,
                null,
                null
        );
        return cursor;
    }



}
