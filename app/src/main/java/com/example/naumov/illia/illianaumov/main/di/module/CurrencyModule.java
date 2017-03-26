package com.example.naumov.illia.illianaumov.main.di.module;

import android.content.Context;

import com.example.naumov.illia.illianaumov.main.MyApp;
import com.example.naumov.illia.illianaumov.main.mvp.model.local.SharedPrefsManager;
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

    private Context mContext;

    public CurrencyModule(MyApp myApp) {
        mContext = myApp.getBaseContext();
    }

    @Provides @Singleton
    CurrencyRatesPresenter provideCurrencyRatesPresenter(){
        return new CurrencyRatesPresenterImpl();
    }

    @Provides @Singleton
    SharedPrefsManager provideSharedPrefsManager(){
        return new SharedPrefsManager(mContext);
    }
}
