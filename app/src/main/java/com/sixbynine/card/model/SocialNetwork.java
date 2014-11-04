package com.sixbynine.card.model;

import com.sixbynine.card.R;

/**
 * Created by steviekideckel on 10/26/14.
 */
public enum SocialNetwork {
    TELEPHONY(0, "telephony",0, R.string.telephony, false),
    FACEBOOK(1, "facebook", R.drawable.icon_fb, R.string.facebook, false),
    LINKEDIN(2, "linkedin",R.drawable.icon_linkedin, R.string.linkedin, false),
    SNAPCHAT(3, "snapchat", R.drawable.icon_snapchat, R.string.snapchat, true),
    INSTAGRAM(4, "instagram", R.drawable.icon_instagram, R.string.instagram, true),
    TWITTER(5, "twitter", R.drawable.icon_twitter, R.string.twitter, true);


    String key;
    int id;
    int logo;
    int name;
    boolean atInHandle;


    SocialNetwork(int id, String key, int logo, int name, boolean atInHandle){
        this.id = id;
        this.key = key;
        this.logo = logo;
        this.name = name;
        this.atInHandle = atInHandle;
    }

    public String getKey(){
        return key;
    }

    public int getId(){
        return id;
    }

    public int getLogo(){
        return logo;
    }

    public int getName(){
        return name;
    }

    public boolean hasAtInHandle(){
        return atInHandle;
    }

    public static SocialNetwork fromId(int id){
        for(SocialNetwork network : SocialNetwork.values()){
            if(network.id == id){
                return network;
            }
        }
        return null;
    }

}
