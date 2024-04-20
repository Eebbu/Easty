package com.example.eatsy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    EditText username , password;
    Button loginBtn;
   // FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
         loginBtn = findViewById(R.id.login);
         username = findViewById(R.id.Email);
         password = findViewById(R.id.user_pass);
       //  firebaseAuth = FirebaseAuth.getInstance;

         loginBtn.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             String email = username.getText().toString();
                                             String pswrd = password.getText().toString();

                                             if (TextUtils.isEmpty(email) && TextUtils.isEmpty(pswrd)) {
                                                 Toast.makeText(MainActivity2.this, "Please enter your credentials", Toast.LENGTH_SHORT).show();
                                                 return;
                                             }

                                             if (TextUtils.isEmpty(pswrd)) {
                                                 Toast.makeText(MainActivity2.this, "Please enter password", Toast.LENGTH_SHORT).show();
                                                 return;
                                             }

                                             if (TextUtils.isEmpty(email)) {
                                                 Toast.makeText(MainActivity2.this, "Please enter email", Toast.LENGTH_SHORT).show();
                                                 return;
                                             }

                                             if (email.equals("comp2100@anu.edu.au") && pswrd.equals("comp2100")) {
                                                 Intent intent = new Intent(MainActivity2.this, registerActivity.class);

                                                 startActivity(intent);
                                                 Toast.makeText(MainActivity2.this, "Logged in", Toast.LENGTH_SHORT).show();
                                             }

                                             if (email.equals("comp6442@anu.edu.au") && pswrd.equals("comp6442")) {
                                                 Intent intent = new Intent(MainActivity2.this, registerActivity.class);

                                                 startActivity(intent);
                                                 Toast.makeText(MainActivity2.this, "Logged in", Toast.LENGTH_SHORT).show();
                                             }

                                         }

                                     });
    }

}

//                firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            Toast.makeText(MainActivity2.this, "Login Successful", Toast.LENGTH_SHORT).show();
//                            startActivity(new Intent(getApplicationContext(), MainActivity4.class));
//                        } else {
//                            Toast.makeText(MainActivity2.this, "Authentication failed" + task.getException().getMessage, Toast.LENGTH_SHORT).show();
//                        }
//
//                    }
//                });
//
//            }
//
//
//
//            }
//        });
//
//    }
//}