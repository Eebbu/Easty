package com.example.eatsy;

import android.app.Application;

import com.example.eatsy.datamanagement.PostDataDownloader;
import com.example.eatsy.datamanagement.UserDataDownloader;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.CollectionReference;

/**
 * Application entry class, responsible for initializing Firebase and downloading necessary user and post data.
 * Called upon application creation, it sets up user and post data in the data manager.
 */

public class MyApplication extends Application {



    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(this);

        CollectionReference usersCollectionRef = FireStoreHelper.getCollectionRef("users");
        UserDataDownloader downloader = new UserDataDownloader();

        // Get the posts collection reference
        CollectionReference postsCollectionRef = FireStoreHelper.getCollectionRef("posts");
        PostDataDownloader postDownloader = new PostDataDownloader();

        // Call the asynchronous method, and handle the returned CompletableFuture
        downloader.downloadData(usersCollectionRef)
                .thenAccept(data ->  {

                    DataManager.getDataInstance().setUserHashMap(data); // store data to DataManager
                });
        // Download and handle post data
        postDownloader.downloadData(postsCollectionRef)
                .thenAccept(data -> { DataManager.getDataInstance().setPostHashMap(data); // Store to DataManager
                    // Success: post data received
                })
                .exceptionally(ex -> {
                    // Handle the case of post data download failure
                    System.err.println("Post data download failed: " + ex.getMessage());
                    return null;
                });
    }



    }

