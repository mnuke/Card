package com.sixbynine.card.object;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.util.Pair;

import com.sixbynine.card.model.SocialNetwork;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by steviekideckel on 10/26/14.
 */
public class SocialNetworkMap extends HashMap<SocialNetwork, String> implements Parcelable{


    public JSONArray toJsonArray(){
        JSONArray arr = new JSONArray();
        try {
            for (Map.Entry<SocialNetwork, String> network : entrySet()) {
                JSONObject obj = new JSONObject();
                obj.put("key", network.getKey().getId());
                obj.put("value", network.getValue());
                arr.put(obj);
            }
        }catch(Exception e){

        }
        return arr;
    }

    public String toJsonArrayString(){
        return toJsonArray().toString();
    }

    public static SocialNetworkMap fromJsonArray(JSONArray jsonArray){
        SocialNetworkMap map = new SocialNetworkMap();
        try {
            int len = jsonArray.length();
            for (int i = 0; i < len; i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                int id = obj.getInt("id");
                String value = obj.getString("value");
                map.put(SocialNetwork.fromId(id), value);
            }
        }catch(Exception e){

        }
        return map;
    }

    public static SocialNetworkMap fromJsonArray(String jsonArray){
        try {
            return fromJsonArray(new JSONArray(jsonArray));
        }catch(Exception e){
            return null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(keySet().size());
        for(Map.Entry<SocialNetwork, String> network : entrySet()){
            dest.writeInt(network.getKey().getId());
            dest.writeString(network.getValue());
        }
    }

    public static final Creator<SocialNetworkMap> CREATOR = new Creator<SocialNetworkMap>() {
        @Override
        public SocialNetworkMap createFromParcel(Parcel source) {
            SocialNetworkMap map = new SocialNetworkMap();
            int numNetworks = source.readInt();
            for(int i = 0; i < numNetworks; i++){
                int network = source.readInt();
                String value = source.readString();
                map.put(SocialNetwork.fromId(network), value);
            }
            return map;
        }

        @Override
        public SocialNetworkMap[] newArray(int size) {
            return new SocialNetworkMap[size];
        }
    };

    public ArrayList<Pair<SocialNetwork, String>> toPairArrayList(){
        ArrayList<Pair<SocialNetwork, String>> list = new ArrayList<Pair<SocialNetwork, String>>(size());
        for(Map.Entry<SocialNetwork, String> entry : entrySet()){
            list.add(new Pair<SocialNetwork, String>(entry.getKey(), entry.getValue()));
        }
        return list;
    }
}
