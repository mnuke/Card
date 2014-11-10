package com.sixbynine.card.model;

import android.util.DisplayMetrics;
import android.util.TypedValue;

/**
 * Created by steviekideckel on 11/9/14.
 */
public enum LogoSize {
    SMALL(32),
    MEDIUM(48),
    LARGE(64),
    HUGE(96);

    int baseDimension;

    LogoSize(int baseDimension){
        this.baseDimension = baseDimension;
    }

    public int toPixels(DisplayMetrics displayMetrics){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, baseDimension, displayMetrics);
    }


}
