package com.example.naumov.illia.illianaumov.main.retrofit;


import com.example.naumov.illia.illianaumov.main.mvp.model.entities.News;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by illia_naumov.
 */

public interface NewsApi {
    @GET("v1/articles?")
    Observable<News> getNews(@Query("source") String source, @Query("apiKey") String apiKey);
}
