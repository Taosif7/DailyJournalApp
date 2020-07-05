package com.androidWebinar.dailyjournalapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.androidWebinar.dailyjournalapp.Activities.ViewJournalActivity;
import com.androidWebinar.dailyjournalapp.Constants;
import com.androidWebinar.dailyjournalapp.R;
import com.androidWebinar.dailyjournalapp.models.model_entry;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class EntryListAdapter extends BaseAdapter {

    List<model_entry> entries = new ArrayList<>();
    LayoutInflater inflater;
    Context context;

    public EntryListAdapter(Context c, List<model_entry> entry) {
        this.context = c;
        entries.addAll(entry);
        inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return entries.size();
    }

    @Override
    public model_entry getItem(int i) {
        return entries.get(i);
    }

    @Override
    public long getItemId(int i) {
        return entries.get(i).id;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final model_entry entry = entries.get(i);
        view = inflater.inflate(R.layout.entry_list_item, null);

        // Find views
        TextView TV_date = view.findViewById(R.id.entryItem_date);
        TextView TV_monthYear = view.findViewById(R.id.entryItem_monthYear);
        TextView TV_note = view.findViewById(R.id.entryItem_note);
        TextView TV_emoji = view.findViewById(R.id.entryItem_emoji);

        // Set data to views
        TV_date.setText(String.format("%02d", entry.cal_date.get(Calendar.DAY_OF_MONTH)));
        TV_monthYear.setText(String.format("%s '%02d", Constants.month_names[entry.cal_date.get(Calendar.MONTH)], entry.cal_date.get(Calendar.YEAR) % 100));
        TV_note.setText(entry.note);
        TV_emoji.setText(entry.emoji);

        // Set click listener
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Open View activity for this item
                Intent intent = new Intent(context, ViewJournalActivity.class);
                intent.putExtra(ViewJournalActivity.PARAM_ID, entry.id);
                context.startActivity(intent);
            }
        });

        return view;
    }
}
