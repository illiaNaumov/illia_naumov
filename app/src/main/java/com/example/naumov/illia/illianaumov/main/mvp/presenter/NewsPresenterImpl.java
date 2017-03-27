package com.example.naumov.illia.illianaumov.main.mvp.presenter;

import com.example.naumov.illia.illianaumov.BuildConfig;
import com.example.naumov.illia.illianaumov.main.MyApp;
import com.example.naumov.illia.illianaumov.main.mvp.interactor.INewsInteractor;
import com.example.naumov.illia.illianaumov.main.mvp.model.entities.News;
import com.example.naumov.illia.illianaumov.main.mvp.view.activity.INewsView;
import com.example.naumov.illia.illianaumov.main.retrofit.NewsApi;
import com.example.naumov.illia.illianaumov.main.utils.Utility;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by illia_naumov.
 */

public class NewsPresenterImpl implements INewsPresenter {


    @Inject
    public INewsInteractor newsInteractor;

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
        subscription = newsInteractor.getNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        this::showNews,
                        System.out::println);
    }

    private void showNews(News news) {
        if (newsView != null) {
            newsView.showNews(news.getArticles());
        }
    }

    @Override
    public void destroy() {
        newsView = null;

        subscription.unsubscribe();
    }
}
