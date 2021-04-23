package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;


public class splash extends AppCompatActivity {
    public static final String USER_NAME = "username";
    private Animation slideUp;
    private ImageView instagramLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);



        CountDownTimer count = new CountDownTimer(2000, 2000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                Intent intent;
                if (isLoggedIn()) {
                    intent = new Intent(splash.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    intent = new Intent(splash.this, login.class);
                    startActivity(intent);
                    finish();
                }
            }
        }.start();


    }

    private boolean isLoggedIn() {
        return false;
    }
}
