package com.example.naumov.illia.illianaumov.main.di.module;

import com.example.naumov.illia.illianaumov.main.mvp.presenter.INewsPresenter;
import com.example.naumov.illia.illianaumov.main.mvp.presenter.NewsPresenterImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by illia_naumov.
 */

@Module
public class NewsManagerModule {

    public NewsManagerModule() {

    }

    @Provides
    @Singleton
    INewsPresenter provideNewsPresenter(){
        return new NewsPresenterImpl();
    }
}
