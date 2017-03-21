package com.example.naumov.illia.illianaumov;

import android.app.Application;

import com.example.naumov.illia.illianaumov.currencyrates.di.CurrencyComponent;
import com.example.naumov.illia.illianaumov.currencyrates.di.CurrencyModule;
import com.example.naumov.illia.illianaumov.currencyrates.di.DaggerCurrencyComponent;
import com.example.naumov.illia.illianaumov.currencyrates.di.NetModule;
import com.example.naumov.illia.illianaumov.currencyrates.model.retrofit.CurrencyApi;
import com.example.naumov.illia.illianaumov.currencyrates.utils.Constants;
import com.example.naumov.illia.illianaumov.transitionimage.di.component.DaggerNewsManagerComponent;
import com.example.naumov.illia.illianaumov.transitionimage.di.component.NewsManagerComponent;
import com.example.naumov.illia.illianaumov.transitionimage.di.module.NewsManagerModule;

import javax.inject.Inject;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by illia_naumov.
 */

public class MyApp extends Application {
    private static NewsManagerComponent newsManagerComponent;
    private static CurrencyComponent currencyComponent;

    public static NewsManagerComponent getNewsManagerComponent() {
        return newsManagerComponent;
    }

    public static CurrencyComponent getCurrencyComponent() {
        return currencyComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        newsManagerComponent = buildComponent();
        currencyComponent = buildCurrencyComponent();

    }

    private CurrencyComponent buildCurrencyComponent() {
        return DaggerCurrencyComponent.builder()
                .currencyModule(new CurrencyModule())
                .netModule(new NetModule(Constants.Currency.CURRENCY_PB_DOMAIN))
                .build();
    }

    protected NewsManagerComponent buildComponent() {
        return DaggerNewsManagerComponent.builder()
                .newsManagerModule(new NewsManagerModule(this))
                .build();
    }

}
