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
        Notification.create(context, "Alarm", "Powiadomienie");
        Intent alertDialogIntent = new Intent(context, EventDialog.class);
        alertDialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(alertDialogIntent);
        wakeLock.release();
    }

}
