package com.sixbynine.card.persistent;

import android.provider.BaseColumns;

/**
 * Created by steviekideckel on 10/26/14.
 */
public final class ContactContract {

    public ContactContract(){}

    public static abstract class Contact implements BaseColumns{
        public static final String TABLE_NAME="contacts";
        public static final String COLUMN_NAME_CONTACT_ID="contact_id";
        public static final String COLUMN_NAME_CONTACT_NAME="contact_name";
        public static final String COLUMN_NAME_CONTACT_PHOTO="contact_photo";
        public static final String COLUMN_NAME_CONTACT_SOCIAL_NETWORKS="contact_social";
    }

}
