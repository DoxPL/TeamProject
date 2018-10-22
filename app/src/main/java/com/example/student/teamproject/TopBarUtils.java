package com.example.student.teamproject;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public final class TopBarUtils {
    public static void setTopBar(AppCompatActivity activity, View view, String title) {
        final String TAG = activity.getLocalClassName();

        try {
            ActionBar actionBar = activity.getSupportActionBar();

            actionBar.setTitle(title);
        } catch (NullPointerException exception) {
            Log.e(TAG, "Cannot get actionBar. @setTopBar(..)");
        }
    }
}


//private void setTopBar(View view) {
//        AppCompatActivity activity = (AppCompatActivity) getActivity();
//
//        try {
//            ActionBar actionBar = activity.getSupportActionBar();
//
//            actionBar.setTitle(R.string.calendar);
//        } catch (NullPointerException exception) {
//            Log.e(TAG, "Cannot get actionBar. @setTopBar(..)");
//        }
//    }