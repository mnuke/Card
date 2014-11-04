package com.sixbynine.card.home;

import android.os.Bundle;

import com.sixbynine.card.R;
import com.sixbynine.card.activity.BaseCardActivity;
import com.sixbynine.card.object.Contact;

import java.util.ArrayList;

/**
 * Created by steviekideckel on 10/26/14.
 */
public class MainActivity extends BaseCardActivity implements ContactsListFragment.Callback{

    private ContactsListFragment mContactsFragment;
    private NewContactFragment mNewContactFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContactsFragment = ContactsListFragment.newInstance(new ArrayList<Contact>());
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.bottom_frame, mContactsFragment)
                .commit();

    }

    @Override
    public void onActionButtonClicked() {
        if(mNewContactFragment == null){
            mNewContactFragment = NewContactFragment.newInstance();
        }else if(mNewContactFragment.isAdded()){
            return;
        }
        getSupportFragmentManager().beginTransaction().
                add(R.id.top_frame, mNewContactFragment)
                .setCustomAnimations(R.anim.expand_in_from_bottom_right, R.anim.shrink_out_to_bottom_right, R.anim.expand_in_from_bottom_right, R.anim.shrink_out_to_bottom_right)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onBackPressed() {
        if(mNewContactFragment.isAdded()){
            getSupportFragmentManager().beginTransaction().remove(mNewContactFragment).commit();
        }else{
            super.onBackPressed();
        }
    }
}
