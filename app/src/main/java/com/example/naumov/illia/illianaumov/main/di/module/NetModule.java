package com.example.naumov.illia.illianaumov.main.di.module;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.naumov.illia.illianaumov.main.retrofit.CurrencyApi;
import com.example.naumov.illia.illianaumov.main.retrofit.NewsApi;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by illia_naumov.
 */

@Module
public class NetModule {

    private String mBaseCurrencyUrl;
    private String mBaseNewsUrl;

    public NetModule(String mBaseCurrencyUrl, String mBaseNewsUrl) {
        this.mBaseCurrencyUrl = mBaseCurrencyUrl;
        this.mBaseNewsUrl = mBaseNewsUrl;
    }

    // Dagger will only look for methods annotated with @Provides
    @Provides
    @Singleton
    // Application reference must come from AppModule.class
    SharedPreferences providesSharedPreferences(Application application) {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        return new OkHttpClient();
    }

    @Provides
    @Singleton
    CurrencyApi provideCurrencyApi(Gson gson, OkHttpClient okHttpClient){
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(mBaseCurrencyUrl)
                .client(okHttpClient)
                .build()
                .create(CurrencyApi.class);
    }

    @Provides
    @Singleton
    NewsApi provideNewsApi(Gson gson, OkHttpClient okHttpClient){
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(mBaseNewsUrl)
                .client(okHttpClient)
                .build()
                .create(NewsApi.class);
    }
}