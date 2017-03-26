package com.example.naumov.illia.illianaumov.main.mvp.presenter;

import android.util.Log;

import com.example.naumov.illia.illianaumov.main.MyApp;
import com.example.naumov.illia.illianaumov.main.mvp.model.entities.ExchangeRate;
import com.example.naumov.illia.illianaumov.main.mvp.view.activity.CurrencyView;
import com.example.naumov.illia.illianaumov.main.retrofit.CurrencyApi;
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

public class CurrencyRatesPresenterImpl implements CurrencyRatesPresenter{

    private static final String TAG = "CurrencyRatesPresenterI";

    private CurrencyView currencyView;
    private Subscription subscription;

    @Inject
    public CurrencyApi currencyApi;

    private List<ExchangeRate> currencyList;



    public CurrencyRatesPresenterImpl() {
        MyApp.getCurrencyComponent().inject(this);

        currencyList = new ArrayList<>();
    }

    @Override
    public void setView(CurrencyView currencyView) {
        this.currencyView = currencyView;
    }

    @Override
    public void loadCurrencyData(Date beginDate, Date endDate, String currency) {
        currencyView.clearRates();
        currencyList.clear();
        unsubscribe();
        currencyView.showLoadingDialog();

        List<String> dates = Utility.makeDateList(beginDate, endDate);
        Log.i(TAG, "dates.size = " + dates.size());

        subscription = Observable.just(dates).flatMap(Observable::from)
                .flatMap(currDate -> currencyApi.getCurrency(currDate))
                .flatMap(privat -> Observable.from(privat.getExchangeRate()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(curr -> curr.getCurrency().equals(currency))
                .subscribe(
                        this::addCurrencyRate,
                        System.err::println,
                        this::showCurrencies
                );
    }



    private void addCurrencyRate(ExchangeRate exchangeRate){
        currencyList.add(exchangeRate);
    }

    private void showCurrencies(){
        if(isViewAttached()) {
            currencyView.dismissLoadingDialog();
            currencyView.showCurrencyList(currencyList);
        }
    }

    private boolean isViewAttached()
    {
        return currencyView != null;
    }

    private void unsubscribe(){
        if(subscription != null) {
            subscription.unsubscribe();
        }
    }

    @Override
    public void destroy() {
        currencyView = null;

        unsubscribe();
    }
}
