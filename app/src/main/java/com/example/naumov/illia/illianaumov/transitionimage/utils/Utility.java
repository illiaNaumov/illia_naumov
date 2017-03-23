package com.example.naumov.illia.illianaumov.transitionimage.utils;


import java.util.Calendar;
import java.util.Date;

/**
 * Created by illia_naumov.
 */

public class Utility {
    public static boolean isRecentDate(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.WEEK_OF_YEAR, -1);

        return date.compareTo(calendar.getTime()) >= 0;
    }


}
