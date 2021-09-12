package com.letz.quizgame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signup_Page extends AppCompatActivity
{

    EditText email;
    EditText password;
    Button btnSignup;
    ProgressBar progressBar;

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);

        email = findViewById(R.id.etEmailSignUp);
        password = findViewById(R.id.etPasswordSignUp);
        btnSignup = findViewById(R.id.btnSignUp);
        progressBar = findViewById(R.id.progressBar);

        progressBar.setVisibility(View.INVISIBLE);

        btnSignup.setOnClickListener(v -> {
            btnSignup.setClickable(false);
            String userEmail = email.getText().toString();
            String userPassword = password.getText().toString();
            signUpFirebase(userEmail, userPassword);
        });
    }

    public void signUpFirebase(String userEmail, String userPassword)
    {
        progressBar.setVisibility(View.VISIBLE);
        firebaseAuth.createUserWithEmailAndPassword(userEmail, userPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(Signup_Page.this, "Your Account is created", Toast.LENGTH_SHORT).show();
                            finish();
                            progressBar.setVisibility(View.INVISIBLE);
                        }
                        else
                        {
                            Toast.makeText(Signup_Page.this, "There is a problem. Try again later", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}