package com.sixbynine.card.home;

import android.os.Bundle;
import android.widget.Toast;

import com.sixbynine.card.R;
import com.sixbynine.card.activity.BaseCardActivity;
import com.sixbynine.card.manager.DatabaseManager;
import com.sixbynine.card.manager.UpdateEvent;
import com.sixbynine.card.manager.UpdateListener;
import com.sixbynine.card.object.Contact;
import com.sixbynine.card.util.Logger;

/**
 * Created by steviekideckel on 10/26/14.
 */
public class MainActivity extends BaseCardActivity implements UpdateListener, ContactsListFragment.Callback, NewContactFragment.Callback{

    private ContactsListFragment mContactsFragment;
    private NewContactFragment mNewContactFragment;

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
    protected void onResume() {
        super.onResume();
        DatabaseManager.getInstance().subscribe(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        DatabaseManager.getInstance().unSubscribe(this);
    }

    @Override
    public void onActionButtonClicked() {
        if(mNewContactFragment == null){
            mNewContactFragment = NewContactFragment.newInstance();
        }else if(mNewContactFragment.isAdded()){
            return;
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.top_frame, mNewContactFragment).commit();
    }

    @Override
    public void onBackPressed() {
        if(mNewContactFragment.isAdded()){
            getSupportFragmentManager().beginTransaction().remove(mNewContactFragment).commit();
        }else{
            super.onBackPressed();
        }
    }

    @Override
    public void onContactSaved(Contact contact) {
        DatabaseManager.getInstance().saveContact(contact);
        if(mNewContactFragment != null && mNewContactFragment.isAdded()) {
            getSupportFragmentManager().beginTransaction().remove(mNewContactFragment).commit();
        }
    }

    @Override
    public void update(UpdateEvent e, Object... data) {
        switch(e){
            case CONTACT_SAVED:
                mContactsFragment.refreshContactList();
                break;
            case CONTACT_ERROR_SAVING:
                Toast.makeText(this, R.string.error_saving_contact, Toast.LENGTH_SHORT).show();
                Logger.e("Error saving contact " +data[0]);
                break;

        }
    }
}
