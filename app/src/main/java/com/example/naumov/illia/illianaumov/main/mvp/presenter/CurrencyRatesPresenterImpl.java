package com.example.naumov.illia.illianaumov.main.mvp.presenter;

import com.example.naumov.illia.illianaumov.main.MyApp;
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
    public CurrencyApi currencyApi;
    @Inject
    public SharedPrefsManager sharedPrefsManager;

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
    public void loadCurrencyData() {
        currencyView.clearRates();
        currencyList.clear();
        unsubscribe();
        currencyView.showLoadingDialog();

        String beginDateStr = sharedPrefsManager.getString(Constants.SharedPrefs.BEGIN_DATE_KEY, Utility.formatDate(new Date()));
        String endDateStr = sharedPrefsManager.getString(Constants.SharedPrefs.END_DATE_KEY, Utility.formatDate(new Date()));
        List<String> dates = Utility.makeDateList(Utility.parseDate(beginDateStr), Utility.parseDate(endDateStr));
        String currency = sharedPrefsManager.getString(Constants.SharedPrefs.CURRENCY_KEY, Constants.Currency.USD);

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


    private void addCurrencyRate(ExchangeRate exchangeRate) {
        currencyList.add(exchangeRate);
    }

    private void showCurrencies() {
        if (isViewAttached()) {
            currencyView.dismissLoadingDialog();
            currencyView.showCurrencyList(currencyList);
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
        sharedPrefsManager.setString(Constants.SharedPrefs.CURRENCY_KEY, currency);
    }

    @Override
    public void destroy() {
        currencyView = null;

        unsubscribe();
    }
}
