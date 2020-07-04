package com.androidWebinar.dailyjournalapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set Layout
        setContentView(R.layout.activity_main);

        // Find Views
        FloatingActionButton newJournalBtn = findViewById(R.id.btn_add_journal);

        // Add Listener to FAB
        newJournalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Launch the new Journal Activity using intent
                Intent i = new Intent(getApplicationContext(), NewJournalActivity.class);
                startActivity(i);
                Log.d("FAB_LOG", "Button Clicked");
            }
        });
    }
}