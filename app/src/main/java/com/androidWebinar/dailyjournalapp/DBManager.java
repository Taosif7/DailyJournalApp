package com.androidWebinar.dailyjournalapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBManager extends SQLiteOpenHelper {

    /* Database Properties */
    public static final String DB_NAME = "DJAppDatabase";
    public static final int DB_VERSION = 1;

    /* Journal Table */
    public static final String TABLE_JOURNAL_ENTRY = "JournalEntry";
    public static final String COLUMN_ENTRY_ID = "entry_id";
    public static final String COLUMN_ENTRY_DATE = "entry_date";
    public static final String COLUMN_ENTRY_NOTE = "entry_note";
    public static final String COLUMN_ENTRY_EMOJI = "entry_emoji";
    public static final String COLUMN_ENTRY_MODIFIED = "entry_modified";

    public DBManager(Context c) {
        super(c, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        // Create Entry Table
        sqLiteDatabase.execSQL(
                "CREATE TABLE " + TABLE_JOURNAL_ENTRY
                        + " ("
                        + COLUMN_ENTRY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + COLUMN_ENTRY_DATE + " TEXT,"
                        + COLUMN_ENTRY_NOTE + " TEXT,"
                        + COLUMN_ENTRY_EMOJI + " TEXT,"
                        + COLUMN_ENTRY_MODIFIED + " TEXT"
                        + ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
}
