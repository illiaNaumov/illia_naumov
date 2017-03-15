package com.example.naumov.illia.illianaumov.di.module;

import android.content.Context;
import android.support.v4.content.res.ResourcesCompat;

import com.example.naumov.illia.illianaumov.R;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by illia_naumov.
 */

@Module
public class NewsManagerModule {

    Context context;

    public NewsManagerModule(Context context) {
        this.context = context;
    }

    @Provides @Singleton
    String provideNewsPost(){
        return context.getString(R.string.oop_test_text);
    }

    @Provides @Singleton
    public Context provideAppContext(){
        return context;
    }
}
