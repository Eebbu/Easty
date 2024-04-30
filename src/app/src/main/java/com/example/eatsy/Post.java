package com.example.eatsy;

import java.util.ArrayList;

/**
 * The Post class represents a post in our application.
 * This class includes details such as user identification, post type, and content descriptions,
 * which are essential for managing the posts in our APP.
 */

public abstract class Post {
    private String userID;
    private String userName;
    private String postType;

    private String postTitle;
    private String postDescription;

    private String quantity;

    private String pickUpTimes;

    private String latitude;

    private String longitude;

    private ArrayList<String> images;

    private String food;
    private String wantInExchange;

//    public  Post(String userID,String userName,String postType,String postTitle,String postDescription, String quantity, String pickUpTimes,String latitude,String longitude,ArrayList<String> images,String food){
public  Post(String userName,String postType,String postTitle,String postDescription, String quantity,String latitude,String longitude){
//
//        this.userID = userID;
        this.userName = userName;
        this.postType = postType;
        this.postTitle = postTitle;
        this.postDescription = postDescription;
        this.quantity = quantity;
//        this.pickUpTimes = pickUpTimes;
        this.latitude = latitude;
        this.longitude = longitude;
//        this.images = new ArrayList<>(images);
//        this.food = food;
    }
    // Abstract method to save to Firebase
    public abstract void saveToFirebase();

    // Getter methods
    public String getUserID() {
        return userID;
    }

    public String getUserName() {
        return userName;
    }

    public String getPostType() {
        return postType;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public String getPostDescription() {
        return postDescription;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getPickUpTimes() {
        return pickUpTimes;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public ArrayList<String> getImages() {
        return new ArrayList<>(images);
    }

    public String getFood() {
        return food;
    }

//    // Setter methods
//    public void setUserID(String userID) {
//        this.userID = userID;
//    }
//
//    public void setUserName(String userName) {
//        this.userName = userName;
//    }
//
//    public void setPostType(String postType) {
//        this.postType = postType;
//    }
//
//    public void setPostTitle(String postTitle) {
//        this.postTitle = postTitle;
//    }
//
//    public void setPostDescription(String postDescription) {
//        this.postDescription = postDescription;
//    }

//    public void setQuantity(String quantity) {
//        this.quantity = quantity;
//    }
//
    public void setPickUpTimes(String pickUpTimes) {
        this.pickUpTimes = pickUpTimes;
    }
    public void setWantInExchange(String wantInExchange){
        this.wantInExchange = wantInExchange;
    }
//
//    public void setLatitude(String latitude) {
//        this.latitude = latitude;
//    }
//
//    public void setLongitude(String longitude) {
//        this.longitude = longitude;
//    }
//
//    public void setImages(ArrayList<String> images) {
//        this.images = new ArrayList<>(images);
//    }
    public void addImages(String image) {
        this.images.add(image);
    }
//
//    public void setFood(String food) {
//        this.food = food;
//    }


}
