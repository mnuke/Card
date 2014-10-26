package com.sixbynine.card.splash;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sixbynine.card.R;
import com.sixbynine.card.fragment.ActionBarFragment;

/**
 * Created by steviekideckel on 10/26/14.
 */
public class SplashFragment extends ActionBarFragment{

    public static SplashFragment newInstance(){
        SplashFragment fragment = new SplashFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_splash, container, false);
        return view;
    }
}
