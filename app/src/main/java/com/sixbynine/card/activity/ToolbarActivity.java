package com.sixbynine.card.activity;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

import com.sixbynine.card.R;

/**
 * Created by steviekideckel on 10/26/14.
 */
public abstract class ToolbarActivity extends ActionBarActivity{
    private Toolbar mToolbar;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
    }

    protected Toolbar getToolbar(){
        return mToolbar;
    }
}
