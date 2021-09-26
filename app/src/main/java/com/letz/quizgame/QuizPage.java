package com.letz.quizgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class QuizPage extends AppCompatActivity
{
    TextView time, correct, wrong;
    TextView question, a, b, c, d;
    Button next, finish;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference dbrefQuestions = database.getReference().child("Qustions");

    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseUser user = auth.getCurrentUser();
    DatabaseReference dbref = database.getReference();
//    .child("score").child("userUID").child("countUserCorrect");

    String quizQuestion;
    String quizAnswerA;
    String quizAnswerB;
    String quizAnswerC;
    String quizAnswerD;
    String dbAnswer;
    int questionCount;
    int questionNumber = 1;

    String userAnswer;
    int countUserCorrect = 0;
    int countUserWrong = 0;

    CountDownTimer countDownTimer;
    private static final long TOTAL_TIME = 25000;
    Boolean timerContinue;
    long timeLeft = TOTAL_TIME;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_page);

        time = findViewById(R.id.tvTime_QuizPage);
        correct = findViewById(R.id.tvCorrect_QuizPage);
        wrong = findViewById(R.id.tvWrong_QuizPage);
        question = findViewById(R.id.quizQuestion);
        a = findViewById(R.id.quizAnswerA);
        b = findViewById(R.id.quizAnswerB);
        c = findViewById(R.id.quizAnswerC);
        d = findViewById(R.id.quizAnswerD);
        next = findViewById(R.id.btnNext_QuizPage);
        finish = findViewById(R.id.btnFinish_QuizPage);

        game();

        next.setOnClickListener(v -> {
            resetTimer();
            game();
        });

        finish.setOnClickListener(v -> {
            sendScore();
            Intent iSore_Page = new Intent(QuizPage.this,Score_Page.class);
            startActivity(iSore_Page);
            finish();
            
        });

        a.setOnClickListener(v -> {
            pauseTimer();
            userAnswer = "a";
            if (dbAnswer.equals(userAnswer))
            {
                a.setBackgroundColor(Color.GREEN);
                countUserCorrect++;
                correct.setText("" + countUserCorrect);
            }
            else
            {
                a.setBackgroundColor(Color.RED);
                Toast.makeText(QuizPage.this, "Wrong!!!", Toast.LENGTH_SHORT).show();
                countUserWrong++;
                wrong.setText("" + countUserWrong);
                markCorrectAnswer();
            }
        });
        b.setOnClickListener(v -> {
            pauseTimer();
            userAnswer = "b";
            if (dbAnswer.equals(userAnswer))
            {
                b.setBackgroundColor(Color.GREEN);
                countUserCorrect++;
                correct.setText("" + countUserCorrect);
            }
            else
            {
                b.setBackgroundColor(Color.RED);
                Toast.makeText(QuizPage.this, "Wrong!!!", Toast.LENGTH_SHORT).show();
                countUserWrong++;
                wrong.setText("" + countUserWrong);
                markCorrectAnswer();
            }
        });
        c.setOnClickListener(v -> {
            pauseTimer();
            userAnswer = "c";
            if (dbAnswer.equals(userAnswer))
            {
                c.setBackgroundColor(Color.GREEN);
                countUserCorrect++;
                correct.setText("" + countUserCorrect);

            }
            else
            {
                c.setBackgroundColor(Color.RED);
                Toast.makeText(QuizPage.this, "Wrong!!!", Toast.LENGTH_SHORT).show();
                countUserWrong++;
                wrong.setText("" + countUserWrong);
                markCorrectAnswer();
            }
        });
        d.setOnClickListener(v -> {
            pauseTimer();
            userAnswer = "d";
            if (dbAnswer.equals(userAnswer))
            {
                d.setBackgroundColor(Color.GREEN);
                countUserCorrect++;
                correct.setText("" + countUserCorrect);
            }
            else
            {
                d.setBackgroundColor(Color.RED);
                Toast.makeText(QuizPage.this, "Wrong!!!", Toast.LENGTH_SHORT).show();
                countUserWrong++;
                wrong.setText("" + countUserWrong);
                markCorrectAnswer();
            }
        });
    }

    public void game()
    {
        startTimer();
        a.setBackgroundColor(Color.WHITE);
        b.setBackgroundColor(Color.WHITE);
        c.setBackgroundColor(Color.WHITE);
        d.setBackgroundColor(Color.WHITE);
        // Read from the database
        dbrefQuestions.addValueEventListener(new ValueEventListener()
        {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
//                String value = dataSnapshot.getValue(String.class);
                questionCount = (int) dataSnapshot.getChildrenCount();

                quizQuestion = dataSnapshot.child(String.valueOf(questionNumber)).child("q").getValue().toString();
                quizAnswerA = dataSnapshot.child(String.valueOf(questionNumber)).child("a").getValue().toString();
                quizAnswerB = dataSnapshot.child(String.valueOf(questionNumber)).child("b").getValue().toString();
                quizAnswerC = dataSnapshot.child(String.valueOf(questionNumber)).child("c").getValue().toString();
                quizAnswerD = dataSnapshot.child(String.valueOf(questionNumber)).child("d").getValue().toString();
                dbAnswer = dataSnapshot.child(String.valueOf(questionNumber)).child("answer").getValue().toString();

                question.setText(quizQuestion);
                a.setText(quizAnswerA);
                b.setText(quizAnswerB);
                c.setText(quizAnswerC);
                d.setText(quizAnswerD);

                if (questionNumber < questionCount)
                {
                    questionNumber++;
                }
                else
                {
                    Toast.makeText(QuizPage.this, "Finished", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError error)
            {
                // Failed to read value
                Toast.makeText(QuizPage.this, "There is a problem", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void markCorrectAnswer()
    {
        if (dbAnswer.equals("a"))
        {
            a.setBackgroundColor(Color.GREEN);
        }
        else if (dbAnswer.equals("b"))
        {
            b.setBackgroundColor(Color.GREEN);
        }
        else if (dbAnswer.equals("c"))
        {
            c.setBackgroundColor(Color.GREEN);
        }
        else if (dbAnswer.equals("d"))
        {
            d.setBackgroundColor(Color.GREEN);
        }
    }

    public void startTimer()
    {
        countDownTimer = new CountDownTimer(timeLeft, 1000)
        {
            @Override
            public void onTick(long millisUntilFinished)
            {
                timeLeft = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish()
            {
                timerContinue = false;
                pauseTimer();
                question.setBackgroundColor(Color.RED);
                question.setText("Sorry. Time is up");
                wrong.setText("" + countUserWrong);
            }
        }.start();

        timerContinue = true;
    }

    public void resetTimer()
    {
        timeLeft = TOTAL_TIME;
        updateCountDownText();
    }

    public void updateCountDownText()
    {
        int second = (int) (timeLeft / 1000) & 60;
        time.setText("" + second);
    }

    public void pauseTimer()
    {
        countDownTimer.cancel();
        timerContinue = false;
    }

    public void sendScore()
    {
        String userUID = user.getUid();
        dbref.child("score").child(userUID).child("correct").setValue(countUserCorrect)
        .addOnSuccessListener(new OnSuccessListener<Void>()
        {
            @Override
            public void onSuccess(Void unused)
            {
                Toast.makeText(QuizPage.this, "sores sent successfully", Toast.LENGTH_SHORT).show();
            }
        });
        dbref.child("score").child(userUID).child("wrong").setValue(countUserWrong);
    }
}