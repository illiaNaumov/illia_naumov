package com.example.naumov.illia.illianaumov;

import android.app.Application;

import com.example.naumov.illia.illianaumov.di.component.DaggerNewsManagerComponent;
import com.example.naumov.illia.illianaumov.di.component.NewsManagerComponent;
import com.example.naumov.illia.illianaumov.di.module.NewsManagerModule;

/**
 * Created by illia_naumov.
 */

public class MyApp extends Application {
    private static NewsManagerComponent newsManagerComponent;

    public static NewsManagerComponent getNewsManagerComponent() {
        return newsManagerComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        newsManagerComponent = buildComponent();
    }

    protected NewsManagerComponent buildComponent() {
        return DaggerNewsManagerComponent.builder()
                .newsManagerModule(new NewsManagerModule(this))
                .build();
    }
}
