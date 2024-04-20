package com.example.eatsy;

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
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class registerActivity extends AppCompatActivity {

    EditText mEmail , mPassWord, mFullName;
    Button mRegisterBtn;
    FirebaseAuth fAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        FirebaseApp.initializeApp(this);

        setContentView(R.layout.registerpage);

        mEmail = findViewById(R.id.Email);
        mFullName = findViewById(R.id.fullName);
        mPassWord = findViewById(R.id.pass_word);
        mRegisterBtn = findViewById(R.id.join_easty);

        fAuth = FirebaseAuth.getInstance();

        //if(fAuth.getCurrentUser() != null){
        //  startActivity(new Intent(getApplicationContext(),MainActivity.class));
        // finish();

        //}

        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString().trim();
                String password = mPassWord.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    mEmail.setError("Email is required!");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    mPassWord.setError("Password is required!");
                    return;
                }
                if (password.length() < 6) {
                    mPassWord.setError("Password must longer than 6 characters!");
                    return;
                }

                fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(registerActivity.this, "Successfully created!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            finish();
                        } else {
                            Toast.makeText(registerActivity.this, "Error! Please try again!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                });

            }
        });
    }}