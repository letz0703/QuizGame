package com.letz.quizgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
    DatabaseReference databaseReference = database.getReference().child("Questions");

    String quizQuestion;
    String quizAnswerA;
    String quizAnswerB;
    String quizAnswerC;
    String quizAnswerD;
    String tvCorrectAnswer;
    int questionCount;
    int questionNumber = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
            game();
        });
        
        finish.setOnClickListener(v -> {
            
        });
        

    }
    public void game()
    {
        // Read from the database
        databaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
//                String value = dataSnapshot.getValue(String.class);
                questionCount = (int) dataSnapshot.getChildrenCount();
                
                quizQuestion = dataSnapshot.child(String.valueOf(questionNumber)).child("q").getValue().toString();
                quizAnswerA = dataSnapshot.child(String.valueOf(questionNumber)).child("a").getValue().toString();
                quizAnswerB = dataSnapshot.child(String.valueOf(questionNumber)).child("b").getValue().toString();
                quizAnswerC= dataSnapshot.child(String.valueOf(questionNumber)).child("c").getValue().toString();
                quizAnswerD = dataSnapshot.child(String.valueOf(questionNumber)).child("d").getValue().toString();
                tvCorrectAnswer = dataSnapshot.child(String.valueOf(questionNumber)).child("answer").getValue().toString();

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
                    Toast.makeText(QuizPage.this, "You answered all questions", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Toast.makeText(QuizPage.this, "There is a problem", Toast.LENGTH_SHORT).show();
            }
        });
    }
}