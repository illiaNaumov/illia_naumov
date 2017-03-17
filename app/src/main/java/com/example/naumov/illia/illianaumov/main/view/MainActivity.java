package com.example.naumov.illia.illianaumov.main.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.naumov.illia.illianaumov.R;
import com.example.naumov.illia.illianaumov.currencyrates.view.CurrencyActivity;
import com.example.naumov.illia.illianaumov.transitionimage.view.TransitionImageFirstActivity;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

    }

    @OnClick({R.id.button1, R.id.button2, R.id.button3})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.button1:
                Intent intent = new Intent(this, TransitionImageFirstActivity.class);
                startActivity(intent);
                break;
            case R.id.button2:
                Intent intent1 = new Intent(this, CurrencyActivity.class);
                startActivity(intent1);
                break;
        }
    }
}
