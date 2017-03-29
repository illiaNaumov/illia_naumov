package com.example.naumov.illia.illianaumov.main.mvp.view.activity;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.naumov.illia.illianaumov.main.mvp.model.entities.Article;

import java.util.List;

/**
 * Created by illia_naumov.
 */

@StateStrategyType(AddToEndSingleStrategy.class)
public interface INewsView extends MvpView {
    void showNews(List<Article> news);
}
