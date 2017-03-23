package com.example.naumov.illia.illianaumov.transitionimage.di.module;

import com.example.naumov.illia.illianaumov.transitionimage.mvp.presenter.TransitionImageFirstActivityPresenterImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by illia_naumov.
 */

@Module
public class PresentersModule {
    @Provides @Singleton
    TransitionImageFirstActivityPresenterImpl providePresenter(){
        return new TransitionImageFirstActivityPresenterImpl();
    }
}
