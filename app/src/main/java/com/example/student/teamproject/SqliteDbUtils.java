package com.example.student.teamproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class SqliteDbUtils extends SQLiteOpenHelper {
    private static String TAG = "SqliteDbUtils";
    private static String TABLE_NAME = "Notes";
    private static String ID_NOTES = "idNotes";
    private static String COL1_YEAR = "year";
    private static String COL2_MONTH = "month";
    private static String COL3_DAY = "day";
    private static String COL4_HOUR = "hour";
    private static String COL5_MINUTE = "minute";
    private static String COL6_TITLE = "title";
    private static String COL7_DESCRIPTION = "description";
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
                        COL1_YEAR + " INT, " +
                        COL2_MONTH + " INT, " +
                        COL3_DAY + " INT, " +
                        COL4_HOUR + " INT, " +
                        COL5_MINUTE + " INT, " +
                        COL6_TITLE + " TEXT, " +
                        COL7_DESCRIPTION + " TEXT)";

        sqLiteDatabase.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
