package com.example.naumov.illia.illianaumov.main.retrofit;

import com.example.naumov.illia.illianaumov.main.mvp.model.entities.DayCurrency;
import com.example.naumov.illia.illianaumov.main.mvp.model.entities.Privat;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by illia_naumov.
 */

public interface CurrencyApi {
    @GET("p24api/exchange_rates?json")
    Observable<Privat> getCurrency(@Query("date") String date);

    @GET("p24api/pubinfo?json&exchange&coursid=4")
    Observable<List<DayCurrency>> getCurrentDayCurrencies();


}
