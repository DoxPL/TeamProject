package com.example.student.teamproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.util.ArrayList;

public class SqliteDbUtils extends SQLiteOpenHelper {
    private static final String TAG = "SqliteDbUtils";
    private static final String TABLE_NAME = "Notes";
    private static final String ID_NOTES = "idNotes";
    private static final String COL1_DATETIME = "noteDateTime";
    private static final String COL2_TITLE = "title";
    private static final String COL3_DESCRIPTION = "description";
    private static final String COL4_ENABLED = "enabled";
    // date, hour, title, description

    private Context context;

    public SqliteDbUtils(@Nullable Context context) {
        super(context, TABLE_NAME, null, 1);

        this.context = context;
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

        if (!isDataAlreadyInDb(date, title)) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();

            contentValues.put(COL1_DATETIME, date);
            contentValues.put(COL2_TITLE, title);
            contentValues.put(COL3_DESCRIPTION, description);
            contentValues.put(COL4_ENABLED, is_enabled);

            long result = db.insert(TABLE_NAME, null, contentValues);

            return result != -1;
        }

        return false;
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

    private boolean isDataAlreadyInDb(String date, String title) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query =
                "SELECT * FROM " + TABLE_NAME +
                " WHERE " + COL1_DATETIME + " = \'" + date + "\'" +
                        " AND " + COL2_TITLE + " = \'" + title + "\'";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.getCount() <= 0) {
            cursor.close();
            return false;
        }

//        Log.d(TAG, "addData: Adding h:$hour m:$minute to $TABLE_NAME failed " +
//                ", already exist");
        Toast.makeText(
                context, context.getString(R.string.already_exist), Toast.LENGTH_SHORT).show();

        return true;
    }

//    private fun isDataAlreadyInDb(hour: Int, minute: Int): Boolean {
//
//        val sqlDb = this.writableDatabase
//        val query = "SELECT * FROM $TABLE_NAME WHERE $COL1_HOUR = $hour and $COL2_MINUTE = $minute"
//        val cursor = sqlDb.rawQuery(query, null)
//
//        if (cursor.count <= 0) {
//            cursor.close()
//            return false
//        }
//
//        Log.d(TAG, "addData: Adding h:$hour m:$minute to $TABLE_NAME failed " +
//                ", already exist")
//        Toast.makeText(
//                context, context.getString(R.string.already_exist), Toast.LENGTH_SHORT).show()
//
//        cursor.close()
//        return true
//    }

    // TODO("Make list getter using simpleDateFormat for parsing, or parsing DATE value to current NotesModel.");
    
    public ArrayList<NotesModel> getList() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        ArrayList<NotesModel> list = new ArrayList<NotesModel>();
        
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

    public NotesModel getSingleRow(String name)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COL2_TITLE + " = \'" + name + "\'";
        Cursor cursor = null;
        try {
            cursor = ((SQLiteDatabase) db).rawQuery(query, null);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                NotesModel event = new NotesModel(cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getInt(4) == 1
                );
                return event;
            }
        }
        finally {
            if(cursor != null)
                cursor.close();
        }
        return null;
    }
}
