package com.example.android.chatapp.Models;

import android.widget.LinearLayout;

import java.io.Serializable;
import java.util.List;

public class Chat implements Serializable {

    private String id;
    private List<String> userIds;

    public Chat(){

    }

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public List<String> getUserIds(){
        return userIds;
    }

    public void setUserIds(List<String> userIds){
        this.userIds = userIds;
    }
}
