package com.example.eatsy.pages;


import static com.example.eatsy.R.id.back_btn;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.eatsy.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
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


    FirebaseUser user;


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

        user = FirebaseAuth.getInstance().getCurrentUser();

        // Setting the variables
        logOutBtn = findViewById(R.id.Signout);
        user_Name = findViewById(R.id.name_person);

        user_Email = findViewById(R.id.email_on_profile);
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
        // Creating a folder 'users' which contains profile page of different users.
        stRef = storageReference.child("users/" + firebaseAuth.getCurrentUser().getUid()+"/profile.jpg");
        stRef.getDownloadUrl().addOnSuccessListener(uri -> Picasso.get().load(uri).into(profileImage));


        userID = firebaseAuth.getCurrentUser().getUid();
        backButton = findViewById(back_btn);
        profileImage = findViewById(R.id.profile_pic);
        profileChange = findViewById(R.id.Change_profile_pic);

        // Displaying user data such as name and email on the profile page.

        if (user != null) {
            String emailOfPerson = FirebaseAuth.getInstance().getCurrentUser().getEmail();
            // Fetching the email from firebase and username from the hashmap using email as the key of the current user.
            String nameOfPerson = DashboardActivity.users.get(emailOfPerson).getUsername();
            user_Name.setText(nameOfPerson);
            user_Email.setText(emailOfPerson);

        }






// This onClickListener help the user to log out from the app and closes all the other activities running behind.

        logOutBtn.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            startActivity(intent);
            Toast.makeText(ProfileActivity.this,
                    "You have been signed out", Toast.LENGTH_SHORT).show();
        });

        // This back button help us to get back to dashboard.
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, DashboardActivity.class);
            startActivity(intent);
        });

        // This button helps the user to set ot change his/her profile picture.
        profileChange.setOnClickListener(v -> {
            Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
           startActivityForResult(galleryIntent, 1000);
        });




    }

    // This method fetching the data from the imageUri and uploading the image to the firebase.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1000) {
            if (resultCode == Activity.RESULT_OK){
                Uri imageUri = data.getData();
                uploadImageToFirebase(imageUri);
            }

        }
    }


    // This method is used to upload image to the firebase.
    private void uploadImageToFirebase(Uri imageUri) {
           // Upload image to firebase storage
       final StorageReference fileRef = storageReference.child("users/" + firebaseAuth.getCurrentUser().getUid()+"/profile.jpg");
        fileRef.putFile(imageUri).addOnSuccessListener(taskSnapshot -> fileRef.getDownloadUrl().addOnSuccessListener(uri -> Picasso.get().load(uri).into(profileImage))).addOnFailureListener(e -> Toast.makeText(ProfileActivity.this, "Upload failed", Toast.LENGTH_SHORT).show());
    }


}