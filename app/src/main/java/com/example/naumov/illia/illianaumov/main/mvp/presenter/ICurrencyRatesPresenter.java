package com.example.naumov.illia.illianaumov.main.mvp.presenter;

import com.example.naumov.illia.illianaumov.main.mvp.view.activity.CurrencyView;

import java.util.Date;

/**
 * Created by illia_naumov.
 */

public interface ICurrencyRatesPresenter {
    void setView(CurrencyView currencyView);
    void loadCurrencyData();
    void saveCurrencySelection(String currency);

    void destroy();
}
