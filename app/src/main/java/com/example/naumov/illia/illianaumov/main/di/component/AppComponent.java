package com.example.naumov.illia.illianaumov.main.di.component;

import com.example.naumov.illia.illianaumov.main.di.module.AppModule;
import com.example.naumov.illia.illianaumov.main.di.module.CurrencyModule;
import com.example.naumov.illia.illianaumov.main.di.module.NetModule;
import com.example.naumov.illia.illianaumov.main.di.module.NewsManagerModule;
import com.example.naumov.illia.illianaumov.main.mvp.view.activity.MainActivity;
import com.example.naumov.illia.illianaumov.main.mvp.view.fragment.DateDialogFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by illia_naumov.
 */

@Component(modules = {AppModule.class, NetModule.class})
@Singleton
public interface AppComponent {
    CurrencyComponent plusCurrencyComponent(CurrencyModule currencyModule);
    NewsManagerComponent plusNewsManagerComponent(NewsManagerModule newsManagerModule);
    void inject(MainActivity mainActivity);
    void inject(DateDialogFragment dateDialogFragment);
}
