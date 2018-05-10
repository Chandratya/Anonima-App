package com.krein.chandratya.anonimaapp;

import java.io.Serializable;

/**
 * Created by Leidy on 5/5/2018.
 */

public class CommentModel implements Serializable {
    private String key;
    private String userId;
    private String userName;
    private String comment;
    private String role;

    public CommentModel() {
    }

    public CommentModel(String userId, String userName, String comment, String role) {
        this.userId = userId;
        this.userName = userName;
        this.comment = comment;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
