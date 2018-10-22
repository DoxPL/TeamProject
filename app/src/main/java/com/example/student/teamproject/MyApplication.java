package com.example.student.teamproject;

import android.app.Application;
import android.os.Build;
import android.security.NetworkSecurityPolicy;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted();
        }
    }

}
