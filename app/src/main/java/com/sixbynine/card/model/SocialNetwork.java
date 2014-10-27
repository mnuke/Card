package com.sixbynine.card.model;

/**
 * Created by steviekideckel on 10/26/14.
 */
public enum SocialNetwork {
    TELEPHONY("telephony"),
    FACEBOOK("facebook"),
    LINKEDIN("linkedin");

    private String key;

    SocialNetwork(String key){
        this.key = key;
    }

    public String getKey(){
        return key;
    }

    public static SocialNetwork fromKey(String key){
        for(SocialNetwork network : SocialNetwork.values()){
            if(network.getKey().equals(key)){
                return network;
            }
        }
        return null;
    }

}
