package com.letz.quizgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class QuizPage extends AppCompatActivity
{
    TextView time, correct, wrong;
    TextView question, a, b, c, d;
    Button next, finish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_page);

        time = findViewById(R.id.tvTime_QuizPage);
        correct = findViewById(R.id.tvCorrect_QuizPage);
        wrong = findViewById(R.id.tvWrong_QuizPage);
        question = findViewById(R.id.tvQuestion);
        a = findViewById(R.id.tvAnswer1_QuizPage);
        b = findViewById(R.id.tvAnswer2_QuizPage);
        c = findViewById(R.id.tvAnswer3_QuizPage);
        d = findViewById(R.id.tvAnswer4_QuizPage);
        next = findViewById(R.id.btnNext_QuizPage);
        finish = findViewById(R.id.btnFinish_QuizPage);

    }
}