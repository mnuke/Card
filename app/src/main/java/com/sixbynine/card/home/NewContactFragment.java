package com.sixbynine.card.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sixbynine.card.R;
import com.sixbynine.card.fragment.ActionBarFragment;

/**
 * Created by steviekideckel on 11/3/14.
 */
public class NewContactFragment extends ActionBarFragment{

    public static NewContactFragment newInstance(){
        NewContactFragment frag = new NewContactFragment();
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_contact, container, false);

        return view;
    }
}
