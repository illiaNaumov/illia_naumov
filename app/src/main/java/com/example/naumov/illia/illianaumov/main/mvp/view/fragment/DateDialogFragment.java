package com.example.naumov.illia.illianaumov.main.mvp.view.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.naumov.illia.illianaumov.R;
import com.example.naumov.illia.illianaumov.main.MyApp;
import com.example.naumov.illia.illianaumov.main.mvp.presenter.CurrencyRatesPresenter;
import com.example.naumov.illia.illianaumov.main.mvp.presenter.CurrencyRatesPresenterImpl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by illia_naumov.
 */

public class DateDialogFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private static final int BEGIN_DATE_CHOSEN = 1;
    private static final int END_DATE_CHOSEN = 2;

    @Inject
    CurrencyRatesPresenter currencyRatesPresenter;

    @BindView(R.id.etBeginDate)
    EditText etBeginDate;
    @BindView(R.id.etEndDate)
    EditText etEndDate;

    private Calendar dateAndTime = Calendar.getInstance();
    private Date beginDate;
    private Date endDate;
    private int dateChosen = -1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MyApp.getCurrencyComponent().inject(this);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View view = LayoutInflater.from(getContext())
                .inflate(R.layout.fragment_dialog_date, null);

        ButterKnife.bind(this, view);

        return new AlertDialog.Builder(getActivity())
                .setTitle("Choose date range")
                .setView(view)
                .setPositiveButton("OK",
                        (dialog, whichButton) -> {
                            currencyRatesPresenter.loadCurrencyData(beginDate, endDate, "USD");
                        }
                )
                .setNegativeButton("Cancel",
                        (dialog, whichButton) -> {
                        }
                )
                .create();
    }


    @OnClick(R.id.etBeginDate)
    public void onBeginDateCLicked() {
        dateChosen = BEGIN_DATE_CHOSEN;
        showDatePickerDialog();
    }

    @OnClick(R.id.etEndDate)
    public void onEndDateClicked() {
        dateChosen = END_DATE_CHOSEN;
        showDatePickerDialog();
    }

    public void showDatePickerDialog() {
        new DatePickerDialog(getContext(), this,
                dateAndTime.get(Calendar.YEAR),
                dateAndTime.get(Calendar.MONTH),
                dateAndTime.get(Calendar.DAY_OF_MONTH))
                .show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar resultCalend = Calendar.getInstance();
        resultCalend.set(year, month, dayOfMonth);

        setDate(resultCalend.getTime());

        dateChosen = -1;
    }


    private void setDate(Date date) {
        switch (dateChosen) {
            case BEGIN_DATE_CHOSEN:
                beginDate = date;
                showDate(etBeginDate, beginDate);
                break;
            case END_DATE_CHOSEN:
                endDate = date;
                showDate(etEndDate, date);
                break;
        }
    }

    private void showDate(EditText etDate, Date date) {
        etDate.setText(new SimpleDateFormat("dd.MM.yyyy", Locale.US).format(date));
    }
}
