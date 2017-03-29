package com.example.naumov.illia.illianaumov.main.mvp.view.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;

import com.example.naumov.illia.illianaumov.R;
import com.example.naumov.illia.illianaumov.main.MyApp;
import com.example.naumov.illia.illianaumov.main.navigation.Navigator;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.nvMain)
    NavigationView nvMain;
    @Inject
    Navigator navigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        MyApp.getAppComponent().inject(this);

        nvMain.setNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.currency:
                    navigator.navigateToCurrencies(this);
                    return true;
                case R.id.news:
                    navigator.navigateToNews(this);
                    return true;

            }

            return false;
        });

    }


}
