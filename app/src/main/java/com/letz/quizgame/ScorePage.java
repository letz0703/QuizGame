package com.letz.quizgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ScorePage extends AppCompatActivity
{
    TextView scoreCorrect, scoreWrong;
    Button playAgain, exit;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference dbrefScore = database.getReference("score");

    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseUser user = auth.getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_page);

        scoreCorrect = findViewById(R.id.tvCorrect_ScorePage);
        scoreWrong = findViewById(R.id.tvWrong_ScorePage);

        playAgain = findViewById(R.id.btnPlayAgain_ScorePage);
        playAgain.setOnClickListener(v -> {

        });

        exit = findViewById(R.id.btnExit_ScorePage);
        exit.setOnClickListener(v -> {

        });

    }
}