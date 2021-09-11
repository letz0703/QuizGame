package com.letz.quizgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class Signup_Page extends AppCompatActivity
{

    EditText email;
    EditText password;
    Button btnSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);

        email = findViewById(R.id.etEmailSignUp);
        password = findViewById(R.id.etPasswordSignUp);
        btnSignup = findViewById(R.id.btnSignUp);

        btnSignup.setOnClickListener(v -> {

        });


    }
}