package com.letz.quizgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Splash_Screen extends AppCompatActivity
{

    ImageView imageView;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        imageView = findViewById(R.id.ivSplash);
        title = findViewById(R.id.tvSplash);

        Animation animation = AnimationUtils.loadAnimation(
                getApplicationContext()
                , R.anim.ani_splash
        );

        title.startAnimation(animation);

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run() {
                Intent i = new Intent(Splash_Screen.this,Login_Page.class);
                startActivity(i);
                finish();
            }
        }, 5000);
    }
}