package com.letz.quizgame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ScorePage extends AppCompatActivity
{
    TextView scoreCorrect, scoreWrong;
    Button playAgain, exit;
    String userCorrect;
    String userWrong;

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
            Intent i = new Intent(ScorePage.this, MainActivity.class);
            startActivity(i);
            finish();
        });

        exit = findViewById(R.id.btnExit_ScorePage);
        exit.setOnClickListener(v -> {
            finish();
        });

        dbrefScore.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                String userUID = user.getUid();
                userCorrect = snapshot.child(userUID).child("correct").getValue().toString();
                userWrong = snapshot.child(userUID).child("wrong").getValue().toString();
                scoreCorrect.setText(userCorrect);
                scoreWrong.setText(userWrong);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {

            }
        });

    }
}