package com.example.naumov.illia.illianaumov.main.mvp.view;

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
import com.example.naumov.illia.illianaumov.main.utils.Constants;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CurrencyActivity extends AppCompatActivity implements CurrencyView, DatePickerDialog.OnDateSetListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rv_currency_rates)
    RecyclerView rvCurrencyRates;
//    @BindView(R.id.et_date)
//    EditText etDate;

    @Inject
    public CurrencyRatesPresenter currencyRatesPresenter;


    private Calendar dateAndTime = Calendar.getInstance();
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
        String currDate = "01.12.2015";

        switch (item.getItemId()){
            case R.id.btnUsd:
                currencyRatesPresenter.loadCurrencyData(currDate, Constants.Currency.USD);
                return true;
            case R.id.btnRub:
                currencyRatesPresenter.loadCurrencyData(currDate, Constants.Currency.RUB);
                return true;
            case R.id.btnEur:
                currencyRatesPresenter.loadCurrencyData(currDate, Constants.Currency.EUR);
                return true;
        }

        return false;
    }

    @Override
    public void clearRates(){
        currencyList.clear();
    }

//    @OnClick({R.id.btn_load_currency, R.id.et_date})
//    public void onClick(View view){
//        switch(view.getId()){
//            case R.id.btn_load_currency:
//                currencyRatesPresenter.loadCurrencyData(etDate.getText().toString(), Constants.Currency.USD);
//                break;
//            case R.id.et_date:
//                showDatePickerDialog();
//                break;
//        }
//    }

    public void showDatePickerDialog() {
        new DatePickerDialog(CurrencyActivity.this, this,
                dateAndTime.get(Calendar.YEAR),
                dateAndTime.get(Calendar.MONTH),
                dateAndTime.get(Calendar.DAY_OF_MONTH))
                .show();
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
    public void showCurrency(ExchangeRate exchangeRate) {
        currencyList.add(exchangeRate);
        currencyRatesAdapter.notifyDataSetChanged();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        currencyRatesPresenter.destroy();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar resultCalend = Calendar.getInstance();
        resultCalend.set(year, month, dayOfMonth);
//        etDate.setText(new SimpleDateFormat("dd.MM.yyyy").format(resultCalend.getTime()));
    }
}
