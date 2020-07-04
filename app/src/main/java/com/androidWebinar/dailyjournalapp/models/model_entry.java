package com.androidWebinar.dailyjournalapp.models;

import android.content.ContentValues;
import android.database.Cursor;

import com.androidWebinar.dailyjournalapp.DBManager;

import java.util.Calendar;

public class model_entry {

    /* Fields */
    public int id;
    public String note, emoji, date, modified;

    Calendar cal_date, cal_modified;

    public model_entry(int id, String note, String emoji, Calendar date, Calendar modified) {
        this.id = id;
        this.note = note;
        this.emoji = emoji;
        this.cal_date = date;
        this.cal_modified = modified;

        this.date = String.format("%04d%02d%02d%02d%02d", date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DATE), date.get(Calendar.HOUR_OF_DAY), date.get(Calendar.MINUTE));
        this.modified = String.format("%04d%02d%02d%02d%02d", modified.get(Calendar.YEAR), modified.get(Calendar.MONTH), modified.get(Calendar.DATE), modified.get(Calendar.HOUR_OF_DAY), modified.get(Calendar.MINUTE));
    }

    public model_entry(Cursor data) {
        this.id = data.getInt(data.getColumnIndex(DBManager.COLUMN_ENTRY_ID));
        this.note = data.getString(data.getColumnIndex(DBManager.COLUMN_ENTRY_NOTE));
        this.emoji = data.getString(data.getColumnIndex(DBManager.COLUMN_ENTRY_EMOJI));
        this.date = data.getString(data.getColumnIndex(DBManager.COLUMN_ENTRY_DATE));
        this.modified = data.getString(data.getColumnIndex(DBManager.COLUMN_ENTRY_MODIFIED));

        // Create Calendar Instance by splitting the string
        this.cal_date = Calendar.getInstance();
        cal_date.set(Calendar.YEAR, Integer.parseInt(this.date.substring(0, 4)));
        cal_date.set(Calendar.MONTH, Integer.parseInt(this.date.substring(4, 6)));
        cal_date.set(Calendar.DATE, Integer.parseInt(this.date.substring(6, 8)));
        cal_date.set(Calendar.HOUR_OF_DAY, Integer.parseInt(this.date.substring(8, 10)));
        cal_date.set(Calendar.MINUTE, Integer.parseInt(this.date.substring(10, 12)));

        // Create Calendar Instance by splitting the string
        this.cal_modified = Calendar.getInstance();
        cal_modified.set(Calendar.YEAR, Integer.parseInt(this.modified.substring(0, 4)));
        cal_modified.set(Calendar.MONTH, Integer.parseInt(this.modified.substring(4, 6)));
        cal_modified.set(Calendar.DATE, Integer.parseInt(this.modified.substring(6, 8)));
        cal_modified.set(Calendar.HOUR_OF_DAY, Integer.parseInt(this.modified.substring(8, 10)));
        cal_modified.set(Calendar.MINUTE, Integer.parseInt(this.modified.substring(10, 12)));
    }

    public ContentValues get() {
        ContentValues values = new ContentValues();
        values.put(DBManager.COLUMN_ENTRY_ID, this.id);
        values.put(DBManager.COLUMN_ENTRY_DATE, this.date);
        values.put(DBManager.COLUMN_ENTRY_NOTE, this.note);
        values.put(DBManager.COLUMN_ENTRY_EMOJI, this.emoji);
        values.put(DBManager.COLUMN_ENTRY_MODIFIED, this.modified);

        return values;
    }

}
