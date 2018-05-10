package com.krein.chandratya.anonimaapp;

import java.io.Serializable;

/**
 * Created by waski on 10/05/2018.
 */

public class ChatModel implements Serializable{
    String key, keyItem;
    String txt, date;
    String userFrom, userTo;
    String userFromEmail, userToEmail;

    public ChatModel() {
    }

    public ChatModel(String txt, String date, String userFrom, String userTo, String userFromEmail, String userToEmail) {
        this.txt = txt;
        this.date = date;
        this.userFrom = userFrom;
        this.userTo = userTo;
        this.userFromEmail = userFromEmail;
        this.userToEmail = userToEmail;
    }

    public String getUserFromEmail() {
        return userFromEmail;
    }

    public void setUserFromEmail(String userFromEmail) {
        this.userFromEmail = userFromEmail;
    }

    public String getUserToEmail() {
        return userToEmail;
    }

    public void setUserToEmail(String userToEmail) {
        this.userToEmail = userToEmail;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getKeyItem() {
        return keyItem;
    }

    public void setKeyItem(String keyItem) {
        this.keyItem = keyItem;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUserFrom() {
        return userFrom;
    }

    public void setUserFrom(String userFrom) {
        this.userFrom = userFrom;
    }

    public String getUserTo() {
        return userTo;
    }

    public void setUserTo(String userTo) {
        this.userTo = userTo;
    }
}
