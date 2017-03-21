package com.example.naumov.illia.illianaumov.currencyrates.presenter;

import com.example.naumov.illia.illianaumov.currencyrates.view.CurrencyView;

/**
 * Created by illia_naumov.
 */

public interface CurrencyRatesPresenter {
    void setView(CurrencyView currencyView);
    void loadCurrencyData(String date);
    void destroy();
}
