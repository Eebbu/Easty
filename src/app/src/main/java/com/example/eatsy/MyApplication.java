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

    // Call the asynchronous method, and handle the returned CompletableFuture
        userDataDownloader.downloadData(usersCollectionRef)
            .thenAccept(data ->  {

        DataManager.getDataInstance().setUserHashMap(data); // store data to DataManager
        // Print the user data to verify contents
        System.out.println("Downloaded User Data:");
        data.forEach((key, value) -> System.out.println(key + " -> " + value));

    });
    // Download and handle post data
        postDownloader.downloadData(postsCollectionRef)
            .thenAccept(data -> {
                System.out.println("Downloaded Post Data:");
                DataManager.getDataInstance().setPostHashMap(data); // Store to DataManager
        data.forEach((key, value) -> System.out.println(key + " -> " + value));
        // Success: post data received
    })
            .exceptionally(ex -> {
        // Handle the case of post data download failure
        System.err.println("Post data download failed: " + ex.getMessage());

        return null;
    });
}
}

