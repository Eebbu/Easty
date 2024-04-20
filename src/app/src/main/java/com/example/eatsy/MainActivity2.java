package com.example.eatsy;

import static android.app.ProgressDialog.show;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.example.eatsy.LoginUser;


public class MainActivity2 extends AppCompatActivity {

    EditText username, password;
    Button loginBtn;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        loginBtn = findViewById(R.id.login);
        username = findViewById(R.id.Email);
        password = findViewById(R.id.user_pass);
        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() != null)
            startActivity(new Intent(getApplicationContext(), MainActivity4.class));
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = username.getText().toString().trim();
                String pswrd = password.getText().toString().trim();
                if (email.isEmpty() && pswrd.isEmpty()) {
                    Toast.makeText(MainActivity2.this, "Please enter your credentials", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (email.isEmpty()) {
                    Toast.makeText(MainActivity2.this, "Please enter password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (pswrd.isEmpty()) {
                    Toast.makeText(MainActivity2.this, "Please enter email", Toast.LENGTH_SHORT).show();

                    return;
                }


                firebaseAuth.signInWithEmailAndPassword(email, pswrd)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {
                                    Toast.makeText(MainActivity2.this,
                                    "Login Successful", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(), MainActivity4.class));
                                } else {
                                    Toast.makeText(MainActivity2.this,
                                    "Authentication failed", Toast.LENGTH_SHORT).show();
                                    LoginUser.getInstance().error(MainActivity2.class, task.getException());
                                }
                            }
                        });




            }});}}





