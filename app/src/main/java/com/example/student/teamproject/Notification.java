package com.example.student.teamproject;

import android.content.Context;
import android.support.v4.app.NotificationCompat;

public class Notification {
    private String title;
    private String description;
    private Context context;

    public Notification(String title, String description)
    {
        this.title = title;
        this.description = description;
    }

    public void create()
    {
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context, 1)
                .setContentTitle(this.title)
                .setContentText(this.description);
    }
}
