package com.letz.quizgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.common.SignInButton;

public class Login_Page extends AppCompatActivity
{

    EditText email;
    EditText password;
    Button SignIn;
    SignInButton SigninGoogle;
    TextView signUp;
    TextView forgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        email = findViewById(R.id.editTextEmailAddress);
        password = findViewById(R.id.editTextPassword);
        SignIn = findViewById(R.id.buttonSignin);
        SigninGoogle = findViewById(R.id.buttonGoogleSignin);
        signUp = findViewById(R.id.textViewSignUp);
        forgotPassword = findViewById(R.id.textViewForgot);

        SignIn.setOnClickListener(v -> {

        });

        SigninGoogle.setOnClickListener(v -> {

        });

        signUp.setOnClickListener(v -> {
            Intent i = new Intent(Login_Page.this,Signup_Page.class);
            startActivity(i);
        });

        forgotPassword.setOnClickListener(v -> {

        });

    }
}