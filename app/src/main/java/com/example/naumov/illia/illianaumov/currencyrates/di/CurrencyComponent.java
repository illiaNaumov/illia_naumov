package com.example.naumov.illia.illianaumov.currencyrates.di;

import com.example.naumov.illia.illianaumov.MyApp;
import com.example.naumov.illia.illianaumov.currencyrates.presenter.CurrencyRatesPresenterImpl;
import com.example.naumov.illia.illianaumov.currencyrates.view.CurrencyActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by illia_naumov.
 */

@Component(modules = {CurrencyModule.class, NetModule.class})
@Singleton
public interface CurrencyComponent {
    void inject(CurrencyActivity currencyActivity);
    void inject(CurrencyRatesPresenterImpl presenter);
}
