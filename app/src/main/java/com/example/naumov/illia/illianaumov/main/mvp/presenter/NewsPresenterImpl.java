package com.example.naumov.illia.illianaumov.main.mvp.presenter;

import android.util.Log;

import com.example.naumov.illia.illianaumov.main.MyApp;
import com.example.naumov.illia.illianaumov.main.mvp.model.entities.News;
import com.example.naumov.illia.illianaumov.main.mvp.view.activity.INewsView;
import com.example.naumov.illia.illianaumov.main.retrofit.NewsApi;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by illia_naumov.
 */

public class NewsPresenterImpl implements INewsPresenter {


    @Inject
    public NewsApi newsApi;

    private INewsView newsView;
    private Subscription subscription;

    public NewsPresenterImpl() {
        MyApp.getNewsManagerComponent().inject(this);
    }

    @Override
    public void setView(INewsView newsView) {
        this.newsView = newsView;
    }

    @Override
    public void loadNews() {
        subscription = newsApi.getNews("bloomberg", "b55c2696462747d0bf61e156f5b60531")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        this::showNews,
                        System.out::println);
    }

    private void showNews(News news){
        Log.i("ARTICLES", "size =" + news.getArticles().size());
        if(newsView != null) {
            newsView.showNews(news.getArticles());
        }
    }

    @Override
    public void destroy() {
        newsView = null;

        subscription.unsubscribe();
    }
}
