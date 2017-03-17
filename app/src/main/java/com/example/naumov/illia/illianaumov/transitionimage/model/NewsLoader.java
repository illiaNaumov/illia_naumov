package com.example.naumov.illia.illianaumov.transitionimage.model;

import com.example.naumov.illia.illianaumov.transitionimage.domain.NewsPost;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by illia_naumov.
 */

public class NewsLoader {

    public static List<NewsPost> generateNews(){
        List<NewsPost> news = new ArrayList<>();

        news.add(new NewsPost("Custom Title", "Custom Text", com.example.naumov.illia.illianaumov.transitionimage.utils.Constants.PANDA_IMAGE_URL));

        for(int i = 1; i < 20; i++){
            news.add(new NewsPost("Custom title", "Edite, bibite, post mortem nulla voluptas!", com.example.naumov.illia.illianaumov.transitionimage.utils.Constants.PICASSO_TEST_IMAGE_URL));
        }

        return news;
    }


}
