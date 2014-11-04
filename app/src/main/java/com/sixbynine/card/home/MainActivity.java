package com.sixbynine.card.home;

import android.os.Bundle;

import com.sixbynine.card.R;
import com.sixbynine.card.activity.BaseCardActivity;
import com.sixbynine.card.object.Contact;

import java.util.ArrayList;

/**
 * Created by steviekideckel on 10/26/14.
 */
public class MainActivity extends BaseCardActivity{

    private ContactsListFragment mContactsFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toolbar_frame);

        mContactsFragment = ContactsListFragment.newInstance(new ArrayList<Contact>());
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, mContactsFragment).commit();

    }


}
