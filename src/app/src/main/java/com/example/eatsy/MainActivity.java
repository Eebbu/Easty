package com.example.eatsy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;


/** Functionality
 * This is the main page which appears when user opens the app.
 * It has title of the app and a login button that takes us to the login page.
 * Loading data from firebase
 * @author Vishakha Mathur(u7663368) and Lin Xi(u7777752)
 */
public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button logIn = findViewById(R.id.start);



        //init post data firebase
        //StorageList.initPostData();

        //local data
        StorageList.mapList = ImportDataFromLocalJson.read(getApplicationContext());
        StorageList.initLocalData();

        // Login page that takes us to the login page.
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        });


    }


}