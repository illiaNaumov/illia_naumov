package com.example.naumov.illia.illianaumov.main.mvp.interactor;

import com.example.naumov.illia.illianaumov.main.mvp.model.entities.ExchangeRate;

import java.util.List;

import rx.Observable;

/**
 * Created by illia_naumov.
 */

public interface ICurrencyRatesInteractor {
    Observable<List<ExchangeRate>> getCurrencyRates();

    void saveCurrencySelection(String currency);
}
