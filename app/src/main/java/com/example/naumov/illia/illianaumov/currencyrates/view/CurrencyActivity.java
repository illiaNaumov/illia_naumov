package com.example.naumov.illia.illianaumov.currencyrates.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.naumov.illia.illianaumov.MyApp;
import com.example.naumov.illia.illianaumov.R;
import com.example.naumov.illia.illianaumov.currencyrates.domain.ExchangeRate;
import com.example.naumov.illia.illianaumov.currencyrates.domain.Privat;
import com.example.naumov.illia.illianaumov.currencyrates.view.adapter.CurrencyRatesAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CurrencyActivity extends AppCompatActivity implements CurrencyView{

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rv_currency_rates)
    RecyclerView rvCurrencyRates;
    @BindView(R.id.et_date)
    EditText etDate;

    private List<ExchangeRate> currencyList;
    private CurrencyRatesAdapter currencyRatesAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        currencyList = new ArrayList<>();
        currencyRatesAdapter = new CurrencyRatesAdapter(this);
        currencyRatesAdapter.setCurrencyList(currencyList);
        rvCurrencyRates.setAdapter(currencyRatesAdapter);
        rvCurrencyRates.setLayoutManager(new LinearLayoutManager(this));

    }

    @OnClick(R.id.btn_load_currency)
    public void onClick(View view){
        switch(view.getId()){
            case R.id.btn_load_currency:
                getCurrecy();
                break;
        }
    }

    private void getCurrecy(){
        MyApp.getCurrencyApi().getCurrency(etDate.getText().toString()).enqueue(new Callback<Privat>() {
            @Override
            public void onResponse(Call<Privat> call, Response<Privat> response) {
                currencyList.clear();
                currencyList.addAll(response.body().getExchangeRate());
                currencyRatesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Privat> call, Throwable t) {


            }
        });
    }
}
