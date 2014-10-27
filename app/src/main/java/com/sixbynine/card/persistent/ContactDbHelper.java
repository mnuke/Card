package com.sixbynine.card.persistent;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by steviekideckel on 10/26/14.
 */
public class ContactDbHelper extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME="CardContact.db";

    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + ContactContract.Contact.TABLE_NAME + " (" +
                    ContactContract.Contact._ID + " INTEGER PRIMARY KEY" + COMMA_SEP +
                    ContactContract.Contact.COLUMN_NAME_CONTACT_ID + INTEGER_TYPE + COMMA_SEP +
                    ContactContract.Contact.COLUMN_NAME_CONTACT_NAME + TEXT_TYPE + COMMA_SEP +
                    ContactContract.Contact.COLUMN_NAME_TAG + TEXT_TYPE + COMMA_SEP +
                    ContactContract.Contact.COLUMN_NAME_CONTACT_PHOTO + TEXT_TYPE + COMMA_SEP +
                    ContactContract.Contact.COLUMN_NAME_CONTACT_SOCIAL_NETWORKS + TEXT_TYPE +

            " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + ContactContract.Contact.TABLE_NAME;

    public ContactDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
