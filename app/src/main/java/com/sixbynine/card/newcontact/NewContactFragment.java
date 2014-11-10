package com.sixbynine.card.newcontact;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.sixbynine.card.R;
import com.sixbynine.card.fragment.ActionBarFragment;
import com.sixbynine.card.model.SocialNetwork;
import com.sixbynine.card.object.Contact;
import com.sixbynine.card.ui.dialog.SocialNetworkPickerDialog;
import com.sixbynine.card.ui.view.SocialNetworkEntryView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by steviekideckel on 11/3/14.
 */
public class NewContactFragment extends ActionBarFragment{

    private EditText mNameEditText;
    private EditText mPhoneEditText;

    private ImageButton mAddButton;
    private LinearLayout mSocialNetworkContainer;
    private List<SocialNetworkEntryView> mSocialNetworkEntryViews;
    private Callback mCallback;

    public interface Callback{
        public void onContactSaved(Contact contact);
    }

    private SocialNetworkEntryView.OnRemoveListener mOnRemoveListener = new SocialNetworkEntryView.OnRemoveListener() {
        @Override
        public void onRemove(SocialNetworkEntryView view) {
            removeSocialNetwork(view);
        }

        @Override
        public void onNetworkTapped(final SocialNetworkEntryView view) {
            SocialNetworkPickerDialog dialog = SocialNetworkPickerDialog.newInstance(new SocialNetworkPickerDialog.SocialNetworkPickerDialogListener() {
                @Override
                public void onCancel() {

                }

                @Override
                public void onSocialNetworkPicked(SocialNetwork network) {
                    view.setNetwork(network);
                }
            });
            dialog.show(getChildFragmentManager(), "picker");
        }
    };

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(activity instanceof Callback){
            mCallback = (Callback) activity;
        }else{
            throw new IllegalStateException(activity.toString() + " must implement Callback interface");
        }

    }

    public static NewContactFragment newInstance(){
        NewContactFragment frag = new NewContactFragment();
        return frag;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        final ActionBar actionBar = getActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(R.string.new_contact);
            menu.clear();
            inflater.inflate(R.menu.menu_new_contact, menu);
        }
        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                getActivity().onBackPressed();
                return true;
            case R.id.action_save:
                saveAndCallback();
                return true;
        }
        return false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_contact, container, false);

        mNameEditText = (EditText) view.findViewById(R.id.name_edit_text);
        mPhoneEditText = (EditText) view.findViewById(R.id.phone_edit_text);

        mAddButton = (ImageButton) view.findViewById(R.id.add_button);
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addSocialNetwork();
            }
        });

        mSocialNetworkContainer = (LinearLayout) view.findViewById(R.id.social_network_container);
        mSocialNetworkEntryViews = new ArrayList<SocialNetworkEntryView>();
        for(int i = 0; i < mSocialNetworkContainer.getChildCount(); i ++){
            View child = mSocialNetworkContainer.getChildAt(i);
            if(child instanceof SocialNetworkEntryView){
                mSocialNetworkEntryViews.add((SocialNetworkEntryView) child);
                ((SocialNetworkEntryView) child).setOnRemoveListener(mOnRemoveListener);
            }
        }

        setHasOptionsMenu(true);
        return view;
    }

    private void addSocialNetwork(){
        SocialNetworkPickerDialog dialog = SocialNetworkPickerDialog.newInstance(new SocialNetworkPickerDialog.SocialNetworkPickerDialogListener() {
            @Override
            public void onCancel() {

            }

            @Override
            public void onSocialNetworkPicked(SocialNetwork network) {
                SocialNetworkEntryView entryView = new SocialNetworkEntryView(getActivity());
                entryView.setNetwork(network);
                mSocialNetworkEntryViews.add(entryView);
                entryView.setOnRemoveListener(mOnRemoveListener);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.topMargin = getResources().getDimensionPixelOffset(R.dimen.social_margin_bottom);
                mSocialNetworkContainer.addView(entryView, layoutParams);
            }
        });
        dialog.show(getChildFragmentManager(), "picker");

    }

    private void removeSocialNetwork(SocialNetworkEntryView view){
        mSocialNetworkContainer.removeView(view);
        mSocialNetworkEntryViews.remove(view);
    }

    private void saveAndCallback(){
        String name = mNameEditText.getText().toString();
        if(name == null || name.trim().isEmpty()){
            mNameEditText.requestFocus();
            mNameEditText.setError(getString(R.string.please_enter_name));
            return;
        }

        Contact contact = new Contact();
        contact.setName(name);

        String phoneNumber = mPhoneEditText.getText().toString();
        if(phoneNumber != null && !phoneNumber.isEmpty()){
            contact.addSocialNetwork(SocialNetwork.TELEPHONY, phoneNumber);
        }

        for(SocialNetworkEntryView networkEntryView : mSocialNetworkEntryViews){
            if(networkEntryView.hasValue()){
                contact.addSocialNetwork(networkEntryView.getNetwork(), networkEntryView.getText());
            }
        }


        mCallback.onContactSaved(contact);
    }

}
