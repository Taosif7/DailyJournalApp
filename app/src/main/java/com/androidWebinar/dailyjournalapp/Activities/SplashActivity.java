package com.androidWebinar.dailyjournalapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.androidWebinar.dailyjournalapp.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Wait 3 seconds
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                // Goto MainActivity
                Intent req = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(req);

                // Finish this activity
                finish();
            }
        }, 3000);
    }
}