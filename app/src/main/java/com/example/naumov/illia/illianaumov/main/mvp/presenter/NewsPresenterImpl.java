package com.example.naumov.illia.illianaumov.main.mvp.presenter;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.example.naumov.illia.illianaumov.main.MyApp;
import com.example.naumov.illia.illianaumov.main.mvp.interactor.INewsInteractor;
import com.example.naumov.illia.illianaumov.main.mvp.model.entities.Article;
import com.example.naumov.illia.illianaumov.main.mvp.view.activity.INewsView;

import java.util.List;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by illia_naumov.
 */

@InjectViewState
public class NewsPresenterImpl extends BasePresenter<INewsView> implements INewsPresenter {


    @Inject
    public INewsInteractor newsInteractor;

    public NewsPresenterImpl() {
        MyApp.plusNewsManagerComponent().inject(this);
    }

    @Override
    public void loadNews() {
        Subscription subscription = newsInteractor.getNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        this::showNews,
                        System.out::println);

        unsubscribeOnDestroy(subscription);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        loadNews();
    }

    private void showNews(List<Article> news) {
            getViewState().showNews(news);
    }

}
