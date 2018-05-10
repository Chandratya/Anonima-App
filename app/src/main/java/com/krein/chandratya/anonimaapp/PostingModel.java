package com.krein.chandratya.anonimaapp;

import java.io.Serializable;

/**
 * Created by Leidy on 5/5/2018.
 */

public class PostingModel implements Serializable {
    private String key;
    private String userId;
    private String userName;
    private String posting;
    private String role;

    public PostingModel() {
    }

    public PostingModel(String userId, String userName, String posting, String role) {
        this.userId = userId;
        this.userName = userName;
        this.posting = posting;
        this.role = role;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPosting() {
        return posting;
    }

    public void setPosting(String posting) {
        this.posting = posting;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
