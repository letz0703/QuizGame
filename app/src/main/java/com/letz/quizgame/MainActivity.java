package com.letz.quizgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity
{
    TextView signOut;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    Button startGame;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startGame = findViewById(R.id.btnStart);

        startGame.setOnClickListener(v -> {
            Intent iStartGame = new Intent(MainActivity.this,QuizPage.class);
            startActivity(iStartGame);
            finish();
        });

        signOut = findViewById(R.id.tvSignOut);

        signOut.setOnClickListener(v -> {
            auth.signOut();
            Intent igoLogin_Page = new Intent(MainActivity.this, Login_Page.class);
            startActivity(igoLogin_Page);
            finish();
        });
    }
}