package com.example.eatsy;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncListUtil;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class UserDataDownloader {
    public void downloadData(CollectionReference usersCollectionRef, DataDownloadCallback<userFT> callback) {
        HashMap<String, userFT> userHashMap = new HashMap<>();
        usersCollectionRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (QueryDocumentSnapshot document : task.getResult()) {
                    String email = document.getId();
                    String username = document.getString("name");
                    String photoURL = document.getString("photo_url");
                    ArrayList<String> postid = (ArrayList<String>)document.get("postid");
                    userFT user = new userFT(username, email, photoURL, postid);
                    userHashMap.put(email, user);
                }
                callback.onDataReceived(userHashMap);
            } else {
                callback.onDataDownloadFailed(task.getException());
            }
        });
    }







}
