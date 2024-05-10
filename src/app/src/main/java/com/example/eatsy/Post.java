package com.example.eatsy;

import android.net.Uri;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.StorageReference;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

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
    private String wantInExchange;
    private Uri filePath;
    private StorageReference storageReference;

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
        this.userID = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        this.userName = userName;
        this.postType = postType;
        this.postTitle = postTitle;
        this.postDescription = postDescription;
        this.quantity = quantity;
        this.Address = new Address(latitude,longitude);
        this.wantInExchange = "";
        this.images = new ArrayList<>();
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
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseFirestore db = FirebaseFirestore.getInstance();  // Get FireStore instance
        Uri filePath = this.getFilePath();
        StorageReference storageReference = getStorageReference();
        if (filePath != null) {
            String userEmail = user.getEmail(); // Get current user's email address
            String newPostId = DataManager.generateTimestampBasedId(); // Generate ID based on timestamp
            storageReference.putFile(filePath).addOnSuccessListener(taskSnapshot -> taskSnapshot.getStorage().getDownloadUrl().addOnSuccessListener(uri -> {
                Map<String, Object> postMap = new HashMap<>();
                postMap.put("userName", DashboardActivity.users.get(userEmail));
                postMap.put("userID", userEmail);
                postMap.put("postType", this.getPostType());
                postMap.put("postTitle", this.getPostTitle());
                postMap.put("postDescription", this.getPostDescription());
                postMap.put("quantity", this.getQuantity());
                postMap.put("latitude", this.getLatitude());
                postMap.put("longitude", this.getLongitude());
                postMap.put("pickUpTimes", this.getPickUpTimes());
                postMap.put("imageURL", uri.toString());
                // Save post data to FireStore using the newly generated post ID
                db.collection("posts").document(newPostId).set(postMap)
                        .addOnSuccessListener(aVoid -> {
                            System.out.println("Post data successfully saved!");
                            // Update user's post list
                            DocumentReference userRef = db.collection("users").document(userEmail);
                            userRef.update("postid", FieldValue.arrayUnion(newPostId))
                                    .addOnSuccessListener(aVoid1 -> System.out.println("Post ID added to user profile"))
                                    .addOnFailureListener(e -> System.err.println("Failed to add post ID to user profile: " + e.getMessage()));
                        }).addOnFailureListener(e -> System.err.println("Error saving post data: " + e.getMessage()));
            }).addOnFailureListener(e -> System.err.println("Error getting file URL: " + e.getMessage()))).addOnFailureListener(e -> System.err.println("Error uploading file: " + e.getMessage()));
        } else {
            System.err.println("File path is null or user is not logged in");
        }
    }

    // Getter methods for retrieving post information
    public String getId(){return id;}
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

    public Uri getFilePath(){return filePath;}
    //public String getWantInExchange(String wantInExchange){return getWantInExchange;}
    public StorageReference getStorageReference(){return this.storageReference;}
    public void setId(String id){this.id = id;}
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
    public void addImages(String image) {
        if(images == null){
            images = new ArrayList<>();
            images.add(image);
        }else
            this.images.add(image);
    }


    @NonNull
    @Override
    public String toString() {
        System.out.println("userid= " + userID);
        System.out.println("username= " + userName);
        System.out.println("post_type= " + postType);
        System.out.println("post_title= " + postTitle);
        System.out.println("post_description= " + postDescription);
        System.out.println("quantity= " + quantity);
        System.out.println("pick_up_times= " + pickUpTimes);
        System.out.println("latitude= " + Address.latitude);
        System.out.println("longitude= " + Address.longitude);
        if (images.size() == 0){
            System.out.println("images = ");
        }else{
            System.out.println("images= " + images.get(0));
        }
        System.out.println("wantInExchange= " + wantInExchange);
        return super.toString();
    }
}
