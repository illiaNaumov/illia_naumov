package com.example.naumov.illia.illianaumov.transitionimage.mvp.presenter;

import com.example.naumov.illia.illianaumov.transitionimage.mvp.view.IFirstActivityView;

/**
 * Created by illia_naumov.
 */

public interface IFirstActivityPresenter {
    void setView(IFirstActivityView firstActivityView);
    void loadNews();
    void destroy();
}
