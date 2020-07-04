package com.androidWebinar.dailyjournalapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.androidWebinar.dailyjournalapp.models.model_entry;

import java.util.ArrayList;
import java.util.List;

public class entry_controller {
    Context context;
    DBManager dbManager;

    entry_controller(Context c) {
        this.context = c;
        this.dbManager = new DBManager(c);
    }

    /**
     * Method to add entry to the database
     *
     * @param entry Entry object that contains entry information
     * @return int ID of the new entry
     */
    public int addEntry(model_entry entry) {
        ContentValues values = entry.get();

        // Because we don't want to provide manual ID to the entry
        values.remove(DBManager.COLUMN_ENTRY_ID);
        return (int) dbManager.getWritableDatabase().insert(DBManager.TABLE_JOURNAL_ENTRY, null, values);
    }

    /**
     * Method to remove a particular entry from database
     *
     * @param entry Entry object that contains entry information
     */
    public void removeEntry(model_entry entry) {
        dbManager.getWritableDatabase().delete(DBManager.TABLE_JOURNAL_ENTRY, DBManager.COLUMN_ENTRY_ID + "=" + entry.id, null);
    }

    /**
     * Method to get all entries from the database
     *
     * @return returns a list of model_entry of entries from database
     */
    public List<model_entry> getEntries() {
        List<model_entry> entries = new ArrayList<>();
        Cursor data = dbManager.getReadableDatabase().query(DBManager.TABLE_JOURNAL_ENTRY, null, null, null, null, null, DBManager.COLUMN_ENTRY_DATE + " DESC");
        while (data.moveToNext()) {
            model_entry entry = new model_entry(data);
            entries.add(entry);
        }
        return entries;
    }
}
