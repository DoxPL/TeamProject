package com.example.student.teamproject;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public final class LoginSharedPrefsUtils {
    private static final String PREFS_NAME = "LoginSharedPrefsUtils";
    private static final String PREF_IS_USER_SIGNED_IN = "isUserSignedIn";
    private static final boolean DEF_IS_USER_SIGNED_IN = false;

    public static void setIsUserSignedIn(Context context, boolean isUserSignedIn) {
        SharedPreferences.Editor editor =
                context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
        editor.putBoolean(PREF_IS_USER_SIGNED_IN, isUserSignedIn);
        editor.apply();
    }

    public static boolean getIsUserSignedIn(Context context) {
        return context
                .getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
                .getBoolean(PREF_IS_USER_SIGNED_IN, DEF_IS_USER_SIGNED_IN);
    }
}
