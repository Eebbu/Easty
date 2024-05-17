package com.example.eatsy;

import android.app.Application;

import com.example.eatsy.datamanagement.DataManager;
import com.example.eatsy.datamanagement.FireStoreHelper;
import com.example.eatsy.datamanagement.PostDataDownloader;
import com.example.eatsy.datamanagement.UserDataDownloader;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.CollectionReference;

import java.util.concurrent.CompletableFuture;

/**
 * Application entry class, responsible for initializing Firebase and downloading necessary user and post data.
 * Called upon application creation, it sets up user and post data in the data manager.
 * Author: Boxuan Lin(u7705128)
 */
public class MyApplication extends Application {
    public static boolean fileDownloaded = false;
    private UserDataDownloader userDataDownloader;
    private PostDataDownloader postDownloader;

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(this);

        CollectionReference usersCollectionRef = FireStoreHelper.getCollectionRef("users");
        userDataDownloader = new UserDataDownloader(this);

        CollectionReference postsCollectionRef = FireStoreHelper.getCollectionRef("posts");
        postDownloader = new PostDataDownloader(this);

        // Start the asynchronous process
        CompletableFuture<Void> postDataFuture = postDownloader.downloadData(postsCollectionRef)
                .thenAccept(postData -> {
                    // Store post data to DataManager
                    DataManager.getDataInstance().setPostHashMap(postData);

                });

        CompletableFuture<Void> userDataFuture = postDataFuture.thenCompose(v -> userDataDownloader.downloadData(usersCollectionRef))
                .thenAccept(userData -> {
                    // Store user data to DataManager
                    DataManager.getDataInstance().setUserHashMap(userData);

                });

        // Ensure both post and user data are downloaded before setting fileDownloaded to true
        CompletableFuture.allOf(postDataFuture, userDataFuture)
                .thenRun(() -> {
                    // Success: both user and post data received
                    fileDownloaded = true;
                })
                .exceptionally(ex -> {
                    // Handle any errors that occurred during the download process
                    System.err.println("Data download failed: " + ex.getMessage());
                    return null;
                });
    }
}



