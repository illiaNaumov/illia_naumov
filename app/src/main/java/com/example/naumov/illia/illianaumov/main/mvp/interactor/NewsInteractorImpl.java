package com.example.naumov.illia.illianaumov.main.mvp.interactor;

import com.example.naumov.illia.illianaumov.BuildConfig;
import com.example.naumov.illia.illianaumov.main.MyApp;
import com.example.naumov.illia.illianaumov.main.mvp.model.entities.Article;
import com.example.naumov.illia.illianaumov.main.retrofit.NewsApi;
import com.example.naumov.illia.illianaumov.main.utils.Utility;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by illia_naumov.
 */

public class NewsInteractorImpl implements INewsInteractor {

    @Inject
    public NewsApi newsApi;

    public NewsInteractorImpl() {
        MyApp.plusNewsManagerComponent().inject(this);
    }

    @Override
    public Observable<List<Article>> getNews(){
        return Observable.just(Utility.getNewsSourcesList())
                .flatMap(Observable::from)
                .flatMap(s -> newsApi.getNews(s, BuildConfig.NEWS_API_KEY))
                .flatMap(news -> Observable.from(news.getArticles()))
                .toList();
    }
}
