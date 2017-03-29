package com.example.naumov.illia.illianaumov.main;

import android.app.Application;

import com.example.naumov.illia.illianaumov.main.di.component.AppComponent;
import com.example.naumov.illia.illianaumov.main.di.component.CurrencyComponent;
import com.example.naumov.illia.illianaumov.main.di.component.DaggerAppComponent;
import com.example.naumov.illia.illianaumov.main.di.module.AppModule;
import com.example.naumov.illia.illianaumov.main.di.module.CurrencyModule;
import com.example.naumov.illia.illianaumov.main.di.module.NetModule;
import com.example.naumov.illia.illianaumov.main.di.component.NewsManagerComponent;
import com.example.naumov.illia.illianaumov.main.di.module.NewsManagerModule;
import com.example.naumov.illia.illianaumov.main.utils.Constants;

/**
 * Created by illia_naumov.
 */

public class MyApp extends Application {
    private static AppComponent appComponent;
    private static NewsManagerComponent newsManagerComponent;
    private static CurrencyComponent currencyComponent;

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = buildAppComponent();
    }

    private AppComponent buildAppComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule(Constants.Currency.CURRENCY_PB_DOMAIN, Constants.News.NEWS_BASE_URL))
                .build();
    }

    public static CurrencyComponent plusCurrencyComponent() {
        if(currencyComponent == null) {
            currencyComponent = appComponent.plusCurrencyComponent(new CurrencyModule());
        }

        return currencyComponent;
    }

    public static void clearCurrencyComponent(){
        currencyComponent = null;
    }

    public static NewsManagerComponent plusNewsManagerComponent(){
        if(newsManagerComponent == null){
            newsManagerComponent = appComponent.plusNewsManagerComponent(new NewsManagerModule());
        }

        return newsManagerComponent;
    }

    public static void claerNewsManagerComponent(){
        newsManagerComponent = null;
    }

}
