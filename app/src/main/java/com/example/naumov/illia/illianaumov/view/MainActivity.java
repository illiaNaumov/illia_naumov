package com.example.naumov.illia.illianaumov.view;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.example.naumov.illia.illianaumov.R;
import com.example.naumov.illia.illianaumov.adapter.TestStringAdapter;
import com.example.naumov.illia.illianaumov.domain.NewsPost;
import com.example.naumov.illia.illianaumov.loader.NewsLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.recycler_view)
    RecyclerView rvTestString;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private List<NewsPost> newsPostList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        newsPostList = NewsLoader.generateNews();

        rvTestString.setLayoutManager(new LinearLayoutManager(this));
        rvTestString.setAdapter(new TestStringAdapter(this, newsPostList));
    }

    @Override
    public void onClick(final View view) {

        ActivityOptions transitionActivityOptions;
        if(android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            ImageView imageView = (ImageView) view.findViewById(R.id.image_view);
            transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, imageView,
                    "transitionName");

            Intent intent = new Intent(MainActivity.this, TransitionImageSecondActivity.class);
            intent.putExtra("news_post", newsPostList.get(rvTestString.getChildAdapterPosition(view)));
            startActivity(intent,
                    transitionActivityOptions.toBundle());
        }
    }
}
