package com.example.naumov.illia.illianaumov.main.mvp.presenter;

import com.example.naumov.illia.illianaumov.main.mvp.view.activity.INewsView;

/**
 * Created by illia_naumov.
 */

public interface INewsPresenter {
    void setView(INewsView newsView);
    void loadNews();
    void destroy();
}
