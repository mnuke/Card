package com.sixbynine.card.manager;

import java.util.HashSet;

/**
 * Created by steviekideckel on 10/26/14.
 */
public abstract class Manager {

    private HashSet<UpdateListener> mListeners = new HashSet<UpdateListener>();

    public boolean subscribe(UpdateListener listener){
        return mListeners.add(listener);
    }

    public boolean unSubscribe(UpdateListener listener){
        return mListeners.remove(listener);
    }

    protected void publish(UpdateEvent e, Object... data){
        for(UpdateListener listener : mListeners){
            listener.update(e, data);
        }
    }

}
