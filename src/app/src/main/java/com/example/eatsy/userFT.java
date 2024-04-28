package com.example.eatsy;

import java.util.ArrayList;

public class userFT {
    private String username;
    private String password;
    private String userID;
    private String email;

    private ArrayList<String> postID;

    public userFT(String username, String userID, String email, ArrayList<String> postID) {
        this.username = username;
        this.userID = userID;
        this.email = email;
        this.postID = postID;
    }

    public String getUsername() {
        return username;
    }


    public String getUserID() {
        return userID;
    }

    public ArrayList<String> getPostID() {
        return postID;
    }

    public String getEmail() {
        return email;
    }

    public void setUsername(String username) {
        this.username = username;

    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPostID(ArrayList<String> postID) {
        this.postID = postID;
    }
}
