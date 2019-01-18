package com.example.student.teamproject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Alert {
    private String name;
    private String description;
    private int year, month, day, hour, minute;
    private boolean isActive;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getHour() {

        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getDay() {

        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {

        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {

        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }


    public int getRequestCode()
    {
        String reqCode = (this.month != 0) ? String.valueOf(month) : String.valueOf(month + 1) +
                String.valueOf(this.day) +
                String.valueOf(this.hour) +
                String.valueOf(this.minute);
        return Integer.parseInt(reqCode);
    }

    public static int getRequestCode(Calendar date)
    {
        int month = date.get(Calendar.MONTH);
        String reqCode = (month != 0) ? String.valueOf(month) : String.valueOf(month + 1) +
                String.valueOf(date.get(Calendar.DAY_OF_MONTH)) +
                String.valueOf(date.get(Calendar.HOUR_OF_DAY)) +
                String.valueOf(date.get(Calendar.MINUTE));
        return Integer.parseInt(reqCode);
    }

    public int getRequestCode()
    {
        String reqCode = String.valueOf(this.month) +
                String.valueOf(this.day) +
                String.valueOf(this.hour) +
                String.valueOf(this.minute);
        return Integer.parseInt(reqCode);
    }

}
