package com.example.naumov.illia.illianaumov.view;

import android.os.Build;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.naumov.illia.illianaumov.MyApp;
import com.example.naumov.illia.illianaumov.R;
import com.example.naumov.illia.illianaumov.adapter.TestStringAdapter;
import com.example.naumov.illia.illianaumov.domain.NewsPost;
import com.example.naumov.illia.illianaumov.utils.Constants;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TransitionImageSecondActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.iv_second)
    ImageView ivSecond;
    @BindView(R.id.txt_news_post)
    TextView txtNewsPost;

    @Inject
    String newsPostText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition_image_second);

        ButterKnife.bind(this);

        MyApp.getNewsManagerComponent().inject(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ivSecond.setTransitionName("transitionName");
        }

        NewsPost newsPost = getIntent().getParcelableExtra("news_post");
        Picasso.with(this).load(newsPost.getImageUrl()).into(ivSecond);

        txtNewsPost.setText(newsPostText);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
