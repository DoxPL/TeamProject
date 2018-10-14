package com.example.student.teamproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class SqliteDbUtils extends SQLiteOpenHelper {
    private static String TAG = "SqliteDbUtils";
    private static String TABLE_NAME = "Notes";
    private static String ID_NOTES = "idNotes";
    private static String COL1_DATETIME = "noteDateTime";
    private static String COL2_TITLE = "title";
    private static String COL3_DESCRIPTION = "description";
    // date, hour, title, description

    // constructor:
    public SqliteDbUtils(
            @Nullable Context context, @Nullable String name,
            @Nullable SQLiteDatabase.CursorFactory factory, int version) {

        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTable =
                "CREATE TABLE " + TABLE_NAME +
                        "( " + ID_NOTES + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COL1_DATETIME + " DATE, " +
                        COL2_TITLE + " TEXT, " +
                        COL3_DESCRIPTION + " TEXT)";

        sqLiteDatabase.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean addItem(String date, String title, String description) {
        // TODO("Parse date properly.");

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL1_DATETIME, date);
        contentValues.put(COL2_TITLE, title);
        contentValues.put(COL3_DESCRIPTION, description);

        long result = db.insert(TABLE_NAME, null, contentValues);

        return result != -1;
    }

    // Unique titles per day for notes.
    public void deleteItem(String date, String title) {
        // TODO("Parse date properly.");

        SQLiteDatabase db = this.getWritableDatabase();
        String query =
                "DELETE FROM " + TABLE_NAME +
                        " WHERE " + COL1_DATETIME + " = " + date +
                        " AND " + COL2_TITLE + " = " + title;

        db.execSQL(query);
        db.close();
    }

    // TODO("Make list getter using simpleDateFormat for parsing, or parsing DATE value to current NotesModel.");
}
