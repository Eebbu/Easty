package com.example.eatsy;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;


/**Functionalities
 * This activity allows the user to login using username and password implementing singleton pattern and firebase authorization.
 *@author Vishakha Mathur(u7663368)
 */
public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    Button loginBtn;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginBtn = findViewById(R.id.login);
        username = findViewById(R.id.Email);
        password = findViewById(R.id.user_pass);
        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() != null)
            startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
        loginBtn.setOnClickListener(v -> {
            String email = username.getText().toString().trim();
            String pswrd = password.getText().toString().trim();
            if (email.isEmpty() && pswrd.isEmpty()) {
                Toast.makeText(LoginActivity.this, "Please enter your credentials", Toast.LENGTH_SHORT).show();
                return;
            }

            if (email.isEmpty()) {
                Toast.makeText(LoginActivity.this, "Please enter password", Toast.LENGTH_SHORT).show();
                return;
            }
            if (pswrd.isEmpty()) {
                Toast.makeText(LoginActivity.this, "Please enter email", Toast.LENGTH_SHORT).show();

                return;
            }


            firebaseAuth.signInWithEmailAndPassword(email, pswrd)
                    .addOnCompleteListener(task -> {

                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this,
                            "Login Successful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                            intent.putExtra("USERNAME", email);
                            startActivity(intent);
                            startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
                        } else {
                            Toast.makeText(LoginActivity.this,
                            "Authentication failed", Toast.LENGTH_SHORT).show();
                            LoginUser.getInstance().error(LoginActivity.class, task.getException());
                        }
                    });




        });}}





