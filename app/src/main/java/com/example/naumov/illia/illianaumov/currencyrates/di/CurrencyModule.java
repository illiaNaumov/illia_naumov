package com.example.naumov.illia.illianaumov.currencyrates.di;

import com.example.naumov.illia.illianaumov.currencyrates.presenter.CurrencyRatesPresenter;
import com.example.naumov.illia.illianaumov.currencyrates.presenter.CurrencyRatesPresenterImpl;

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
