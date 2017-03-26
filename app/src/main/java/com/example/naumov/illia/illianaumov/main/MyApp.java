package com.example.naumov.illia.illianaumov.main;

import android.app.Application;

import com.example.naumov.illia.illianaumov.main.di.component.CurrencyComponent;
import com.example.naumov.illia.illianaumov.main.di.component.DaggerCurrencyComponent;
import com.example.naumov.illia.illianaumov.main.di.module.CurrencyModule;
import com.example.naumov.illia.illianaumov.main.di.module.NetModule;
import com.example.naumov.illia.illianaumov.main.di.component.DaggerNewsManagerComponent;
import com.example.naumov.illia.illianaumov.main.di.component.NewsManagerComponent;
import com.example.naumov.illia.illianaumov.main.di.module.NewsManagerModule;
import com.example.naumov.illia.illianaumov.main.utils.Constants;

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
                .currencyModule(new CurrencyModule(this))
                .netModule(new NetModule(Constants.Currency.CURRENCY_PB_DOMAIN))
                .build();
    }

    protected NewsManagerComponent buildComponent() {
        return DaggerNewsManagerComponent.builder()
                .newsManagerModule(new NewsManagerModule(this))
                .netModule(new NetModule(Constants.News.NEWS_BASE_URL))
                .build();
    }

}
