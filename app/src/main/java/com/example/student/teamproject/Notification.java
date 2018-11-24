package com.example.student.teamproject;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class Notification {
    public static final int REQUEST_CODE = 0;

    public static void create(Context context, String title, String description)
    {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context)
                .setContentTitle(title)
                .setContentText(description)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        notificationManager.notify(0, notificationBuilder.build());

    }

    public static void handle(Context context, Alert alert)
    {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, REQUEST_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Calendar date = Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault());
        date.set(Calendar.DATE, alert.getDay() - 1);
        date.set(Calendar.MONTH, alert.getMonth() - 1);
        date.set(Calendar.YEAR, alert.getYear());
        date.set(Calendar.HOUR_OF_DAY, alert.getHour());
        date.set(Calendar.MINUTE, alert.getMinute());
        date.set(Calendar.SECOND, 0);
        Toast.makeText(context, "Ustawiono powiadomienie na: " + date.getTime().toString(), Toast.LENGTH_LONG).show();
        alarmManager.set(AlarmManager.RTC_WAKEUP, date.getTimeInMillis(), pendingIntent);
    }
}
