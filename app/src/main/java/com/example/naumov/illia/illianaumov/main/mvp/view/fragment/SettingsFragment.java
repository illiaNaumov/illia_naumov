package com.example.naumov.illia.illianaumov.main.mvp.view.fragment;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;

import com.example.naumov.illia.illianaumov.R;
import com.example.naumov.illia.illianaumov.main.services.AlarmReceiver;

import java.util.Calendar;

import timber.log.Timber;

/**
 * Created by illia_naumov.
 */

public class SettingsFragment extends PreferenceFragment {

    public static final String KEY_PREF_SEND_NOTIFICATION = "prefSendNotification";

    private SharedPreferences.OnSharedPreferenceChangeListener listener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences);

        initOnPreferenceChangedListener();
    }

    private void initOnPreferenceChangedListener() {
        listener = (prefs, key) -> {
            if (key.equals(KEY_PREF_SEND_NOTIFICATION)) {
                if (prefs.getBoolean(KEY_PREF_SEND_NOTIFICATION, false)) {
                    setAlarmNotificationService();
                } else {
                    removeAlarmNotificationService();
                }
            }
        };
    }

    private void setAlarmNotificationService() {
        AlarmManager alarmMgr = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(getActivity(), AlarmReceiver.class);
        PendingIntent alarmIntent = PendingIntent.getBroadcast(getActivity(), 0, intent, 0);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 11);
        calendar.set(Calendar.MINUTE, 0);

        alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                5000, alarmIntent);
    }

    private void removeAlarmNotificationService() {
        AlarmManager alarmMgr = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(getActivity(), AlarmReceiver.class);
        PendingIntent alarmIntent = PendingIntent.getBroadcast(getActivity(), 0, intent, 0);

        if (alarmMgr!= null) {
            alarmMgr.cancel(alarmIntent);
        }

        Timber.e("removeAlarmNotificationService");
    }

    @Override
    public void onResume() {
        super.onResume();

        getPreferenceScreen().getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(listener);
    }

    @Override
    public void onPause() {
        super.onPause();

        getPreferenceScreen().getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(listener);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        listener = null;
    }
}