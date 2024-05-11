package com.example.eatsy;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class FireStoreHelper {
    private static final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static  CollectionReference collectionRef = db.collection("your_collection_name");

    private FireStoreHelper() {
        // Private constructor to prevent external instantiation
    }

    public static FirebaseFirestore getFirestore() {
        return db;
    }

    public static CollectionReference getCollectionRef(String collectionName) {
        if (collectionRef == null || !collectionRef.getId().equals(collectionName)) {
            collectionRef = db.collection(collectionName);
        }
        return collectionRef;
    }
}
