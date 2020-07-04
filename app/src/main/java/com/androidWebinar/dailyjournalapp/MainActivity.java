package com.androidWebinar.dailyjournalapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.androidWebinar.dailyjournalapp.Adapters.EntryListAdapter;
import com.androidWebinar.dailyjournalapp.models.model_entry;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private entry_controller entriesController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set Layout
        setContentView(R.layout.activity_main);

        // Find Views
        FloatingActionButton newJournalBtn = findViewById(R.id.btn_add_journal);

        // Initialize once
        entriesController = new entry_controller(this);

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

    @Override
    protected void onResume() {
        super.onResume();

        // This will load & set entries to list view every time the activity resumes
        loadAndSetEntries();
    }

    /**
     * This method loads entries from controller
     * then it creates adapter for ListView and sets that adapter to ListView
     */
    public void loadAndSetEntries() {
        ListView LV_entries = findViewById(R.id.entries_listView);
        List<model_entry> entries = entriesController.getEntries();
        EntryListAdapter entriesAdapter = new EntryListAdapter(this, entries);
        LV_entries.setAdapter(entriesAdapter);
    }
}