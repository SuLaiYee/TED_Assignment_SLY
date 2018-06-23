package com.padcmyanmar.ted_talks_app.utils;

public class MilliSecToMinSec {
    public static String getHourMinuteSecond(long milliseconds){
        long value = milliseconds;
        int hours = (int) value/3600;
        int remainder = (int) value - hours * 3600;
        int minutes = remainder/60;
        remainder = remainder - minutes * 60;
        int secs = remainder;
        String time =  minutes + ":" + secs;
        return time;
    }
}
