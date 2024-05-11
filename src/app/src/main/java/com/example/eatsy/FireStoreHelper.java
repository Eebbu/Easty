package com.example.eatsy;

import android.net.Uri;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

public class FireStoreHelper {
    private static final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static  CollectionReference collectionRef = db.collection("your_collection_name");
    static StorageReference storageReference = FirebaseStorage.getInstance().getReference();

    private FireStoreHelper() {
        // Private constructor to prevent external instantiation
    }


    public static CollectionReference getCollectionRef(String collectionName) {
        if (collectionRef == null || !collectionRef.getId().equals(collectionName)) {
            collectionRef = db.collection(collectionName);
        }
        return collectionRef;
    }

    public static void createAndPost(Post post){
        StorageReference ref = storageReference.child("user_post_img/" + UUID.randomUUID().toString());
        UploadTask uploadTask = ref.putFile(Uri.parse(post.getFilePath()));

        uploadTask
                .addOnSuccessListener(taskSnapshot -> {
                    // 上传成功，获取图片的下载地址
                    ref.getDownloadUrl().addOnSuccessListener(uri -> {
                        // 获取下载地址成功，创建 Post 对象并上传到 Firebase Realtime Database
                        String imageUrl = uri.toString();
                        post.setImages(imageUrl);
                        uploadPost(post);
                    });
                });
    }

    public static void uploadPost(Post post){

    }



        //1.upload image and get download url;
//        post.setImages(String downloadUrl);
        //2.upload post with the url；

}
