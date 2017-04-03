package com.example.naumov.illia.illianaumov.main.mvp.view.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.naumov.illia.illianaumov.main.MyApp;
import com.example.naumov.illia.illianaumov.R;
import com.example.naumov.illia.illianaumov.main.mvp.model.entities.UiCurrency;
import com.example.naumov.illia.illianaumov.main.mvp.model.local.SharedPrefsManager;
import com.example.naumov.illia.illianaumov.main.mvp.presenter.CurrencyRatesPresenterImpl;
import com.example.naumov.illia.illianaumov.main.mvp.view.adapter.CurrencyRatesAdapter;
import com.example.naumov.illia.illianaumov.main.mvp.view.fragment.DateDialogFragment;
import com.example.naumov.illia.illianaumov.main.navigation.Navigator;
import com.example.naumov.illia.illianaumov.main.utils.Constants;
import com.example.naumov.illia.illianaumov.main.utils.Utility;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CurrencyActivity extends MvpAppCompatActivity implements CurrencyView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rv_currency_rates)
    RecyclerView rvCurrencyRates;

    @Inject
    Navigator navigator;
    @InjectPresenter
    public CurrencyRatesPresenterImpl currencyRatesPresenter;
    @Inject
    public SharedPrefsManager sharedPrefsManager;



    private List<UiCurrency> currencyList;
    private CurrencyRatesAdapter currencyRatesAdapter;
    private ProgressDialog progressDialog;

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, CurrencyActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency);

        ButterKnife.bind(this);

        MyApp.plusCurrencyComponent().inject(this);

        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        currencyList = new ArrayList<>();
        currencyRatesAdapter = new CurrencyRatesAdapter(this);
        currencyRatesAdapter.setCurrencyList(currencyList);
        rvCurrencyRates.setAdapter(currencyRatesAdapter);
        rvCurrencyRates.setLayoutManager(new LinearLayoutManager(this));

        checkIfStartedFromNotification();
    }

    private void checkIfStartedFromNotification() {
        if(getIntent().hasExtra(Constants.Bundles.IS_FROM_NOTIFICATION)){
            currencyRatesPresenter.saveCurrencySelection(getIntent().getStringExtra(Constants.Bundles.CURRENCY_NAME));
            currencyRatesPresenter.loadCurrentDayCurrency();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_currency, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.btnUsd:
                currencyRatesPresenter.saveCurrencySelection(Constants.Currency.USD);
                currencyRatesPresenter.loadCurrencyData();
                return true;
            case R.id.btnRub:
                currencyRatesPresenter.saveCurrencySelection(Constants.Currency.RUB);
                currencyRatesPresenter.loadCurrencyData();
                return true;
            case R.id.btnEur:
                currencyRatesPresenter.saveCurrencySelection(Constants.Currency.EUR);
                currencyRatesPresenter.loadCurrencyData();
                return true;
            case R.id.btnToday:
                currencyRatesPresenter.loadCurrentDayCurrency();
                return true;
            case R.id.btnLastWeek:
                sharedPrefsManager.setString(Constants.SharedPrefs.BEGIN_DATE_KEY, Utility.formatDate(Utility.getWeekEarlierDate(new Date())));
                sharedPrefsManager.setString(Constants.SharedPrefs.END_DATE_KEY, Utility.formatDate(Calendar.getInstance().getTime()));
                currencyRatesPresenter.loadCurrencyData();
                return true;
            case R.id.btnLastMonth:
                sharedPrefsManager.setString(Constants.SharedPrefs.BEGIN_DATE_KEY,  Utility.formatDate(Utility.getMonthEarlierDate(new Date())));
                sharedPrefsManager.setString(Constants.SharedPrefs.END_DATE_KEY, Utility.formatDate(Calendar.getInstance().getTime()));
                currencyRatesPresenter.loadCurrencyData();
                return true;
            case R.id.settings:
                navigator.navigateToSettingActivity(this);
                return true;

        }

        return false;
    }

    @OnClick(R.id.fabDate)
    public void onFabClicked(){
        DateDialogFragment dateDialogFragment = new DateDialogFragment();
        dateDialogFragment.show(getSupportFragmentManager(), "DateDialogFragment");
    }

    @Override
    public void showCurrencyList(List<UiCurrency> currencyList) {
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

        MyApp.clearCurrencyComponent();
    }

}
