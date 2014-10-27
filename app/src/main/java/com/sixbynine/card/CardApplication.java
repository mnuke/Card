package com.sixbynine.card;

import android.app.Application;

/**
 * Created by steviekideckel on 10/26/14.
 */
public class CardApplication extends Application{

    private static CardApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static CardApplication getInstance(){
        return instance;
    }
}
