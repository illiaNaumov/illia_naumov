package com.example.naumov.illia.illianaumov;

import android.app.Application;

import com.example.naumov.illia.illianaumov.currencyrates.model.retrofit.CurrencyApi;
import com.example.naumov.illia.illianaumov.currencyrates.utils.Constants;
import com.example.naumov.illia.illianaumov.transitionimage.di.component.DaggerNewsManagerComponent;
import com.example.naumov.illia.illianaumov.transitionimage.di.component.NewsManagerComponent;
import com.example.naumov.illia.illianaumov.transitionimage.di.module.NewsManagerModule;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by illia_naumov.
 */

public class MyApp extends Application {
    private static NewsManagerComponent newsManagerComponent;
    private static CurrencyApi currencyApi;

    public static NewsManagerComponent getNewsManagerComponent() {
        return newsManagerComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        newsManagerComponent = buildComponent();

        currencyApi = createCurrencyApi();

    }

    protected NewsManagerComponent buildComponent() {
        return DaggerNewsManagerComponent.builder()
                .newsManagerModule(new NewsManagerModule(this))
                .build();
    }

    protected CurrencyApi createCurrencyApi(){
        return new Retrofit.Builder()
                .baseUrl(Constants.Currency.CURRENCY_PB_DOMAIN)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(CurrencyApi.class);
    }

    public static CurrencyApi getCurrencyApi() {
        return currencyApi;
    }
}
