package com.example.naumov.illia.illianaumov.transitionimage.di.component;

import com.example.naumov.illia.illianaumov.transitionimage.di.module.NewsManagerModule;
import com.example.naumov.illia.illianaumov.transitionimage.di.module.PresentersModule;
import com.example.naumov.illia.illianaumov.transitionimage.mvp.presenter.TransitionImageFirstActivityPresenterImpl;
import com.example.naumov.illia.illianaumov.transitionimage.mvp.view.TransitionImageFirstActivity;
import com.example.naumov.illia.illianaumov.transitionimage.mvp.view.TransitionImageSecondActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by illia_naumov.
 */

@Component(modules = {NewsManagerModule.class, PresentersModule.class})
@Singleton
public interface NewsManagerComponent {
    void inject(TransitionImageSecondActivity transitionImageSecondActivity);
    void inject(TransitionImageFirstActivity transitionImageFirstActivity);
}
