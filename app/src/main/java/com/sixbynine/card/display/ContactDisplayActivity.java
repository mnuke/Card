package com.sixbynine.card.display;

import android.os.Bundle;

import com.sixbynine.card.R;
import com.sixbynine.card.activity.BaseCardActivity;
import com.sixbynine.card.object.Contact;

/**
 * Created by steviekideckel on 11/9/14.
 */
public class ContactDisplayActivity extends BaseCardActivity{

    private Contact mContact;
    private ContactDisplayFragment mDisplayFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toolbar_frame);

        if(savedInstanceState == null){
            mContact = getIntent().getParcelableExtra("contact");
        }else{
            mContact = savedInstanceState.getParcelable("contact");
        }

        mDisplayFragment = ContactDisplayFragment.newInstance(mContact);
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, mDisplayFragment).commit();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("contact", mContact);
    }
}
