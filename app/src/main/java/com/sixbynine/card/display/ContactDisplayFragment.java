package com.sixbynine.card.display;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.sixbynine.card.R;
import com.sixbynine.card.fragment.ActionBarFragment;
import com.sixbynine.card.object.Contact;

/**
 * Created by steviekideckel on 11/9/14.
 */
public class ContactDisplayFragment extends ActionBarFragment{

    private Contact mContact;
    private TextView mNameTextView;
    private ListView mListView;
    private SocialNetworkAdapter mAdapter;

    public static ContactDisplayFragment newInstance(Contact contact){
        ContactDisplayFragment frag = new ContactDisplayFragment();
        Bundle b = new Bundle();
        b.putParcelable("contact", contact);
        frag.setArguments(b);
        return frag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContact = getArguments().getParcelable("contact");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact_display, container, false);

        mNameTextView = (TextView) view.findViewById(R.id.name);
        mListView = (ListView) view.findViewById(R.id.list_view);
        mAdapter = new SocialNetworkAdapter(getActivity(), mContact.getSocialNetworkMap());
        mListView.setAdapter(mAdapter);

        return view;
    }





}
