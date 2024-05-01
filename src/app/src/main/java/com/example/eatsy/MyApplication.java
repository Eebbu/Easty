package com.example.eatsy;

import android.app.Application;
import android.util.Log;

import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MyApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(this);

        CollectionReference usersCollectionRef = FirestoreHelper.getCollectionRef("users");
        UserDataDownloader downloader = new UserDataDownloader();

        // Get the posts collection reference
        CollectionReference postsCollectionRef = FirestoreHelper.getCollectionRef("posts");
        PostDataDownloader postDownloader = new PostDataDownloader();

        // Call the asynchronous method, and handle the returned CompletableFuture
        downloader.downloadData(usersCollectionRef)
                .thenAccept(data ->  {
                    DataManager.getDataInstance().setUserHashMap(data); // 存储到 DataManager
                    // Success: data received
                    for (Map.Entry<String, userFT> entry : data.entrySet()) {
                        String email = entry.getKey();
                        userFT user = entry.getValue();

                        // Printing the data, only for testing purposes
                        System.out.println("Email: " + email);
                        System.out.println("Username: " + user.getUsername());
                        System.out.println("Post IDs: " + user.getPostID()); // Ensure correct method name
                        System.out.println("Photo URL: " + user.getPhotoURL());
                        System.out.println("------------------------");
                    }
                })
                .exceptionally(ex -> {
                    // Handle the case of download failure
                    System.err.println("Data download failed: " + ex.getMessage());
                    return null; // Return a value of the same type as thenAccept
                });
        // Download and handle post data
        postDownloader.downloadData(postsCollectionRef)
                .thenAccept(data -> { DataManager.getDataInstance().setPostHashMap(data); // 存储到 DataManager
                    // Success: post data received
                    for (Map.Entry<String, PostFT> entry : data.entrySet()) {
                        String postID = entry.getKey();
                        PostFT post = entry.getValue();

                        // Printing the post data, only for testing purposes
                        System.out.println("Post ID: " + postID);
                        System.out.println("User Name: " + post.getUserName());
                        System.out.println("Post Type: " + post.getPostType());
                        System.out.println("Post Title: " + post.getPostTitle());
                        System.out.println("Description: " + post.getPostDescription());
                        System.out.println("Quantity: " + post.getQuantity());
                        System.out.println("Pick Up Times: " + post.getPickUpTimes());
                        System.out.println("Latitude: " + post.getLatitude());
                        System.out.println("Longitude: " + post.getLongitude());
                        System.out.println("Images: " + post.getImages());
                        System.out.println("------------------------");
                    }
                })
                .exceptionally(ex -> {
                    // Handle the case of post data download failure
                    System.err.println("Post data download failed: " + ex.getMessage());
                    return null;
                });
    }



    }

