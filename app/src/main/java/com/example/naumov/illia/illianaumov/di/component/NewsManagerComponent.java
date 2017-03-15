package com.example.naumov.illia.illianaumov.di.component;

import com.example.naumov.illia.illianaumov.di.module.NewsManagerModule;
import com.example.naumov.illia.illianaumov.view.TransitionImageSecondActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by illia_naumov.
 */

@Component(modules = NewsManagerModule.class)
@Singleton
public interface NewsManagerComponent {
    void inject(TransitionImageSecondActivity transitionImageSecondActivity);
}
