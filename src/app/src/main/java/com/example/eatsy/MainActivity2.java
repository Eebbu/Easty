package com.example.eatsy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.eatsy.LoginUser;
import com.google.firebase.Firebase;
public class MainActivity2 extends AppCompatActivity {

    EditText username , password;
    Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
         loginBtn = findViewById(R.id.login);
         username = findViewById(R.id.user_email);
         password = findViewById(R.id.user_pass);
         loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = username.getText().toString();
                String pswrd = password.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(MainActivity2.this, "Please enter email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(pswrd)) {
                    Toast.makeText(MainActivity2.this, "Please enter password", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
    }
}