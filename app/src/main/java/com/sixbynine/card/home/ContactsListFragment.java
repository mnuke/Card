package com.sixbynine.card.home;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.melnykov.fab.FloatingActionButton;
import com.sixbynine.card.R;
import com.sixbynine.card.fragment.ActionBarFragment;
import com.sixbynine.card.persistent.ContactDataSource;

/**
 * Created by steviekideckel on 10/26/14.
 */
public class ContactsListFragment extends ActionBarFragment{

    private static final String ARG_CONTACTS = "arg-contacts";
    private static final String ARG_DISPLAY_CONTACTS = "arg-display-contacts";

    private Callback mCallback;
    private ListView mListView;
    private ContactsCursorAdapter mAdapter;
    private FloatingActionButton mFloatingActionButton;
    private ContactDataSource mDataSource;

    public interface Callback{
        public void onActionButtonClicked();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(activity instanceof Callback){
            mCallback = (Callback) activity;
        }else{
            throw new IllegalStateException(activity.toString() + " must implement Callback interface");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallback = null;
    }

    public static ContactsListFragment newInstance(){
        return new ContactsListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDataSource = new ContactDataSource();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contacts, container, false);

        mListView = (ListView) view.findViewById(R.id.list_view);

        mAdapter = new ContactsCursorAdapter(getActivity(), mDataSource.queryAllContacts(), 0);
        mListView.setAdapter(mAdapter);

        mFloatingActionButton = (FloatingActionButton) view.findViewById(R.id.fab);
        mFloatingActionButton.attachToListView(mListView);
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mCallback != null) mCallback.onActionButtonClicked();
            }
        });

        return view;
    }

    public void refreshContactList(){
        mAdapter.changeCursor(mDataSource.queryAllContacts());
    }




}
