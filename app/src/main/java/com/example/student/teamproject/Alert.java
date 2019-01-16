package com.example.student.teamproject;

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
        String reqCode = String.valueOf(this.month) +
                String.valueOf(this.day) +
                String.valueOf(this.hour) +
                String.valueOf(this.minute);
        return Integer.parseInt(reqCode);
    }

    public static int getRequestCode(Date date)
    {
        String reqCode = String.valueOf(date.getMonth()) +
                String.valueOf(date.getDay()) +
                String.valueOf(date.getHours()) +
                String.valueOf(date.getMinutes());
        return Integer.parseInt(reqCode);
    }

}
