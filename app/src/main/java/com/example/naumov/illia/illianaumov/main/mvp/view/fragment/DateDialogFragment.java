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
import com.example.naumov.illia.illianaumov.main.eventbus.LoadCurrencyEvent;
import com.example.naumov.illia.illianaumov.main.mvp.model.local.SharedPrefsManager;
import com.example.naumov.illia.illianaumov.main.mvp.presenter.ICurrencyRatesPresenter;
import com.example.naumov.illia.illianaumov.main.utils.Constants;
import com.example.naumov.illia.illianaumov.main.utils.Utility;

import org.greenrobot.eventbus.EventBus;

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
    SharedPrefsManager sharedPrefsManager;

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

        MyApp.getAppComponent().inject(this);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View view = LayoutInflater.from(getContext())
                .inflate(R.layout.fragment_dialog_date, null);

        ButterKnife.bind(this, view);

        setDates();

        return new AlertDialog.Builder(getActivity())
                .setTitle("Choose date range")
                .setView(view)
                .setPositiveButton("OK",
                        (dialog, whichButton) -> {

                            sharedPrefsManager.setString(Constants.SharedPrefs.BEGIN_DATE_KEY, Utility.formatDate(beginDate));
                            sharedPrefsManager.setString(Constants.SharedPrefs.END_DATE_KEY, Utility.formatDate(endDate));
                            EventBus.getDefault().post(new LoadCurrencyEvent());
                        }
                )
                .setNegativeButton("Cancel",
                        (dialog, whichButton) -> {
                        }
                )
                .create();
    }

    private void setDates() {
        beginDate = Utility.parseDate(sharedPrefsManager.getString(Constants.SharedPrefs.BEGIN_DATE_KEY,
                Utility.formatDate(Calendar.getInstance().getTime())));

        endDate = Utility.parseDate(sharedPrefsManager.getString(Constants.SharedPrefs.END_DATE_KEY,
                Utility.formatDate(Calendar.getInstance().getTime())));

        etBeginDate.setText(sharedPrefsManager.getString(Constants.SharedPrefs.BEGIN_DATE_KEY,
                Utility.formatDate(Calendar.getInstance().getTime())));

        etEndDate.setText(sharedPrefsManager.getString(Constants.SharedPrefs.END_DATE_KEY,
                Utility.formatDate(Calendar.getInstance().getTime())));
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
