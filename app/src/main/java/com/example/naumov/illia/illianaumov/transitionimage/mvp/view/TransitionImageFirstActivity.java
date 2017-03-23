package com.example.naumov.illia.illianaumov.transitionimage.mvp.view;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.naumov.illia.illianaumov.MyApp;
import com.example.naumov.illia.illianaumov.R;
import com.example.naumov.illia.illianaumov.transitionimage.mvp.presenter.TransitionImageFirstActivityPresenterImpl;
import com.example.naumov.illia.illianaumov.transitionimage.mvp.view.adapter.TestStringAdapter;
import com.example.naumov.illia.illianaumov.transitionimage.mvp.model.entities.NewsPost;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TransitionImageFirstActivity extends AppCompatActivity implements IFirstActivityView{

    @BindView(R.id.rvNews)
    RecyclerView rvNews;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Inject
    public TransitionImageFirstActivityPresenterImpl firstActivityPresenter;

    private List<NewsPost> newsPostList;
    private TestStringAdapter testStringAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition_image_first);

        ButterKnife.bind(this);

        MyApp.getNewsManagerComponent().inject(this);

        firstActivityPresenter.setView(this);

        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        newsPostList = new ArrayList<>();
        rvNews.setLayoutManager(new LinearLayoutManager(this));

        testStringAdapter = new TestStringAdapter(this);
        testStringAdapter.setNewsPostList(newsPostList);
        rvNews.setAdapter(testStringAdapter);

        ItemClickSupport.addTo(rvNews).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                ActivityOptions transitionActivityOptions;
                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(TransitionImageFirstActivity.this, v.findViewById(R.id.ivTest),
                            "transitionName");

                    Intent intent = new Intent(TransitionImageFirstActivity.this, TransitionImageSecondActivity.class);
                    intent.putExtra("news_post", newsPostList.get(position));
                    startActivity(intent, transitionActivityOptions.toBundle());
                }
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

        firstActivityPresenter.loadNews();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        firstActivityPresenter.destroy();
    }



    @Override
    public void showNewsPost(NewsPost newsPost) {
        newsPostList.add(newsPost);
        testStringAdapter.notifyDataSetChanged();
    }
}
