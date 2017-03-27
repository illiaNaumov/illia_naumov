package com.example.naumov.illia.illianaumov.main.mvp.interactor;

import com.example.naumov.illia.illianaumov.BuildConfig;
import com.example.naumov.illia.illianaumov.main.MyApp;
import com.example.naumov.illia.illianaumov.main.mvp.model.entities.News;
import com.example.naumov.illia.illianaumov.main.retrofit.NewsApi;
import com.example.naumov.illia.illianaumov.main.utils.Utility;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by illia_naumov.
 */

public class NewsInteractorImpl implements INewsInteractor {

    @Inject
    public NewsApi newsApi;

    public NewsInteractorImpl() {
        MyApp.getNewsManagerComponent().inject(this);
    }

    @Override
    public Observable<News> getNews(){
        return Observable.just(Utility.getNewsSourcesList())
                .flatMap(Observable::from)
                .flatMap(s -> newsApi.getNews(s, BuildConfig.NEWS_API_KEY));
    }
}
