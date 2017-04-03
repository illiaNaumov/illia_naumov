package com.example.naumov.illia.illianaumov.main.mvp.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.annotation.Nullable;

import com.example.naumov.illia.illianaumov.main.mvp.view.fragment.SettingsFragment;

/**
 * Created by illia_naumov.
 */

public class SettingsActivity extends PreferenceActivity{


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();

    }

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, SettingsActivity.class);
    }
}
