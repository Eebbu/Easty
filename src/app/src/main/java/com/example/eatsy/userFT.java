package com.example.eatsy;

import java.util.ArrayList;

public class userFT {
    private String username;
    private String userID;
    private String email;

    private ArrayList<String> postID;

    private String photoURL ;

    public userFT(String username, String userID, String email, String photoURL,ArrayList<String> postID) {
        this.username = username;
        this.userID = userID;
        this.email = email;
        this.postID = postID;
        this.photoURL = photoURL;
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

    public  String getPhotoURL(){return  photoURL;};

    public void setUsername(String username) {
        this.username = username;

    }

    public void photoURL(String photoURL) {
        this.photoURL = photoURL;

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
