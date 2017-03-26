package com.example.naumov.illia.illianaumov.main.mvp.model.local;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by illia_naumov.
 */

public class SharedPrefsManager
{
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    public final String PREFS_NAME = "om.example.naumov.illia.illianaumov.Preferences";

    public SharedPrefsManager(final Context context)
    {
        preferences = context.getSharedPreferences(PREFS_NAME, Activity.MODE_PRIVATE);
        editor = preferences.edit();
    }


    public String getString(final String key, final String defValue)
    {
        return preferences.getString(key, defValue);
    }

    public void setString(final String key, final String value)
    {

        editor.putString(key, value);
        editor.apply();

    }
}