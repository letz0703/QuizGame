package com.letz.quizgame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Forgot_Password extends AppCompatActivity
{
    ImageView ivForgot;
    EditText etEmail;
    Button btnSendEmail;
    ProgressBar progressBar;

    FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        ivForgot = findViewById(R.id.ivForgot);
        etEmail = findViewById(R.id.etEmail_Forgot_Passoword);
        btnSendEmail = findViewById(R.id.btn_Forgot_Password);
        progressBar = findViewById(R.id.progressBar_Forgot_Password);

        btnSendEmail.setOnClickListener(v -> {

        });
    }

    public void resetPassword(String userEmail)
    {
        progressBar.setVisibility(View.VISIBLE);
        auth.sendPasswordResetEmail(userEmail)
        .addOnCompleteListener(new OnCompleteListener<Void>()
        {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
        });


    }

}