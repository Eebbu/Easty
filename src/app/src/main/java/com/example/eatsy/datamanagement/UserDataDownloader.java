package com.example.eatsy.datamanagement;

import com.example.eatsy.datamanagement.DataDownloader;
import com.example.eatsy.userFT;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class UserDataDownloader  extends DataDownloader<userFT> {
    /**
     * Downloads user data from a specified FireStore collection and stores it in a ConcurrentHashMap.
     * Notifies the callback upon successful data retrieval or in case of a failure.
     */

    @Override
    protected void populateHashMapFromQuerySnapshot(QuerySnapshot snapshot, ConcurrentHashMap<String, userFT> userHashMap) {
        snapshot.forEach(document -> {
            String email = document.getId();
            String username = document.getString("name");
            String photoURL = document.getString("photo_url");
            ArrayList<String> postId = (ArrayList<String>) document.get("postid");
            userFT user = new userFT(username, email, email, photoURL, postId);
            userHashMap.put(email, user);
        });
    }
}


