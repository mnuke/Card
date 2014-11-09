package com.sixbynine.card.fragment;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;

/**
 * Created by steviekideckel on 10/26/14.
 */
public class ActionBarFragment extends Fragment{

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

    public final ActionBar getActionBar(){
        if(getActionBarActivity() != null){
            return getActionBarActivity().getSupportActionBar();
        }else{
            return null;
        }
    }
}
