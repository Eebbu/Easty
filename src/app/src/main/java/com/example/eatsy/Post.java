package com.example.eatsy;

import android.net.Uri;

import androidx.annotation.NonNull;

import com.google.firebase.storage.StorageReference;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.io.Serializable;

/**
 * The Post class represents a post in our application.
 * This class includes details such as user identification, post type, and content descriptions,
 * which are essential for managing the posts in our APP.
 */
/**
 * @author Zihan Yuan(u7773880) Boxuan Lin(u7705128)
 */

public class Post implements Serializable{
    private static final long serialVersionUID = 1L;
    private String id;
    @SerializedName("userid")
    private String userID;
    @SerializedName("username")
    private String userName;
    @SerializedName("post_type")
    private String postType;

    @SerializedName("post_title")
    private String postTitle;
    @SerializedName("post_description")
    private String postDescription;

    private String quantity;

    @SerializedName("pick_up_times")
    private String pickUpTimes;

    private Address Address;



    private ArrayList<String> images = null;

    private String food;
    private String wantInExchange;
    private Uri filePath;
    private StorageReference storageReference;

    public Post(String id, String userID, String userName, String postType, String postTitle, String postDescription, String quantity, String pickUpTimes, String latitude, String longitude, ArrayList<String> images, String food) {
    }

    public static class Address implements Serializable {
        @SerializedName("latitude")
        private String latitude;

        @SerializedName("longitude")
        private String longitude;

        public Address(String latitude,String longitude){
            this.latitude = latitude;
            this.longitude = longitude;
        }
    }


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
        this.Address = new Address(latitude,longitude);
//        this.images = new ArrayList<>(images);
//        this.food = food;
    }
    public Post(String id, String userID, String userName, String postType, String postTitle, String postDescription, String quantity, String pickUpTimes, String latitude, String longitude, ArrayList<String> images) {
        this.id = id;
        this.userID = userID;
        this.userName = userName;
        this.postType = postType;
        this.postTitle = postTitle;
        this.postDescription = postDescription;
        this.quantity = quantity;
        this.pickUpTimes = pickUpTimes;
        this.Address = new Address(latitude,longitude);
        this.images = new ArrayList<>(images);

    }
    public  Post(String userID,String userName,String postType,String postTitle,String postDescription, String quantity, String pickUpTimes,String latitude,String longitude,ArrayList<String> images){

        this.userID = userID;
        this.userName = userName;
        this.postType = postType;
        this.postTitle = postTitle;
        this.postDescription = postDescription;
        this.quantity = quantity;
        this.pickUpTimes = pickUpTimes;
        this.Address = new Address(latitude,longitude);
        this.images = new ArrayList<>(images);

    }
    // Abstract method to save to Firebase
    public void saveToFirebase(){
        System.out.println("username" + userName);
        System.out.println("userID"+ userID);
        System.out.println("quantity" + quantity);
        System.out.println("postType" + postType);
        System.out.println("postTitle" + postTitle);
        System.out.println("Des "+postDescription);
        System.out.println(getLongitude());
        System.out.println(getLatitude());
        System.out.println(images);
    };

    // Getter methods for retrieving post information
    public String getId(){return id;}

    public void setId(String id){this.id = id;}
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
    public String getWantInExchange(){ return wantInExchange;}

    public String getLatitude() {
        return this.Address.latitude;
    }

    public String getLongitude() {
        return this.Address.longitude;
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


//    @NonNull
//    @Override
//    public String toString() {
//        System.out.println("userid= " + userID);
//        System.out.println("username= " + userName);
//        System.out.println("post_type= " + postType);
//        System.out.println("post_title= " + postTitle);
//        System.out.println("post_description= " + postDescription);
//        System.out.println("quantity= " + quantity);
//        System.out.println("pick_up_times= " + pickUpTimes);
//        System.out.println("pick_up_times= " + pickUpTimes);
//        System.out.println("latitude= " + Address.latitude);
//        System.out.println("longitude= " + Address.longitude);
//        System.out.println("images= " + images.get(0));
//        System.out.println("food= " + food);
//        return super.toString();
//    }
}
