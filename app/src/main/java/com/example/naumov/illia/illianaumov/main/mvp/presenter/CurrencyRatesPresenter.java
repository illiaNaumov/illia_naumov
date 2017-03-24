package com.example.naumov.illia.illianaumov.main.mvp.presenter;

import com.example.naumov.illia.illianaumov.main.mvp.view.CurrencyView;

/**
 * Created by illia_naumov.
 */

public interface CurrencyRatesPresenter {
    void setView(CurrencyView currencyView);
    void loadCurrencyData(String date, String currency);
    void destroy();
}
