package com.example.student.teamproject;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtilities {

    public static Calendar dateFormat(String date)
    {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-DD HH:mm", Locale.ENGLISH);
        try {
            cal.setTime(dateFormat.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return cal;
    }

    public static String getStrDate(Alert alert)
    {
        return alert.getYear()+ "-" + alert.getMonth() + "-" + alert.getDay() + " " + alert.getHour() + ":" + alert.getMinute();
    }

    public static String getDate(Alert alert)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.set(alert.getYear(), alert.getMonth(), alert.getDay(), alert.getHour(), alert.getMinute());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-DD HH:mm");
        return dateFormat.format(calendar.getTime());
    }

}
