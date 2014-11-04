package com.sixbynine.card.fragment;

import android.app.Activity;
import android.support.v4.app.ListFragment;
import android.support.v7.app.ActionBarActivity;

/**
 * Created by steviekideckel on 10/26/14.
 */
public class ActionBarListFragment extends ListFragment{

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(!(activity instanceof ActionBarActivity)){
            throw new IllegalStateException(activity.toString() + " must be an ActionBarActivity");
        }
    }

    public final ActionBarActivity getActionBarActivity(){
        return (ActionBarActivity) getActivity();
    }

}
