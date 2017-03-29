package com.example.naumov.illia.illianaumov.main.di.module;

import com.example.naumov.illia.illianaumov.main.di.scope.NewsScope;
import com.example.naumov.illia.illianaumov.main.mvp.interactor.INewsInteractor;
import com.example.naumov.illia.illianaumov.main.mvp.interactor.NewsInteractorImpl;
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

    @Provides
    @NewsScope
    INewsPresenter provideNewsPresenter(){
        return new NewsPresenterImpl();
    }

    @Provides
    @NewsScope
    INewsInteractor provideNewsInteractor(){
        return new NewsInteractorImpl();
    }
}
