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

                    DataManager.getDataInstance().setUserHashMap(data); // store data to DataManager
                    System.out.println("Downloaded Post Data:");
                    data.forEach((key, value) -> System.out.println("Key: " + key + ", Value: " + value));

                });
        // Download and handle post data
        postDownloader.downloadData(postsCollectionRef)
                .thenAccept(data -> { DataManager.getDataInstance().setPostHashMap(data); // 存储到 DataManager
                    // Success: post data received
                })
                .exceptionally(ex -> {
                    // Handle the case of post data download failure
                    System.err.println("Post data download failed: " + ex.getMessage());
                    return null;
                });
    }



    }

