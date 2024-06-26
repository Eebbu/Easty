package com.example.eatsy.datamanagement;

import android.net.Uri;

import com.example.eatsy.Post;
import com.example.eatsy.pages.DashboardActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
/**
 * FireStoreHelper is a utility class for interacting with Firebase Firestore and Firebase Storage.
 * It includes methods for posting data and managing Firestore collections.
 * @author : Boxuan Lin(u7705128) and Jinyang Zeng(u7727175)
 */
public class FireStoreHelper {
    private static final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static  CollectionReference collectionRef = db.collection("your_collection_name");
    static StorageReference storageReference = FirebaseStorage.getInstance().getReference();

    /**
     * Private constructor to prevent external instantiation.
     */
    private FireStoreHelper() {
        // Private constructor to prevent external instantiation
    }


    public static CollectionReference getCollectionRef(String collectionName) {
        if (collectionRef == null || !collectionRef.getId().equals(collectionName)) {
            collectionRef = db.collection(collectionName);
        }
        return collectionRef;
    }
    /**
     * Creates a new post and uploads the associated image to Firebase Storage.
     * @param post The post to be created and uploaded.
     */
    public static void createAndPost(Post post){
        if (post.getImages().isEmpty()){
            uploadPost(post);
            return;
        }
        StorageReference ref = storageReference.child("user_post_img/" + UUID.randomUUID().toString());
        UploadTask uploadTask = ref.putFile(Uri.parse(post.getFilePath()));

        uploadTask
                .addOnSuccessListener(taskSnapshot -> ref.getDownloadUrl().addOnSuccessListener(uri -> {
                    String imageUrl = uri.toString();
                    post.setImages(imageUrl);
                    System.out.println(post);
                    uploadPost(post);
                }));
    }
    /**
     * Uploads the post data to Firestore.
     * @param post The post data to be uploaded.
     */
    private static void uploadPost(Post post){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


        String userEmail = user.getEmail(); // Get current user's email address
        String newPostId = DataManager.generateTimestampBasedId(); // Generate ID based on timestamp



        Map<String, Object> postMap = new HashMap<>();
        postMap.put("userName", DashboardActivity.users.get(userEmail).getUsername());
        postMap.put("userID", userEmail);
        postMap.put("postType", post.getPostType());
        postMap.put("postTitle", post.getPostTitle());
        postMap.put("postDescription", post.getPostDescription());
        postMap.put("quantity", post.getQuantity());
        postMap.put("latitude", post.getLatitude());
        postMap.put("longitude", post.getLongitude());
        postMap.put("pickUpTimes", post.getPickUpTimes());
        postMap.put("imageURL", post.getImages());
        // Save post data to Firestore using the newly generated post ID
        db.collection("posts").document(newPostId).set(postMap)
            .addOnSuccessListener(aVoid -> {
                System.out.println("Post data successfully saved!");
                // Update user's post list
                DocumentReference userRef = db.collection("users").document(userEmail);
                userRef.update("postid", FieldValue.arrayUnion(newPostId))
                        .addOnSuccessListener(aVoid1 -> System.out.println("Post ID added to user profile"))
                        .addOnFailureListener(e -> System.err.println("Failed to add post ID to user profile: " + e.getMessage()));
            }).addOnFailureListener(e -> System.err.println("Error saving post data: " + e.getMessage()))
                .addOnFailureListener(e -> System.err.println("Error getting file URL: " + e.getMessage()))
            .addOnFailureListener(e -> System.err.println("Error uploading file: " + e.getMessage()));
    }
}
