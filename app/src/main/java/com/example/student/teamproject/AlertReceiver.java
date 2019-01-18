package com.example.student.teamproject;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.widget.Toast;

public class AlertReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        PowerManager powerManager = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "");
        wakeLock.acquire();
        SqliteDbUtils dbUtils = new SqliteDbUtils(context);
        String eventName = intent.getExtras().getString("EVENT_NAME");
        Intent alertDialogIntent = new Intent(context, EventDialog.class);
        try {
            NotesModel event = dbUtils.getSingleRow(eventName);
            Notification.create(context, context.getString(R.string.notification), event.getTitle());
            alertDialogIntent.putExtra("NAME", event.getTitle());
            if(!event.getDescription().equals(context.getString(R.string.noDescr)))
                alertDialogIntent.putExtra("DESCRIPTION", "\n" + event.getDescription());
        }
        catch(Exception e)
        {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        }

        alertDialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(alertDialogIntent);
        wakeLock.release();
    }
}
