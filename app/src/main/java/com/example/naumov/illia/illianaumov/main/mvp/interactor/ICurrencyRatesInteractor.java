package com.example.naumov.illia.illianaumov.main.mvp.interactor;

import com.example.naumov.illia.illianaumov.main.mvp.model.entities.DayCurrency;
import com.example.naumov.illia.illianaumov.main.mvp.model.entities.ExchangeRate;
import com.example.naumov.illia.illianaumov.main.mvp.model.entities.UiCurrency;

import java.util.List;

import rx.Observable;

/**
 * Created by illia_naumov.
 */

public interface ICurrencyRatesInteractor {
    Observable<List<UiCurrency>> getCurrencyRates();

    Observable<List<UiCurrency>> getDayCurrency();

    void saveCurrencySelection(String currency);
}
