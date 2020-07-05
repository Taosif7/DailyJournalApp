package com.androidWebinar.dailyjournalapp.Activities;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.androidWebinar.dailyjournalapp.Constants;
import com.androidWebinar.dailyjournalapp.R;
import com.androidWebinar.dailyjournalapp.entry_controller;
import com.androidWebinar.dailyjournalapp.models.model_entry;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;

public class NewJournalActivity extends AppCompatActivity {

    // Parameters
    public static final String PARAM_JOURNAL_ID = "JournalId";

    // Object to store selected date; By-default, its today's date
    Calendar selectedDate = Calendar.getInstance();

    // Entry controller
    entry_controller controller;

    // Settings
    boolean EDIT_MODE = false;
    int JournalId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_journal);

        // Find Views
        LinearLayout LL_entryDatePicker = findViewById(R.id.addEntry_date_picker);
        final TextView TV_entryDate = findViewById(R.id.addEntry_selected_date);
        final EditText ET_entryNote = findViewById(R.id.addEntry_note);
        final RadioGroup RG_entryEmoji = findViewById(R.id.addEntry_emoji);
        FloatingActionButton FAB_create = findViewById(R.id.addEntry_create);

        // Initialize Controller
        controller = new entry_controller(this);

        // Check if journal Id is provided, if So then its edit mode
        JournalId = getIntent().getIntExtra(PARAM_JOURNAL_ID, -1);
        EDIT_MODE = (JournalId != -1);

        // If this is edit mode, then
        if (EDIT_MODE) {
            setTitle(R.string.edit_journal_activity_title);

            model_entry entry = controller.getEntry(JournalId);
            ET_entryNote.setText(entry.note);
            selectedDate = entry.cal_date;

            // Iterate through all children of RadioGroup
            for (int i = 0; i < RG_entryEmoji.getChildCount(); i++) {

                // Get radio button at current index
                RadioButton btn = (RadioButton) RG_entryEmoji.getChildAt(i);

                // If radio button's text is equal to emoji
                // then get button's Id and set it selected in radioGroup
                // and break the loop
                if (btn.getText().equals(entry.emoji)) {
                    RG_entryEmoji.check(btn.getId());
                    break;
                }
            }
        }

        // Set selected date to today's date
        TV_entryDate.setText(String.format("%02d %s %04d", selectedDate.get(Calendar.DATE), Constants.month_names[selectedDate.get(Calendar.MONTH)], selectedDate.get(Calendar.YEAR)));

        // Show date picker on click of picker LinearLayout
        LL_entryDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Create dialog set action to set our selected_date object as selected date of picker
                // Also set the TextBiew tor reflect selected date
                DatePickerDialog picker = new DatePickerDialog(NewJournalActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                        selectedDate.set(y, m, d);
                        TV_entryDate.setText(String.format("%02d %s %04d", selectedDate.get(Calendar.DATE), Constants.month_names[selectedDate.get(Calendar.MONTH)], selectedDate.get(Calendar.YEAR)));
                    }
                }, selectedDate.get(Calendar.YEAR), selectedDate.get(Calendar.MONTH), selectedDate.get(Calendar.DATE));

                // Set max date to be today : not allowing future date entry
                picker.getDatePicker().setMaxDate(Calendar.getInstance().getTimeInMillis());

                // Show picker
                picker.show();
            }
        });

        // Perform adding of entry on click of fab
        FAB_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Find selected radiobutton
                RadioButton selectedRadioBtn = findViewById(RG_entryEmoji.getCheckedRadioButtonId());

                // Get values
                String note = ET_entryNote.getText().toString();
                String emoji = selectedRadioBtn.getText().toString();

                // Create Database Entry
                model_entry entry = new model_entry(JournalId, note, emoji, selectedDate, Calendar.getInstance());

                if (EDIT_MODE) {
                    // Edit entry & Show message
                    controller.updateEntry(entry);
                    Toast.makeText(NewJournalActivity.this, "Journal Updated!", Toast.LENGTH_SHORT).show();
                } else {
                    // Add entry & Show message
                    controller.addEntry(entry);
                    Toast.makeText(NewJournalActivity.this, "Journal Added!", Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });
    }
}