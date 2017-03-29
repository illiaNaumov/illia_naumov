package com.example.naumov.illia.illianaumov.main.di.module;


import com.example.naumov.illia.illianaumov.main.di.scope.CurrencyScope;
import com.example.naumov.illia.illianaumov.main.mvp.interactor.CurrencyRatesInteractorImpl;
import com.example.naumov.illia.illianaumov.main.mvp.interactor.ICurrencyRatesInteractor;
import com.example.naumov.illia.illianaumov.main.mvp.presenter.ICurrencyRatesPresenter;
import com.example.naumov.illia.illianaumov.main.mvp.presenter.CurrencyRatesPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by illia_naumov.
 */

@Module
public class CurrencyModule {

    @Provides @CurrencyScope
    ICurrencyRatesPresenter provideCurrencyRatesPresenter(){
        return new CurrencyRatesPresenterImpl();
    }

    @Provides @CurrencyScope
    ICurrencyRatesInteractor provideCurrencyRatesInteractor(){
        return new CurrencyRatesInteractorImpl();
    }
}
