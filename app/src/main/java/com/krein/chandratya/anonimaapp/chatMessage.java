package com.krein.chandratya.anonimaapp;

import java.util.Date;

/**
 * Created by Chandratya on 09/05/2018.
 */

public class chatMessage {
    private String messageTxt;
    private String messageUser;
    private long messageTime;

    public chatMessage(String messageTxt, String messageUser) {
        this.messageTxt = messageTxt;
        this.messageUser = messageUser;

        messageTime = new Date().getTime();
    }

    public chatMessage(){

    }

    public String getMessageTxt() {
        return messageTxt;
    }

    public void setMessageTxt(String messageTxt) {
        this.messageTxt = messageTxt;
    }

    public String getMessageUser() {
        return messageUser;
    }

    public void setMessageUser(String messageUser) {
        this.messageUser = messageUser;
    }

    public long getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(long messageTime) {
        this.messageTime = messageTime;
    }
}
