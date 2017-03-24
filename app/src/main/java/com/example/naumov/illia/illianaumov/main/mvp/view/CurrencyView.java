package com.example.naumov.illia.illianaumov.main.mvp.view;

import com.example.naumov.illia.illianaumov.main.mvp.model.entities.ExchangeRate;

import java.util.List;

/**
 * Created by illia_naumov.
 */

public interface CurrencyView {
    void showCurrencyList(List<ExchangeRate> currencyList);
    void showLoadingDialog();
    void dismissLoadingDialog();
    void showCurrency(ExchangeRate exchangeRate);
    void clearRates();
}
