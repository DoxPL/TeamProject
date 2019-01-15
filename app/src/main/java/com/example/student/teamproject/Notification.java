package com.example.student.teamproject;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class Notification {

    public static void create(Context context, String title, String description)
    {
        NotificationCompat.Builder ncBuilder = new NotificationCompat.Builder(context)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setContentTitle(title)
                .setContentText(description)
                .setSmallIcon(R.drawable.ic_menu_send);
        NotificationManager nManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        nManager.notify(1, ncBuilder.build());
        //showDialog(text);

    }

    public static void handle(Context context, Alert alert)
    {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, alert.getRequestCode(), intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Calendar date = Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault());
        date.set(Calendar.DAY_OF_MONTH, alert.getDay());
        date.set(Calendar.MONTH, alert.getMonth());
        date.set(Calendar.YEAR, alert.getYear());
        date.set(Calendar.HOUR_OF_DAY, alert.getHour());
        date.set(Calendar.MINUTE, alert.getMinute());
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);
        Toast.makeText(context, "Ustawiono powiadomienie na: " + date.getTime().toString(), Toast.LENGTH_LONG).show();
        alarmManager.set(AlarmManager.RTC_WAKEUP, date.getTimeInMillis(), pendingIntent);
    }

}
