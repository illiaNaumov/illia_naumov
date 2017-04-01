package com.example.naumov.illia.illianaumov.main.mvp.view.activity;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.naumov.illia.illianaumov.main.mvp.model.entities.ExchangeRate;
import com.example.naumov.illia.illianaumov.main.mvp.model.entities.UiCurrency;

import java.util.List;

/**
 * Created by illia_naumov.
 */

@StateStrategyType(AddToEndSingleStrategy.class)
public interface CurrencyView extends MvpView {
    void showCurrencyList(List<UiCurrency> currencyList);
    void showLoadingDialog();
    void dismissLoadingDialog();
}
