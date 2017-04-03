package com.example.naumov.illia.illianaumov.main.utils;

/**
 * Created by illia_naumov.
 */

public class Constants {
    public static final String PICASSO_TEST_IMAGE_URL = "http://i.imgur.com/DvpvklR.png";

    public static class Currency{
        public static final String CURRENCY_PB_DOMAIN = "https://api.privatbank.ua/";

        public static final String USD = "USD";
        public static final String RUB = "RUB";
        public static final String EUR = "EUR";

        public static final String DATE_PATTERN = "dd.MM.yyyy";
    }

    public static class News{
        public static final String NEWS_BASE_URL = "https://newsapi.org/";
        public static final String NEWS_DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    }

    public static class SharedPrefs{
        public static final String CURRENCY_KEY = "currency";
        public static final String BEGIN_DATE_KEY = "beginDate";
        public static final String END_DATE_KEY = "endDate";
        public static final String CHECKED_CURRENCY = "prefCheckedCurrency";
        public static final String CHECKED_CURRENCY_VALUE = "prefCheckedCurrencyValue";

    }

    public static class Bundles{
        public static final String IS_FROM_NOTIFICATION = "com.example.naumov.illia.illianaumov.IS_FROM_NOTIFICATION";
        public static final String CURRENCY_NAME = "com.example.naumov.illia.illianaumov.CURRENCY_NAME";
    }

}
