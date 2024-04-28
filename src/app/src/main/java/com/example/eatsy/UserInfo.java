package com.example.eatsy;

public class UserInfo {
    private String username;
    private String password;
    private String userID;
    private String email;

    private String postID;

    public UserInfo(String username, String password, String userID, String email, String postID) {
        this.username = username;
        this.password = password;
        this.userID = userID;
        this.email = email;
        this.postID = postID;
    }

    public String getUsername() {
        return username;
    }

    public  String getPassword(){
        return password;
    }

    public String getUserID() {
        return userID;
    }

    public String getPostID() {
        return postID;
    }

    public String getEmail() {
        return email;
    }

    public void setUsername(String username) {
        this.username = username;

    }
    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPostID(String postID) {
        this.postID = postID;
    }
}
