package com.example.naumov.illia.illianaumov.main.mvp.presenter;

import com.example.naumov.illia.illianaumov.main.MyApp;
import com.example.naumov.illia.illianaumov.main.mvp.model.entities.ExchangeRate;
import com.example.naumov.illia.illianaumov.main.mvp.view.CurrencyView;
import com.example.naumov.illia.illianaumov.main.retrofit.CurrencyApi;

import java.util.ArrayList;
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

    private CurrencyView currencyView;
    private Subscription subscription;

    @Inject
    public CurrencyApi currencyApi;


    public CurrencyRatesPresenterImpl() {
        MyApp.getCurrencyComponent().inject(this);
    }

    @Override
    public void setView(CurrencyView currencyView) {
        this.currencyView = currencyView;
    }

    @Override
    public void loadCurrencyData(String date, String currency) {
        currencyView.clearRates();
        unsubscribe();
        currencyView.showLoadingDialog();

        List<String> dates = new ArrayList<>();
        dates.add("01.12.2015");
        dates.add("02.12.2015");
        dates.add("03.12.2015");
        dates.add("04.12.2015");
        dates.add("05.12.2015");
        dates.add("06.12.2015");
        dates.add("07.12.2015");
        dates.add("08.12.2015");

        subscription = Observable.just(dates).flatMap(Observable::from)
                .flatMap(currDate -> currencyApi.getCurrency(currDate))
                .flatMap(privat -> Observable.from(privat.getExchangeRate()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(curr -> curr.getCurrency().equals(currency))
                .subscribe(
                        this::showCurrencyRate,
                        System.err::println,
                        currencyView::dismissLoadingDialog
                );
    }



    private void showCurrencyRate(ExchangeRate exchangeRate){
        currencyView.showCurrency(exchangeRate);
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
