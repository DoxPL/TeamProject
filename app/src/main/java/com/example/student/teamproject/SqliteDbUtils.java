package com.example.student.teamproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class SqliteDbUtils extends SQLiteOpenHelper {
    private static String TAG = "SqliteDbUtils";
    private static String TABLE_NAME = "Notes";
    private static String ID_NOTES = "idNotes";
    // date, hour, title, description

    // constructor:
    public SqliteDbUtils(
            @Nullable Context context, @Nullable String name,
            @Nullable SQLiteDatabase.CursorFactory factory, int version) {

        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
