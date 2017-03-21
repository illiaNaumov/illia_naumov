package com.example.naumov.illia.illianaumov.currencyrates.view;

import com.example.naumov.illia.illianaumov.currencyrates.domain.ExchangeRate;

import java.util.List;

/**
 * Created by illia_naumov.
 */

public interface CurrencyView {
    void showCurrencyList(List<ExchangeRate> currencyList);
}
