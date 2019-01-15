package com.example.student.teamproject;

import android.content.Context;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateUtilities {

    public static String formatDate(String fullDate, Context context) {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date tmpDate = null;
        try {
            tmpDate = dateFormat.parse(fullDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long when = tmpDate.getTime();
        int flags = 0;
        flags |= android.text.format.DateUtils.FORMAT_SHOW_TIME;
        flags |= android.text.format.DateUtils.FORMAT_SHOW_DATE;
        flags |= android.text.format.DateUtils.FORMAT_ABBREV_MONTH;
        flags |= android.text.format.DateUtils.FORMAT_SHOW_YEAR;

        return  android.text.format.DateUtils.formatDateTime(context,
                when + TimeZone.getDefault().getOffset(when), flags);
    }

    public static String getFullDate(Alert alert)
    {
        return alert.getYear()+ "-" + alert.getMonth() + "-" + alert.getDay() + " " + alert.getHour() + ":" + alert.getMinute();
    }
}
