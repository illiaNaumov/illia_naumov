package com.example.naumov.illia.illianaumov.currencyrates.presenter;

import android.widget.Toast;

import com.example.naumov.illia.illianaumov.MyApp;
import com.example.naumov.illia.illianaumov.currencyrates.di.CurrencyComponent;
import com.example.naumov.illia.illianaumov.currencyrates.domain.Privat;
import com.example.naumov.illia.illianaumov.currencyrates.model.retrofit.CurrencyApi;
import com.example.naumov.illia.illianaumov.currencyrates.view.CurrencyView;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by illia_naumov.
 */

public class CurrencyRatesPresenterImpl implements CurrencyRatesPresenter{

    private CurrencyView currencyView;

    @Inject
    public CurrencyApi currencyApi;

    public CurrencyRatesPresenterImpl() {
        MyApp.getCurrencyComponent().inject(this);
    }

    @Override
    public void setView(CurrencyView currencyView) {
        this.currencyView = currencyView;
    }

    @Override
    public void loadCurrencyData(String date) {
        currencyApi.getCurrency(date).enqueue(new Callback<Privat>() {
            @Override
            public void onResponse(Call<Privat> call, Response<Privat> response) {
                if(isViewAttached()) {
                    currencyView.showCurrencyList(response.body().getExchangeRate());
                }

            }

            @Override
            public void onFailure(Call<Privat> call, Throwable t) {
            }
        });
    }

    private boolean isViewAttached()
    {
        return currencyView != null;
    }

    @Override
    public void destroy() {
        currencyView = null;
    }
}
