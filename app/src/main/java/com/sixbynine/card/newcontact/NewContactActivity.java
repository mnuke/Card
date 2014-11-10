package com.sixbynine.card.newcontact;

import android.os.Bundle;
import android.widget.Toast;

import com.sixbynine.card.R;
import com.sixbynine.card.activity.BaseCardActivity;
import com.sixbynine.card.manager.DatabaseManager;
import com.sixbynine.card.manager.UpdateEvent;
import com.sixbynine.card.manager.UpdateListener;
import com.sixbynine.card.object.Contact;

/**
 * Created by steviekideckel on 11/8/14.
 */
public class NewContactActivity extends BaseCardActivity implements NewContactFragment.Callback, UpdateListener {

    private NewContactFragment mNewContactFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toolbar_frame);
        mNewContactFragment = NewContactFragment.newInstance();
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, mNewContactFragment).commit();
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
    public void onContactSaved(Contact contact) {
        DatabaseManager.getInstance().saveContact(contact);
    }

    @Override
    public void update(UpdateEvent e, Object... data) {
        switch(e){
            case CONTACT_SAVED:
                setResult(RESULT_OK);
                finish();
                break;
            case CONTACT_ERROR_SAVING:
                Toast.makeText(this, R.string.error_saving_contact, Toast.LENGTH_SHORT).show();
                setResult(RESULT_CANCELED);
                finish();
                break;
        }

    }
}
