package com.sixbynine.card.object;

import android.os.Parcel;
import android.os.Parcelable;

import com.sixbynine.card.model.SocialNetwork;

/**
 * Created by steviekideckel on 10/26/14.
 */
public class Contact implements Parcelable{

    private int id; //Database id
    private int contactId;
    private String name;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        int[] ints = new int[2];
        ints[0] = id;
        ints[1] = contactId;
        dest.writeIntArray(ints);

        String[] strings = new String[1];
        strings[0] = name;
        dest.writeStringArray(strings);

        dest.writeParcelable(socialNetworkMap, flags);

    }

    public static final Creator<Contact> CREATOR = new Creator<Contact>(){

        @Override
        public Contact createFromParcel(Parcel source) {
            Contact contact = new Contact();
            int[] ints = new int[2];
            source.readIntArray(ints);
            contact.id = ints[0];
            contact.contactId = ints[1];

            String[] strings = new String[1];
            source.readStringArray(strings);
            contact.name = strings[0];

            contact.socialNetworkMap = source.readParcelable(SocialNetworkMap.class.getClassLoader());

            return contact;
        }



        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };
}
