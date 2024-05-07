package com.example.eatsy;

import android.net.Uri;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
/**
 * @author Zihan Yuan(u7773880) Boxuan Lin(u7705128)
 */
public class factory_donate extends Post{
    // Constructor for factory_donate
    factory_donate(String userName,String postTitle,String postDescription, String quantity,
                   String pickUpTimes,String latitude,String longitude, String image,Uri filePath,
                   StorageReference storageReference){
        // Call to the superclass (Post) constructor
        super(userName,"donate",postTitle,postDescription,quantity,latitude,longitude);
        this.addImages(image);// Add image to the post
        this.setPickUpTimes(pickUpTimes); // Set pick-up times for the donation
        this.setFilePath(filePath);// Set the file path for the image
        this.setStorageReference(storageReference);// Set the storage reference for Firebase
    }


    @Override
    public void saveToFirebase() {
        //.....
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();  // 获取当前用户
        FirebaseFirestore db = FirebaseFirestore.getInstance(); // 获取 Firestore 实例
        Uri filePath = this.getFilePath();
        StorageReference storageReference = getStorageReference();
        if (filePath != null) {
            String userEmail = user.getEmail(); // 获取当前用户的邮箱地址
            String newPostId = DataManager.generateTimestampBasedId(); //生成基于时间戳的ID
            storageReference.putFile(filePath).addOnSuccessListener(taskSnapshot -> {
                taskSnapshot.getStorage().getDownloadUrl().addOnSuccessListener(uri -> {
                    Map<String, Object> postMap = new HashMap<>();
                    postMap.put("userName", this.getUserName());
                    postMap.put("userEmail", userEmail);
                    postMap.put("postType", this.getPostType());
                    postMap.put("postTitle", this.getPostTitle());
                    postMap.put("postDescription", this.getPostDescription());
                    postMap.put("quantity", this.getQuantity());
                    postMap.put("latitude", this.getLatitude());
                    postMap.put("longitude", this.getLongitude());
                    postMap.put("pickUpTimes", this.getPickUpTimes());
                    postMap.put("imageURL", uri.toString());
                    // 保存帖子数据到 Firestore 并使用新生成的 post ID
                    db.collection("posts").document(newPostId).set(postMap)
                            .addOnSuccessListener(aVoid -> {
                                System.out.println("Post data successfully saved!");
                                // 更新用户的帖子列表
                                DocumentReference userRef = db.collection("users").document(userEmail);
                                userRef.update("postid", FieldValue.arrayUnion(newPostId))
                                        .addOnSuccessListener(aVoid1 -> System.out.println("Post ID added to user profile"))
                                        .addOnFailureListener(e -> System.err.println("Failed to add post ID to user profile: " + e.getMessage()));
                            }).addOnFailureListener(e -> {
                                System.err.println("Error saving post data: " + e.getMessage());
                            });
                }).addOnFailureListener(e -> {
                    System.err.println("Error getting file URL: " + e.getMessage());
                });
            }).addOnFailureListener(e -> {
                System.err.println("Error uploading file: " + e.getMessage());
            });
        } else {
            System.err.println("File path is null or user is not logged in");
        }
    }
            }


