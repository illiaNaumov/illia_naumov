package com.example.naumov.illia.illianaumov.main.di.module;

import com.example.naumov.illia.illianaumov.main.mvp.presenter.CurrencyRatesPresenter;
import com.example.naumov.illia.illianaumov.main.mvp.presenter.CurrencyRatesPresenterImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by illia_naumov.
 */

@Module
public class CurrencyModule {

    public CurrencyModule() {
    }

    @Provides @Singleton
    CurrencyRatesPresenter provideCurrencyRatesPresenter(){
        return new CurrencyRatesPresenterImpl();
    }
}
