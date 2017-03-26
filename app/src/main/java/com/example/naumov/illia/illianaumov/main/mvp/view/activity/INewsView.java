package com.example.naumov.illia.illianaumov.main.mvp.view.activity;

import com.example.naumov.illia.illianaumov.main.mvp.model.entities.Article;

import java.util.List;

/**
 * Created by illia_naumov.
 */

public interface INewsView {
    void showNews(List<Article> news);
}
