package com.example.eatsy;

import android.app.Application;

import com.example.eatsy.datamanagement.DataManager;
import com.example.eatsy.datamanagement.FireStoreHelper;
import com.example.eatsy.datamanagement.PostDataDownloader;
import com.example.eatsy.datamanagement.UserDataDownloader;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

/**
 * Application entry class, responsible for initializing Firebase and downloading necessary user and post data.
 * Called upon application creation, it sets up user and post data in the data manager.
 * @author  Boxuan Lin(u7705128)
 */

public class MyApplication extends Application {
    public static boolean fileDownloaded = false;
    private UserDataDownloader userDataDownloader;
    private PostDataDownloader  postDownloader;



    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(this);



        CollectionReference usersCollectionRef = FireStoreHelper.getCollectionRef("users");
    userDataDownloader = new UserDataDownloader(this);

    // Get the posts collection reference
    CollectionReference postsCollectionRef = FireStoreHelper.getCollectionRef("posts");
    postDownloader = new PostDataDownloader(this);


        // Start the asynchronous process
        userDataDownloader.downloadData(usersCollectionRef)
                .thenCompose(userData -> {
                    // Store user data to DataManager
                    DataManager.getDataInstance().setUserHashMap(userData);
                    // Print the user data to verify contents
                    System.out.println("Downloaded User Data:");
                    userData.forEach((key, value) -> System.out.println(key + " -> " + value));

                    // Proceed to download post data
                    return postDownloader.downloadData(postsCollectionRef);
                })
                .thenAccept(postData -> {
                    // Store post data to DataManager
                    DataManager.getDataInstance().setPostHashMap(postData);
                    System.out.println("Downloaded Post Data:");
                    postData.forEach((key, value) -> System.out.println(key + " -> " + value));

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


