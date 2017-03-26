package com.example.naumov.illia.illianaumov.main.mvp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;

import com.example.naumov.illia.illianaumov.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
//    @BindView(R.id.toolbar)
//    Toolbar toolbar;

    @BindView(R.id.nvMain)
    NavigationView nvMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

//        setSupportActionBar(toolbar);

        nvMain.setNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.currency:
                    Intent intent = new Intent(MainActivity.this, CurrencyActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.news:
                    Intent intent1 = new Intent(this, TransitionImageFirstActivity.class);
                    startActivity(intent1);
                    return true;

            }

            return false;
        });

    }


}
