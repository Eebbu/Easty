package com.example.eatsy;

import android.net.Uri;

import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

/**
 * The Post class represents a post in our application.
 * This class includes details such as user identification, post type, and content descriptions,
 * which are essential for managing the posts in our APP.
 */
/**
 * @author Zihan Yuan(u7773880) Boxuan Lin(u7705128)
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

    private ArrayList<String> images = null;

    private String food;
    private String wantInExchange;
    private Uri filePath;
    private StorageReference storageReference;


//    public  Post(String userID,String userName,String postType,String postTitle,String postDescription, String quantity, String pickUpTimes,String latitude,String longitude,ArrayList<String> images,String food){
// Attributes to store user and post details
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

    // Getter methods for retrieving post information
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
    public Uri getFilePath(){return filePath;}
    //public String getWantInExchange(String wantInExchange){return getWantInExchange;}
    public StorageReference getStorageReference(){return this.storageReference;}


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

// Methods to modify post details
    public void setPickUpTimes(String pickUpTimes) {
        this.pickUpTimes = pickUpTimes;
    }
    public void setWantInExchange(String wantInExchange){
        this.wantInExchange = wantInExchange;
    }
    public void setFilePath(Uri filePath){
        this.filePath = filePath;
    }
    public void setStorageReference(StorageReference storageReference){
        this.storageReference = storageReference;
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
        if(images == null){
            images = new ArrayList<>();
            images.add(image);
        }else
            this.images.add(image);
    }
//
//    public void setFood(String food) {
//        this.food = food;
//    }


}
