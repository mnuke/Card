package com.sixbynine.card.util;

import android.util.Log;

import com.sixbynine.card.BuildConfig;

/**
 * Created by steviekideckel on 10/26/14.
 */
public class Logger {

    private static final String TAG = "card_android";

    public static void d(String text){
        if(BuildConfig.DEBUG){
            Log.d(TAG, text);
        }
    }

    public static void e(String text){
        if(BuildConfig.DEBUG){
            Log.e(TAG, text);
        }
    }

    public static void w(String text){
        if(BuildConfig.DEBUG){
            Log.w(TAG, text);
        }
    }

    public static void i(String text){
        if(BuildConfig.DEBUG){
            Log.i(TAG, text);
        }
    }

    public static void v(String text){
        if(BuildConfig.DEBUG){
            Log.v(TAG, text);
        }
    }

}
