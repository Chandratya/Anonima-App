package com.krein.chandratya.anonimaapp;

/**
 * Created by Chandratya on 01/05/2018.
 */

public class NearbyCons {
    public String key;
    public String user;
    public String posting;
    public String keyComment;
    public NearbyCons(){}
    public NearbyCons(String key, String user, String posting, String keyComment) {
        this.key = key;
        this.user = user;
        this.posting = posting;
        this.keyComment = keyComment;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPosting() {
        return posting;
    }

    public void setPosting(String posting) {
        this.posting = posting;
    }

    public String getKeyComment() {
        return keyComment;
    }

    public void setKeyComment(String keyComment) {
        this.keyComment = keyComment;
    }
}
