package com.example.naumov.illia.illianaumov.main.di.component;

import com.example.naumov.illia.illianaumov.main.di.module.NetModule;
import com.example.naumov.illia.illianaumov.main.di.module.NewsManagerModule;
import com.example.naumov.illia.illianaumov.main.mvp.presenter.INewsPresenter;
import com.example.naumov.illia.illianaumov.main.mvp.presenter.NewsPresenterImpl;
import com.example.naumov.illia.illianaumov.main.mvp.view.activity.TransitionImageFirstActivity;
import com.example.naumov.illia.illianaumov.main.mvp.view.activity.TransitionImageSecondActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by illia_naumov.
 */

@Component(modules = {NewsManagerModule.class, NetModule.class})
@Singleton
public interface NewsManagerComponent {
    void inject(TransitionImageFirstActivity transitionImageFirstActivity);
    void inject(TransitionImageSecondActivity transitionImageSecondActivity);
    void inject(NewsPresenterImpl newsPresenter);
}
