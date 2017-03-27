package com.example.naumov.illia.illianaumov.main.utils;

import java.text.ParseException;
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
    private static SimpleDateFormat newsSimpleDateFormat = new SimpleDateFormat(Constants.News.NEWS_DATE_PATTERN, Locale.US);

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

    public static String formatDate(Date date){
        return simpleDateFormat.format(date);
    }

    public static Date parseDate(String stringDate){
        try {
            return simpleDateFormat.parse(stringDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String formatFromNewsDate(String newsDate){
        try {
            Date date = newsSimpleDateFormat.parse(newsDate);
            return simpleDateFormat.format(date);
        } catch (ParseException e) {
                return newsDate.substring(0, 10).replace('-', '.');
        }
    }

    public static Date getWeekEarlierDate(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.roll(Calendar.WEEK_OF_YEAR, -1);

        return calendar.getTime();

    }


    public static Date getMonthEarlierDate(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.roll(Calendar.MONTH, -1);

        return calendar.getTime();
    }

    public static List<String> getNewsSourcesList(){
        List<String> sources = new ArrayList<>();

        sources.add("bloomberg");
        sources.add("business-insider");
        sources.add("cnbc");

        return sources;
    }


}
