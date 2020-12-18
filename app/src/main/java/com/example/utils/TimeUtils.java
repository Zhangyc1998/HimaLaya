package com.example.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {

    public static String getMine(int second) {
        int hour = 0;
        int minute = 0;
        int s = 0;
        int temp = second % 3600;
        if (second > 3600) {
            hour = second / 3600;
            if (temp != 0) {
                if (temp > 60) {
                    minute = temp / 60;
                    if (temp % 60 != 0) {
                        s = temp % 60;
                    }
                }
                else {
                    s = temp;
                }
            }
        }
        else {
            minute = second / 60;
            if (second % 60 != 0) {
                s = second % 60;
            }
            return minute + ":" + s;
        }
        return hour + ":" + minute + ":" + s;
    }

    public static String getDate(long time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        return simpleDateFormat.format(new Date(time));

    }
}
