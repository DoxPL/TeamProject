package com.example.student.teamproject;

import android.app.Activity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public final class KeyboardUtils {
    private static final String TAG = "KeyboardUtils";

    public static void setupKeyboardVisibility(View view, final Activity activity) {
        if (!(view instanceof EditText) && view.getId() != R.id.eye_button_id) {
            view.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    hideSoftKeyboard(activity);

                    return false;
                }
            });
        }

        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);

                setupKeyboardVisibility(innerView, activity);
            }
        }
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);

        if (activity.getCurrentFocus() != null) {
            try {
                inputMethodManager
                        .hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
            } catch (NullPointerException exception) {
                Log.e(TAG, "@inputMethodManager.hideSoftInputFromWindow(..)");
            }
        }
    }
}
