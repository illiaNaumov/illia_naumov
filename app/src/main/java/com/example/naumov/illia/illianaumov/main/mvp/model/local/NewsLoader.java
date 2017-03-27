package com.example.naumov.illia.illianaumov.main.mvp.model.local;

import com.example.naumov.illia.illianaumov.main.mvp.model.entities.NewsPost;
import com.example.naumov.illia.illianaumov.main.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by illia_naumov.
 */

public class NewsLoader {

    public static List<NewsPost> generateNews(){
        List<NewsPost> news = new ArrayList<>();

        news.add(new NewsPost("Custom Title", "Custom Text", Constants.PICASSO_TEST_IMAGE_URL));

        for(int i = 1; i < 20; i++){
            news.add(new NewsPost("Custom title", "Edite, bibite, post mortem nulla voluptas!", Constants.PICASSO_TEST_IMAGE_URL));
        }

        return news;
    }


}
