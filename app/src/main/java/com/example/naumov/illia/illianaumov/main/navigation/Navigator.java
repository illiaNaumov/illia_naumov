package com.example.naumov.illia.illianaumov.main.navigation;

import android.content.Context;
import android.content.Intent;

import com.example.naumov.illia.illianaumov.main.mvp.view.activity.CurrencyActivity;
import com.example.naumov.illia.illianaumov.main.mvp.view.activity.SettingsActivity;
import com.example.naumov.illia.illianaumov.main.mvp.view.activity.TransitionImageFirstActivity;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by illia_naumov.
 */

@Singleton
public class Navigator {

    @Inject
    public Navigator() {
    }

    public void navigateToCurrencies(Context context){
        if(context != null){
            Intent intentToLaunch = CurrencyActivity.getCallingIntent(context);
            context.startActivity(intentToLaunch);
        }
    }

    public void navigateToNews(Context context){
        if(context != null){
            Intent intentToLaunch = TransitionImageFirstActivity.getCallingIntent(context);
            context.startActivity(intentToLaunch);
        }
    }

    public void navigateToSettingActivity(Context context){
        if(context != null) {
            Intent intentToLaunch = SettingsActivity.getCallingIntent(context);
            context.startActivity(intentToLaunch);
        }
    }
}
