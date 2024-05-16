package com.example.eatsy.pages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.eatsy.MyApplication;
import com.example.eatsy.datamanagement.LocalJsonDataBase;
import com.example.eatsy.R;
import com.example.eatsy.searchengine.StorageList;


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





        // Login page that takes us to the login page.
        logIn.setOnClickListener(v -> {
            while(MyApplication.fileDownloaded = false){
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        });


    }


}