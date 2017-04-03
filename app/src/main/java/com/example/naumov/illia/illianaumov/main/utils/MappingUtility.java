package com.example.naumov.illia.illianaumov.main.utils;

import com.example.naumov.illia.illianaumov.main.mvp.model.entities.DayCurrency;
import com.example.naumov.illia.illianaumov.main.mvp.model.entities.ExchangeRate;
import com.example.naumov.illia.illianaumov.main.mvp.model.entities.UiCurrency;


/**
 * Created by illia_naumov.
 */

public class MappingUtility {

    public static UiCurrency mapCurrencyRate(ExchangeRate exchangeRate){

        return new UiCurrency(exchangeRate.getCurrency(),
                exchangeRate.getPurchaseRate(),
                exchangeRate.getSaleRate());
    }

    public static UiCurrency mapCurrentDayCurrency(DayCurrency dayCurrency){

        return new UiCurrency(dayCurrency.getCcy(),
                Double.parseDouble(dayCurrency.getBuy()),
                Double.parseDouble(dayCurrency.getSale()));
    }
}
