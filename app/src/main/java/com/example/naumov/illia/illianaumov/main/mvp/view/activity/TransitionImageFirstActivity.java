package com.example.naumov.illia.illianaumov.main.mvp.view.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.naumov.illia.illianaumov.R;
import com.example.naumov.illia.illianaumov.main.MyApp;
import com.example.naumov.illia.illianaumov.main.mvp.model.entities.Article;
import com.example.naumov.illia.illianaumov.main.mvp.presenter.INewsPresenter;
import com.example.naumov.illia.illianaumov.main.mvp.view.ItemClickSupport;
import com.example.naumov.illia.illianaumov.main.mvp.view.adapter.NewsAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TransitionImageFirstActivity extends AppCompatActivity implements INewsView {

    @BindView(R.id.recycler_view)
    RecyclerView rvTestString;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Inject
    INewsPresenter newsPresenter;

    private NewsAdapter newsAdapter;
    private List<Article> newsPostList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition_image_first);

        ButterKnife.bind(this);

        MyApp.getNewsManagerComponent().inject(this);
        newsPresenter.setView(this);

        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        newsPostList = new ArrayList<>();

        rvTestString.setLayoutManager(new LinearLayoutManager(this));

        newsAdapter = new NewsAdapter(this);
        newsAdapter.setNewsPostList(newsPostList);
        rvTestString.setAdapter(newsAdapter);

        ItemClickSupport.addTo(rvTestString).setOnItemClickListener((recyclerView, position, v) -> {
            ActivityOptions transitionActivityOptions;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(TransitionImageFirstActivity.this, v.findViewById(R.id.image_view),
                        "transitionName");

                Intent intent = new Intent(TransitionImageFirstActivity.this, TransitionImageSecondActivity.class);
                intent.putExtra("news_post", newsPostList.get(position));
                startActivity(intent, transitionActivityOptions.toBundle());
            }
        });

        newsPresenter.loadNews();
    }

    @Override
    public void showNews(List<Article> news) {
        int lastBeforeInsert = newsPostList.size();

        newsPostList.addAll(news);

        newsAdapter.notifyItemRangeInserted(lastBeforeInsert, news.size());
    }
}
