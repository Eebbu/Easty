package com.example.eatsy;

import static com.example.eatsy.R.id.Signout;
import static com.example.eatsy.R.id.back_btn;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

/**Functionality
 * This is the profile activity that has function of setting and changing the profile picture,
 * displaying name and email of the user and a sign out button that helps the user to sign out from the app.
 * @author Vishakha Mathur(u7663368)
 */

public class ProfileActivity extends AppCompatActivity {

    TextView user_Name, user_Email;

    FirebaseAuth firebaseAuth;

    FirebaseFirestore firebaseFirestore;
    ImageView profileImage;
    Button profileChange;
    StorageReference storageReference;
    StorageReference stRef;


    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


    String userID;
    Button logOutBtn;
    ImageView backButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



// Setting the variables
        logOutBtn = findViewById(R.id.Signout);
        user_Name = findViewById(R.id.name_person);

        user_Email = findViewById(R.id.email_on_profile);
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
        stRef = storageReference.child("users/" + firebaseAuth.getCurrentUser().getUid()+"/profile.jpg");
        stRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(profileImage);
            }
        });


        userID = firebaseAuth.getCurrentUser().getUid();
        backButton = findViewById(back_btn);
        profileImage = findViewById(R.id.profile_pic);
        profileChange = findViewById(R.id.Change_profile_pic);

        // Displaying user data such as name and email on the profile page.

        if (user != null) {
            String emailOfPerson = FirebaseAuth.getInstance().getCurrentUser().getEmail();
            String nameOfPerson = DashboardActivity.users.get(emailOfPerson).getUsername();
            user_Name.setText(nameOfPerson);
            user_Email.setText(emailOfPerson);


//            firebaseFirestore.collection("users").document(user.getUid()).get().addOnCompleteListener(task -> {
//                if (task.isSuccessful()) {
//                    DocumentSnapshot ds = task.getResult();
//                    if (ds.exists()) {
//                        String nameOfPerson = ds.getString("name");
//                        String emailOfPerson = ds.getString("email");
//                        Log.d("TAG", "Success!!!!!!!");
//                        userName.setText(nameOfPerson);
//                        userEmail.setText(emailOfPerson);
//                    } else {
//                        Log.d("TAG", "No such document");
//                    }
//                }
//
//                    else {
//                        Log.d("TAG", "get failed with", task.getException());
//                    }
//
//            });
        }

//        DocumentReference documentReference = firebaseFirestore.collection("users").document(userID);
//        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
//            @Override
//            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
//                userName.setText(value.getString("name"));
//                userEmail.setText(value.getString("email"));
//            }
//        });




// This onClickListener help the user to log out from the app and closes all the other activities running behind.

        logOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                startActivity(intent);
                Toast.makeText(ProfileActivity.this,
                        "You have been signed out", Toast.LENGTH_SHORT).show();
            }
        });

        // This back button help us to get back to dashboard.
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, DashboardActivity.class);
                startActivity(intent);
            }
        });

        // This button helps the user to set ot change his/her profile picture.
        profileChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
               startActivityForResult(galleryIntent, 1000);
            }
        });




    }

    // This method fetching the data from the imageUri and uploading the image to the firebase.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1000) {
            if (resultCode == Activity.RESULT_OK){
                Uri imageUri = data.getData();
              //  profileImage.setImageURI(imageUri);
                uploadImageToFirebase(imageUri);
            }

        }
    }


    // This method is used to upload image to the firebase.
    private void uploadImageToFirebase(Uri imageUri) {
           // Upload image to firebase storage
       final StorageReference fileRef = storageReference.child("users/" + firebaseAuth.getCurrentUser().getUid()+"/profile.jpg");
        fileRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
            fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    Picasso.get().load(uri).into(profileImage);
                }
            });

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ProfileActivity.this, "Upload failed", Toast.LENGTH_SHORT).show();
            }
        });
    }


}