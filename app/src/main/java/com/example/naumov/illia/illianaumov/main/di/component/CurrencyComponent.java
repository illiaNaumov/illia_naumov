package com.example.naumov.illia.illianaumov.main.di.component;

import com.example.naumov.illia.illianaumov.main.di.module.CurrencyModule;
import com.example.naumov.illia.illianaumov.main.di.module.NetModule;
import com.example.naumov.illia.illianaumov.main.mvp.presenter.CurrencyRatesPresenterImpl;
import com.example.naumov.illia.illianaumov.main.mvp.view.activity.CurrencyActivity;
import com.example.naumov.illia.illianaumov.main.mvp.view.fragment.DateDialogFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by illia_naumov.
 */

@Component(modules = {CurrencyModule.class, NetModule.class})
@Singleton
public interface CurrencyComponent {
    void inject(CurrencyActivity currencyActivity);
    void inject(CurrencyRatesPresenterImpl presenter);
    void inject(DateDialogFragment dateDialogFragment);
}
