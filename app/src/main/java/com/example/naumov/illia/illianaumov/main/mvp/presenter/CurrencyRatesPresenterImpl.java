package com.example.naumov.illia.illianaumov.main.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.example.naumov.illia.illianaumov.main.MyApp;
import com.example.naumov.illia.illianaumov.main.eventbus.LoadCurrencyEvent;
import com.example.naumov.illia.illianaumov.main.mvp.interactor.ICurrencyRatesInteractor;
import com.example.naumov.illia.illianaumov.main.mvp.model.entities.ExchangeRate;
import com.example.naumov.illia.illianaumov.main.mvp.model.entities.UiCurrency;
import com.example.naumov.illia.illianaumov.main.mvp.model.local.SharedPrefsManager;
import com.example.naumov.illia.illianaumov.main.mvp.view.activity.CurrencyView;
import com.example.naumov.illia.illianaumov.main.retrofit.CurrencyApi;
import com.example.naumov.illia.illianaumov.main.utils.Constants;
import com.example.naumov.illia.illianaumov.main.utils.Utility;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by illia_naumov.
 */

@InjectViewState
public class CurrencyRatesPresenterImpl extends BasePresenter<CurrencyView> implements ICurrencyRatesPresenter {

    private Subscription subscription;

    @Inject
    public ICurrencyRatesInteractor currencyRatesInteractor;

    public CurrencyRatesPresenterImpl() {
        MyApp.plusCurrencyComponent().inject(this);

        EventBus.getDefault().register(this);
    }

    @Override
    public void loadCurrencyData() {
        unsubscribe();
        getViewState().showLoadingDialog();

        subscription = currencyRatesInteractor.getCurrencyRates()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        this::showCurrencies,
                        System.err::println
                );

        unsubscribeOnDestroy(subscription);

    }

    private void showCurrencies(List<UiCurrency> exchangeRates) {
            getViewState().dismissLoadingDialog();
            getViewState().showCurrencyList(exchangeRates);
    }

    private void unsubscribe() {
        if (subscription != null) {
            subscription.unsubscribe();
        }
    }

    @Override
    public void loadCurrentDayCurrency(){
        unsubscribe();
        getViewState().showLoadingDialog();

        subscription = currencyRatesInteractor.getDayCurrency()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::showCurrencies,
                        System.err::println);

        unsubscribeOnDestroy(subscription);

    }

    @Override
    public void saveCurrencySelection(String currency) {
        currencyRatesInteractor.saveCurrencySelection(currency);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(LoadCurrencyEvent event) {
        loadCurrencyData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        EventBus.getDefault().unregister(this);
    }
}
