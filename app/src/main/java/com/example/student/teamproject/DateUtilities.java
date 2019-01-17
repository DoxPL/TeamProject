package com.example.student.teamproject;

import android.content.Context;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtilities {

    public static Date dateFormat(String date) {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-M-dd HH:mm:ss", Locale.ENGLISH);
        Date tmpDate = null;
        try {
            tmpDate = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return tmpDate;
    }

    public static String getFullDate(Alert alert)
    {
        return alert.getYear()+ "-" + alert.getMonth() + "-" + alert.getDay() + " " + alert.getHour() + ":" + alert.getMinute();
    }

}
