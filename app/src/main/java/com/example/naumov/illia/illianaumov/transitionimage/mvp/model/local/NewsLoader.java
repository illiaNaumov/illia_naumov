package com.example.naumov.illia.illianaumov.transitionimage.mvp.model.local;

import com.example.naumov.illia.illianaumov.transitionimage.mvp.model.entities.NewsPost;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by illia_naumov.
 */

public class NewsLoader {
    private static long timeStamp = Calendar.getInstance().getTimeInMillis();
    private static  Random rand = new Random();

    public static List<NewsPost> generateNews(){
        List<NewsPost> news = new ArrayList<>();

        news.add(new NewsPost("Custom Title", "Custom Text",
                com.example.naumov.illia.illianaumov.transitionimage.utils.Constants.PANDA_IMAGE_URL,
                generateDate()));

        for(int i = 1; i < 1000; i++){
            news.add(new NewsPost("Custom title", "Edite, bibite, post mortem nulla voluptas!",
                    com.example.naumov.illia.illianaumov.transitionimage.utils.Constants.PICASSO_TEST_IMAGE_URL,
                    generateDate()));
        }

        return news;
    }

    private static Date generateDate(){
        long randSubtractor = rand.nextInt(90) * 24 * 60 * 60 * 1000L;
        return new Date(timeStamp - randSubtractor);
    }

}
