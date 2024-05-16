package com.example.eatsy.datamanagement;

import android.content.Context;

import com.example.eatsy.userFT;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
/**
 * @author : Boxuan Lin(u7705128)
 */
public class UserDataDownloader extends DataDownloader<userFT> {
    /**
     * Constructor for UserDataDownloader that takes context and class type.
     * @param context Context required for base class to access local resources.
     */
    public UserDataDownloader(Context context) {
        super(context, userFT.class);
    }

    /**
     * Downloads user data from a specified Firestore collection and stores it in a HashMap.
     * Notifies the callback upon successful data retrieval or in case of a failure.
     */
    @Override
    protected void populateHashMapFromQuerySnapshot(QuerySnapshot snapshot, HashMap<String, userFT> userHashMap) {
        snapshot.forEach(document -> {
            String email = document.getId();
            String username = document.getString("name");
            String photoURL = document.getString("photo_url");
            ArrayList<String> postId = document.contains("postid") ? (ArrayList<String>) document.get("postid") : new ArrayList<>();
            userFT user = new userFT(username, email,email, photoURL, postId);

            userHashMap.put(email, user);
        });
    }
}



