package com.example.naumov.illia.illianaumov.main.mvp.model.entities;

/**
 * Created by illia_naumov.
 */

public class UiCurrency {
    private String currencyName;
    private double currencyBuyRate;
    private double currencySaleRate;

    public UiCurrency(String currencyName, double currencyBuyRate, double currencySaleRate) {
        this.currencyName = currencyName;
        this.currencyBuyRate = currencyBuyRate;
        this.currencySaleRate = currencySaleRate;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public double getCurrencyBuyRate() {
        return currencyBuyRate;
    }

    public void setCurrencyBuyRate(double currencyBuyRate) {
        this.currencyBuyRate = currencyBuyRate;
    }

    public double getCurrencySaleRate() {
        return currencySaleRate;
    }

    public void setCurrencySaleRate(double currencySaleRate) {
        this.currencySaleRate = currencySaleRate;
    }
}
