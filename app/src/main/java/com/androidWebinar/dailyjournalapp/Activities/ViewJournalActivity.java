package com.androidWebinar.dailyjournalapp.Activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.androidWebinar.dailyjournalapp.Constants;
import com.androidWebinar.dailyjournalapp.R;
import com.androidWebinar.dailyjournalapp.entry_controller;
import com.androidWebinar.dailyjournalapp.models.model_entry;

import java.util.Calendar;

public class ViewJournalActivity extends AppCompatActivity {

    public static final String PARAM_ID = "ID";

    entry_controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_journal);

        // Get Parameter, if not found -> finish activity
        int Id = getIntent().getIntExtra(PARAM_ID, -1);
        if (Id == -1) finish();

        // Initialize
        controller = new entry_controller(this);

        // load entry
        model_entry journal = controller.getEntry(Id);

        // Find views
        TextView TV_title = findViewById(R.id.viewJournal_title);
        TextView TV_body = findViewById(R.id.viewJournal_body);
        TextView TV_modified = findViewById(R.id.viewJournal_modified);

        TV_title.setText(
                String.format("%s %02d %s %04d",
                        journal.emoji,
                        journal.cal_date.get(Calendar.DAY_OF_MONTH),
                        Constants.month_names[journal.cal_date.get(Calendar.MONTH)],
                        journal.cal_date.get(Calendar.YEAR
                        )));
        TV_body.setText(journal.note);
        TV_modified.setText(
                String.format("Modified on %02d %s %04d %02d:%02d",
                        journal.cal_modified.get(Calendar.DAY_OF_MONTH),
                        Constants.month_names[journal.cal_modified.get(Calendar.MONTH)],
                        journal.cal_modified.get(Calendar.YEAR),
                        journal.cal_modified.get(Calendar.HOUR_OF_DAY),
                        journal.cal_modified.get(Calendar.MINUTE)
                ));

    }
}