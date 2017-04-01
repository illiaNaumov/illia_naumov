package com.example.naumov.illia.illianaumov.main.mvp.presenter;


/**
 * Created by illia_naumov.
 */

public interface ICurrencyRatesPresenter {
    void loadCurrencyData();

    void loadCurrentDayCurrency();

    void saveCurrencySelection(String currency);
}
