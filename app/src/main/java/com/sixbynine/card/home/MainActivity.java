package com.sixbynine.card.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

import com.sixbynine.card.R;
import com.sixbynine.card.activity.BaseCardActivity;
import com.sixbynine.card.display.ContactDisplayActivity;
import com.sixbynine.card.newcontact.NewContactActivity;
import com.sixbynine.card.object.Contact;

/**
 * Created by steviekideckel on 10/26/14.
 */
public class MainActivity extends BaseCardActivity implements ContactsListFragment.Callback{

    private ContactsListFragment mContactsFragment;
    private static final int NEW_CONTACT = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContactsFragment = ContactsListFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.bottom_frame, mContactsFragment)
                .commit();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    public void onActionButtonClicked() {
        Intent intent = new Intent(this, NewContactActivity.class);
        startActivityForResult(intent, NEW_CONTACT);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == NEW_CONTACT && resultCode == RESULT_OK){
            mContactsFragment.refreshContactList();
        }else{
            super.onActivityResult(requestCode, resultCode, data);
        }

    }

    @Override
    public void onContactClicked(Contact contact) {
        Intent intent = new Intent(this, ContactDisplayActivity.class);
        intent.putExtra("contact", contact);
        startActivity(intent);
    }
}
