package com.sixbynine.card.persistent;

import com.sixbynine.card.CardApplication;
import com.sixbynine.card.object.Contact;

/**
 * Created by steviekideckel on 10/26/14.
 */
public class ContactDataSource {
    private ContactDbHelper mHelper;

    public ContactDataSource(){
        mHelper = new ContactDbHelper(CardApplication.getInstance().getApplicationContext());
    }

    public void saveContact(Contact contact){

    }



}
