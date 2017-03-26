package com.example.naumov.illia.illianaumov.main.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by illia_naumov.
 */

public class Utility {
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.Currency.DATE_PATTERN, Locale.US);

    public static List<String> makeDateList(Date beginDate, Date endDate){
        List<String> dateList = new ArrayList<>();

        Calendar iterCalend = Calendar.getInstance();
        iterCalend.setTime(beginDate);

        while(iterCalend.getTime().compareTo(endDate) <  1){
            dateList.add(simpleDateFormat.format(iterCalend.getTime()));

            iterCalend.add(Calendar.DAY_OF_YEAR, 1);
        }

        return dateList;
    }
}
