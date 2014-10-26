package com.sixbynine.card.splash;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.sixbynine.card.R;
import com.sixbynine.card.activity.BaseCardActivity;


public class SplashActivity extends BaseCardActivity {

    SplashFragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toolbar_frame);
        setUpActionBar();

        mFragment = SplashFragment.newInstance();
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, mFragment).commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.splash, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
