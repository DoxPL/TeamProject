package com.example.student.teamproject;

import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class NotesModel {
    private String date;
    private String title;
    private String description;
    private boolean isEnabled;

//    private int year;
//    private int month;
//    private int day;
//    private int hour;
//    private int minute;
//            int year, int month, int day, int hour, int minute,

    public NotesModel(String date, String title, String description, boolean isEnabled) {

        this.date = date;
        this.title = title;
        this.description = description;
        this.isEnabled = isEnabled;
    }

    public String toString() {
        return "\nDate: " + date +
                "\nTitle: " + title +
                "\nDescription: " + description +
                "\nisEnabled: " + isEnabled + "\n";
    }

//        this.year = year;
//        this.month = month;
//        this.day = day;
//        this.hour = hour;
//        this.minute = minute;

    public String getDate() {
            //String [] monthNames = new String[] { "styczeń", "luty", "marzec", "kwiecień", "maj", "czerwiec", "lipiec", "sierpień",
                  //  "wrzesień", "październik", "listopad", "grudzień" };
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        this.isEnabled = enabled;
    }

    public int getRequestCode()
    {
        return 0;
    }
}

//    NotesModel(int year, int month, int day, int hour, int minute, String title, String description)

//    public int getYear() {
//        return year;
//    }
//
//    public void setYear(int year) {
//        this.year = year;
//    }
//
//    public int getMonth() {
//        return month;
//    }
//
//    public void setMonth(int month) {
//        this.month = month;
//    }
//
//    public int getDay() {
//        return day;
//    }
//
//    public void setDay(int day) {
//        this.day = day;
//    }
//
//    public int getHour() {
//        return hour;
//    }
//
//    public void setHour(int hour) {
//        this.hour = hour;
//    }
//
//    public int getMinute() {
//        return minute;
//    }
//
//    public void setMinute(int minute) {
//        this.minute = minute;
//    }
