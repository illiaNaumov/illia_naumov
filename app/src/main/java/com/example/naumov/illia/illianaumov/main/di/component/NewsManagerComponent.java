package com.example.naumov.illia.illianaumov.main.di.component;

import com.example.naumov.illia.illianaumov.main.di.module.NetModule;
import com.example.naumov.illia.illianaumov.main.di.module.NewsManagerModule;
import com.example.naumov.illia.illianaumov.main.di.scope.NewsScope;
import com.example.naumov.illia.illianaumov.main.mvp.interactor.NewsInteractorImpl;
import com.example.naumov.illia.illianaumov.main.mvp.presenter.INewsPresenter;
import com.example.naumov.illia.illianaumov.main.mvp.presenter.NewsPresenterImpl;
import com.example.naumov.illia.illianaumov.main.mvp.view.activity.TransitionImageFirstActivity;
import com.example.naumov.illia.illianaumov.main.mvp.view.activity.TransitionImageSecondActivity;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Subcomponent;

/**
 * Created by illia_naumov.
 */

@Subcomponent(modules = NewsManagerModule.class)
@NewsScope
public interface NewsManagerComponent {
    void inject(TransitionImageFirstActivity transitionImageFirstActivity);
    void inject(NewsPresenterImpl newsPresenter);
    void inject(NewsInteractorImpl newsInteractor);
}
