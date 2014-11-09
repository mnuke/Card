package com.sixbynine.card.object;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.sixbynine.card.model.SocialNetwork;
import com.sixbynine.card.persistent.ContactContract;

/**
 * Created by steviekideckel on 10/26/14.
 */
public class Contact implements Parcelable{

    private long id; //Database id
    private long contactId; //server id
    private String name;
    private String tag;
    private String photoUrl;
    private SocialNetworkMap socialNetworkMap;

    public Contact(){
        socialNetworkMap = new SocialNetworkMap();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getContactId() {
        return contactId;
    }

    public void setContactId(long contactId) {
        this.contactId = contactId;
    }

    public void addSocialNetwork(SocialNetwork network, String value){
        socialNetworkMap.put(network, value);
    }

    public SocialNetworkMap getSocialNetworkMap(){
        return socialNetworkMap;
    }

    public void removeSocialNetwork(SocialNetwork network){
        socialNetworkMap.remove(network);
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public void setSocialNetworkMap(SocialNetworkMap socialNetworkMap) {
        this.socialNetworkMap = socialNetworkMap;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        long[] longs = new long[2];
        longs[0] = id;
        longs[1] = contactId;
        dest.writeLongArray(longs);

        String[] strings = new String[3];
        strings[0] = name;
        strings[1] = photoUrl;
        strings[2] = tag;
        dest.writeStringArray(strings);

        dest.writeParcelable(socialNetworkMap, flags);

    }

    public static final Creator<Contact> CREATOR = new Creator<Contact>(){

        @Override
        public Contact createFromParcel(Parcel source) {
            Contact contact = new Contact();
            long[] longs = new long[2];
            source.readLongArray(longs);
            contact.id = longs[0];
            contact.contactId = longs[1];

            String[] strings = new String[3];
            source.readStringArray(strings);
            contact.name = strings[0];
            contact.photoUrl = strings[1];
            contact.tag = strings[2];

            contact.socialNetworkMap = source.readParcelable(SocialNetworkMap.class.getClassLoader());

            return contact;
        }



        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };


    private static int sIdIndex = -1;
    private static int sContactIdIndex = -1;
    private static int sNameIndex = -1;
    private static int sTagIndex = -1;
    private static int sPhotoIndex = -1;
    private static int sSocialIndex = -1;

    public static Contact fromCursor(Cursor cursor){
        if(cursor == null){
            return null;
        }else{
            if(sIdIndex == -1){
                sIdIndex = cursor.getColumnIndexOrThrow(ContactContract.Contact._ID);
                sContactIdIndex = cursor.getColumnIndexOrThrow(ContactContract.Contact.COLUMN_NAME_CONTACT_ID);
                sNameIndex = cursor.getColumnIndexOrThrow(ContactContract.Contact.COLUMN_NAME_CONTACT_NAME);
                sTagIndex = cursor.getColumnIndexOrThrow(ContactContract.Contact.COLUMN_NAME_TAG);
                sPhotoIndex = cursor.getColumnIndexOrThrow(ContactContract.Contact.COLUMN_NAME_CONTACT_PHOTO);
                sSocialIndex = cursor.getColumnIndexOrThrow(ContactContract.Contact.COLUMN_NAME_CONTACT_SOCIAL_NETWORKS);
            }
            Contact contact = new Contact();
            contact.setId(cursor.getLong(sIdIndex));
            contact.setContactId(cursor.getLong(sContactIdIndex));
            contact.setName(cursor.getString(sNameIndex));
            contact.setTag(cursor.getString(sTagIndex));
            contact.setPhotoUrl(cursor.getString(sPhotoIndex));
            contact.setSocialNetworkMap(SocialNetworkMap.fromJsonArray(cursor.getString(sSocialIndex)));
            return contact;
        }
    }
}
