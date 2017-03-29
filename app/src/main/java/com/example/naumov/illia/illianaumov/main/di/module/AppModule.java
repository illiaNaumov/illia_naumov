package com.example.naumov.illia.illianaumov.main.di.module;

import android.content.Context;

import com.example.naumov.illia.illianaumov.main.MyApp;
import com.example.naumov.illia.illianaumov.main.mvp.model.local.SharedPrefsManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by illia_naumov.
 */

@Module
public class AppModule {
    private Context mContext;

    public AppModule(MyApp myapp) {
        this.mContext = myapp.getBaseContext();
    }

    @Provides
    @Singleton
    Context provideContext(){
        return mContext;
    }

    @Provides @Singleton
    SharedPrefsManager provideSharedPrefsManager(){
        return new SharedPrefsManager(mContext);
    }
}
