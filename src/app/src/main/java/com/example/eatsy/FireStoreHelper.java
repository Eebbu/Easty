package com.example.eatsy;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class FireStoreHelper {
    private static final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static  CollectionReference collectionRef = db.collection("your_collection_name");

    private FireStoreHelper() {
        // 私有构造函数，防止外部实例化
    }

    public static FirebaseFirestore getFireStore() {
        return db;
    }

    public static CollectionReference getCollectionRef(String collectionName) {
        if (collectionRef == null || !collectionRef.getId().equals(collectionName)) {
            collectionRef = db.collection(collectionName);
        }
        return collectionRef;
    }
}
