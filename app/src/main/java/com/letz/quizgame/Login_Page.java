package com.letz.quizgame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login_Page extends AppCompatActivity
{

    EditText email;
    EditText password;
    Button SignIn;
    SignInButton SigninGoogle;
    TextView signUp;
    TextView forgotPassword;
    ProgressBar progressBar_Login;
    FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        email = findViewById(R.id.editTextEmailAddress);
        password = findViewById(R.id.editTextPassword);
        SignIn = findViewById(R.id.buttonSignin);
        SigninGoogle = findViewById(R.id.buttonGoogleSignin);
        signUp = findViewById(R.id.textViewSignUp);

        progressBar_Login = findViewById(R.id.progressBar_LoginPage);
        forgotPassword = findViewById(R.id.textViewForgot);

        SignIn.setOnClickListener(v -> {
            String userMail = email.getText().toString();
            String userPassword = password.getText().toString();
            signInWithFirebase(userMail, userPassword);
        });

        SigninGoogle.setOnClickListener(v -> {

        });

        signUp.setOnClickListener(v -> {
            Intent i = new Intent(Login_Page.this, Signup_Page.class);
            startActivity(i);
        });

        forgotPassword.setOnClickListener(v -> {

        });

    }

    public void signInWithFirebase(String userMail, String userPassword) {
        progressBar_Login.setVisibility(View.VISIBLE);
        SignIn.setClickable(false);
        // create FirebaseAuth on top
        auth.signInWithEmailAndPassword(userMail, userPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            Intent igoMain = new Intent(Login_Page.this,MainActivity.class);
                            startActivity(igoMain);
                            finish();
                            progressBar_Login.setVisibility(View.INVISIBLE);
                            Toast.makeText(Login_Page.this, "Signed in successfully!", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(Login_Page.this, "Sign In Failed", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }
}