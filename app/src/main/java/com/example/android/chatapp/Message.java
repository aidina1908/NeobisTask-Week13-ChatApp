package com.example.android.chatapp;


import com.google.firebase.Timestamp;
import com.google.firebase.firestore.ServerTimestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Message {
    private String id;
    private String displayName;
    private String text;
    private String senderId;
    @ServerTimestamp
    private Timestamp timestamp;
    private boolean isRead;


    public Message() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDisplayName(){
        return displayName;
    }

    public void setDisplayName(String displayName){
        this.displayName = displayName;
    }

    public void setTime(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public Timestamp getTime() {
        return timestamp;
    }

    public String getReadableDate() {
        Date d = getTime().toDate();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
        return sdf.format(d);
    }

    public boolean isRead() {
        return isRead;
    }

    public String getText() {
        return text;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }
}