package com.example.naumov.illia.illianaumov.currencyrates.model.retrofit;

import com.example.naumov.illia.illianaumov.currencyrates.domain.Privat;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by illia_naumov.
 */

public interface CurrencyApi {
    @GET("p24api/exchange_rates?json")
    Call<Privat> getCurrency(@Query("date") String date);
}
