package com.example.student.teamproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.WindowManager;
import android.widget.Toast;

public class EventDialog extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setBackgroundColor(Color.rgb(45, 20, 107));
        long []vibPattern = {500, 500, 400};
        final Vibrator vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        try {
            alertDialog.setTitle(getString(R.string.dialogTitle));
            String message = getIntent().getStringExtra("NAME") + ((getIntent().hasExtra("DESCRIPTION")) ?
                    getIntent().getStringExtra("DESCRIPTION") : "");
            alertDialog.setMessage(message);
        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL, getString(R.string.turn_off_alert), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
                vib.cancel();
                finish();
            }
        });
        //alertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        alertDialog.show();
        vib.vibrate(vibPattern, 0);
    }
}
