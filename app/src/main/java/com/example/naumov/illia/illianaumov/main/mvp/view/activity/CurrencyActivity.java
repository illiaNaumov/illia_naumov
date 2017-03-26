package com.example.naumov.illia.illianaumov.main.mvp.view.activity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;

import com.example.naumov.illia.illianaumov.main.MyApp;
import com.example.naumov.illia.illianaumov.R;
import com.example.naumov.illia.illianaumov.main.mvp.model.entities.ExchangeRate;
import com.example.naumov.illia.illianaumov.main.mvp.presenter.CurrencyRatesPresenter;
import com.example.naumov.illia.illianaumov.main.mvp.view.adapter.CurrencyRatesAdapter;
import com.example.naumov.illia.illianaumov.main.mvp.view.fragment.DateDialogFragment;
import com.example.naumov.illia.illianaumov.main.utils.Constants;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CurrencyActivity extends AppCompatActivity implements CurrencyView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rv_currency_rates)
    RecyclerView rvCurrencyRates;
//    @BindView(R.id.et_date)
//    EditText etDate;

    @Inject
    public CurrencyRatesPresenter currencyRatesPresenter;



    private List<ExchangeRate> currencyList;
    private CurrencyRatesAdapter currencyRatesAdapter;
    private ProgressDialog progressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency);

        ButterKnife.bind(this);

        MyApp.getCurrencyComponent().inject(this);

        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        currencyRatesPresenter.setView(this);

        currencyList = new ArrayList<>();
        currencyRatesAdapter = new CurrencyRatesAdapter(this);
        currencyRatesAdapter.setCurrencyList(currencyList);
        rvCurrencyRates.setAdapter(currencyRatesAdapter);
        rvCurrencyRates.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_currency, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Date currDate = Calendar.getInstance().getTime();

        switch (item.getItemId()){
            case R.id.btnUsd:
                currencyRatesPresenter.loadCurrencyData(currDate, currDate, Constants.Currency.USD);
                return true;
            case R.id.btnRub:
                currencyRatesPresenter.loadCurrencyData(currDate, currDate, Constants.Currency.RUB);
                return true;
            case R.id.btnEur:
                currencyRatesPresenter.loadCurrencyData(currDate, currDate, Constants.Currency.EUR);
                return true;
        }

        return false;
    }

    @Override
    public void clearRates(){
        currencyList.clear();
    }

    @OnClick(R.id.fabDate)
    public void onFabClicked(){
        DateDialogFragment dateDialogFragment = new DateDialogFragment();
        dateDialogFragment.show(getSupportFragmentManager(), "DateDialogFragment");
    }

    @Override
    public void showCurrencyList(List<ExchangeRate> currencyList) {
        this.currencyList.clear();
        this.currencyList.addAll(currencyList);
        currencyRatesAdapter.notifyDataSetChanged();
    }

    @Override
    public void showLoadingDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.loading_message));
        progressDialog.show();
    }

    @Override
    public void dismissLoadingDialog() {
        if(progressDialog != null){
            progressDialog.dismiss();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        currencyRatesPresenter.destroy();
    }


}
