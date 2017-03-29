package com.example.naumov.illia.illianaumov.main.mvp.interactor;

import com.example.naumov.illia.illianaumov.main.mvp.model.entities.Article;
import com.example.naumov.illia.illianaumov.main.mvp.model.entities.News;

import java.util.List;

import rx.Observable;

/**
 * Created by illia_naumov.
 */

public interface INewsInteractor {
    Observable<List<Article>> getNews();
}
