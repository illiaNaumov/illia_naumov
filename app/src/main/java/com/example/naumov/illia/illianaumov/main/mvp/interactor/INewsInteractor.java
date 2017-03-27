package com.example.naumov.illia.illianaumov.main.mvp.interactor;

import com.example.naumov.illia.illianaumov.main.mvp.model.entities.News;

import rx.Observable;

/**
 * Created by illia_naumov.
 */

public interface INewsInteractor {
    Observable<News> getNews();
}
