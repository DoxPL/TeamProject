package com.example.student.teamproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SqliteDbUtils extends SQLiteOpenHelper {
    private static final String TAG = "SqliteDbUtils";
    private static final String TABLE_NAME = "Notes";
    private static final String ID_NOTES = "idNotes";
    private static final String COL1_DATETIME = "noteDateTime";
    private static final String COL2_TITLE = "title";
    private static final String COL3_DESCRIPTION = "description";
    private static final String COL4_ENABLED = "enabled";
    // date, hour, title, description

    public SqliteDbUtils(@Nullable Context context) {
        super(context, TABLE_NAME, null, 1);
    }

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
                        COL1_DATETIME + " TEXT, " +
                        COL2_TITLE + " TEXT, " +
                        COL3_DESCRIPTION + " TEXT, " +
                        COL4_ENABLED + " BOOLEAN)";

        sqLiteDatabase.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean addItem(String date, String title, String description, boolean is_enabled) {
        // TODO("Parse date properly.");

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL1_DATETIME, date);
        contentValues.put(COL2_TITLE, title);
        contentValues.put(COL3_DESCRIPTION, description);
        contentValues.put(COL4_ENABLED, is_enabled);

        long result = db.insert(TABLE_NAME, null, contentValues);

        return result != -1;
    }

    // Unique titles per day for notes.
    public void deleteItem(String date, String title) {
        // TODO("Parse date properly.");

        SQLiteDatabase db = this.getWritableDatabase();
        String query =
                "DELETE FROM " + TABLE_NAME +
                        " WHERE " + COL1_DATETIME + " = \'" + date + "\'" +
                        " AND " + COL2_TITLE + " = \'" + title + "\'";

        db.execSQL(query);
        db.close();
    }

    // TODO("Make list getter using simpleDateFormat for parsing, or parsing DATE value to current NotesModel.");
    
    public List<NotesModel> getList() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        List<NotesModel> list = new ArrayList<NotesModel>();
        
        if (cursor.moveToFirst()) {
            do {
                list.add(new NotesModel(
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getInt(4) == 1
                ));
            } while (cursor.moveToNext());
        }

        cursor.close();

        return list;
    }
}
