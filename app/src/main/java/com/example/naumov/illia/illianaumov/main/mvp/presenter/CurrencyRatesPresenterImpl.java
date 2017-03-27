package com.example.naumov.illia.illianaumov.main.mvp.presenter;

import com.example.naumov.illia.illianaumov.main.MyApp;
import com.example.naumov.illia.illianaumov.main.mvp.interactor.ICurrencyRatesInteractor;
import com.example.naumov.illia.illianaumov.main.mvp.model.entities.ExchangeRate;
import com.example.naumov.illia.illianaumov.main.mvp.model.local.SharedPrefsManager;
import com.example.naumov.illia.illianaumov.main.mvp.view.activity.CurrencyView;
import com.example.naumov.illia.illianaumov.main.retrofit.CurrencyApi;
import com.example.naumov.illia.illianaumov.main.utils.Constants;
import com.example.naumov.illia.illianaumov.main.utils.Utility;

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

public class CurrencyRatesPresenterImpl implements ICurrencyRatesPresenter {

    private CurrencyView currencyView;
    private Subscription subscription;

    @Inject
    public ICurrencyRatesInteractor currencyRatesInteractor;

    public CurrencyRatesPresenterImpl() {
        MyApp.getCurrencyComponent().inject(this);

    }

    @Override
    public void setView(CurrencyView currencyView) {
        this.currencyView = currencyView;
    }

    @Override
    public void loadCurrencyData() {
        unsubscribe();
        currencyView.showLoadingDialog();

        subscription = currencyRatesInteractor.getCurrencyRates()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        this::showCurrencies,
                        System.err::println
                );

    }


    private void showCurrencies(List<ExchangeRate> exchangeRates) {
        if (isViewAttached()) {
            currencyView.dismissLoadingDialog();
            currencyView.showCurrencyList(exchangeRates);
        }
    }


    private boolean isViewAttached() {
        return currencyView != null;
    }

    private void unsubscribe() {
        if (subscription != null) {
            subscription.unsubscribe();
        }
    }

    @Override
    public void saveCurrencySelection(String currency) {
        currencyRatesInteractor.saveCurrencySelection(currency);
    }

    @Override
    public void destroy() {
        currencyView = null;

        unsubscribe();
    }
}
