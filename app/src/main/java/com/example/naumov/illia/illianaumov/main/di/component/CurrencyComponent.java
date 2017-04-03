package com.example.naumov.illia.illianaumov.main.di.component;

import com.example.naumov.illia.illianaumov.main.di.module.CurrencyModule;
import com.example.naumov.illia.illianaumov.main.di.module.NetModule;
import com.example.naumov.illia.illianaumov.main.di.scope.CurrencyScope;
import com.example.naumov.illia.illianaumov.main.mvp.interactor.CurrencyRatesInteractorImpl;
import com.example.naumov.illia.illianaumov.main.mvp.presenter.CurrencyRatesPresenterImpl;
import com.example.naumov.illia.illianaumov.main.mvp.view.activity.CurrencyActivity;
import com.example.naumov.illia.illianaumov.main.mvp.view.activity.MainActivity;
import com.example.naumov.illia.illianaumov.main.mvp.view.fragment.DateDialogFragment;
import com.example.naumov.illia.illianaumov.main.services.CurrencyAlarmService;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Subcomponent;

/**
 * Created by illia_naumov.
 */

@Subcomponent(modules = CurrencyModule.class)
@CurrencyScope
public interface CurrencyComponent {
    void inject(CurrencyActivity currencyActivity);
    void inject(CurrencyRatesPresenterImpl presenter);
    void inject(CurrencyRatesInteractorImpl currencyRatesInteractor);
    void inject(CurrencyAlarmService currencyAlarmService);
}
