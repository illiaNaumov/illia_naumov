package com.example.naumov.illia.illianaumov.main.mvp.interactor;

import com.example.naumov.illia.illianaumov.main.MyApp;
import com.example.naumov.illia.illianaumov.main.mvp.model.entities.ExchangeRate;
import com.example.naumov.illia.illianaumov.main.mvp.model.local.SharedPrefsManager;
import com.example.naumov.illia.illianaumov.main.retrofit.CurrencyApi;
import com.example.naumov.illia.illianaumov.main.utils.Constants;
import com.example.naumov.illia.illianaumov.main.utils.Utility;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by illia_naumov.
 */

public class CurrencyRatesInteractorImpl implements ICurrencyRatesInteractor {

    @Inject
    public CurrencyApi currencyApi;
    @Inject
    public SharedPrefsManager sharedPrefsManager;

    public CurrencyRatesInteractorImpl() {
        MyApp.plusCurrencyComponent().inject(this);
    }

    @Override
    public Observable<List<ExchangeRate>> getCurrencyRates(){
        return Observable.just(getDates())
                .flatMap(Observable::from)
                .flatMap(currDate -> currencyApi.getCurrency(currDate))
                .flatMap(privat -> Observable.from(privat.getExchangeRate()))
                .filter(curr -> curr.getCurrency().equals(getCurrency()))
                .toList();
    }

    private List<String> getDates(){
        String beginDateStr = sharedPrefsManager.getString(Constants.SharedPrefs.BEGIN_DATE_KEY, Utility.formatDate(new Date()));
        String endDateStr = sharedPrefsManager.getString(Constants.SharedPrefs.END_DATE_KEY, Utility.formatDate(new Date()));
        return Utility.makeDateList(Utility.parseDate(beginDateStr), Utility.parseDate(endDateStr));
    }

    private String getCurrency(){
        return sharedPrefsManager.getString(Constants.SharedPrefs.CURRENCY_KEY, Constants.Currency.USD);
    }

    @Override
    public void saveCurrencySelection(String currency){
        sharedPrefsManager.setString(Constants.SharedPrefs.CURRENCY_KEY, currency);
    }
}
