package com.example.eatsy;

import android.app.Application;
import android.util.Log;

import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MyApplication extends Application {
    private FirebaseFirestore db;
    private CollectionReference usersCollectionRef;
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(this);

        db = FirestoreHelper.getFirestore();
        usersCollectionRef = FirestoreHelper.getCollectionRef("users");
        UserDataDownloader downloader = new UserDataDownloader();
        downloader.downloadData(usersCollectionRef, new DataDownloadCallback<userFT>() {
            @Override
            public void onDataReceived(HashMap<String, userFT> data) {
                for (Map.Entry<String, userFT> entry : data.entrySet()) {
                    String email = entry.getKey();
                    userFT user = entry.getValue();


                    // printing the data(only for testing perpose)
                    System.out.println("Email: " + email);
                    System.out.println("Username: " + user.getUsername());
                    System.out.println("Post IDs: " + user.getPostID());
                    System.out.println("------------------------");
                }
            }

            @Override
            public void onDataDownloadFailed(Exception exception) {
                // dealing with downloading fail case.
                Log.e("DataDownloader", "download fail...", exception);
            }
        });


    }
}
