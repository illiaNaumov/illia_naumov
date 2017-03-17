package com.example.naumov.illia.illianaumov.transitionimage.view;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.naumov.illia.illianaumov.R;
import com.example.naumov.illia.illianaumov.transitionimage.view.adapter.TestStringAdapter;
import com.example.naumov.illia.illianaumov.transitionimage.domain.NewsPost;
import com.example.naumov.illia.illianaumov.transitionimage.model.NewsLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TransitionImageFirstActivity extends AppCompatActivity {

    @BindView(R.id.recycler_view)
    RecyclerView rvTestString;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private List<NewsPost> newsPostList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition_image_first);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        newsPostList = NewsLoader.generateNews();

        rvTestString.setLayoutManager(new LinearLayoutManager(this));

        TestStringAdapter testStringAdapter = new TestStringAdapter(this);
        testStringAdapter.setNewsPostList(newsPostList);
        rvTestString.setAdapter(testStringAdapter);

        ItemClickSupport.addTo(rvTestString).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                ActivityOptions transitionActivityOptions;
                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(TransitionImageFirstActivity.this, v.findViewById(R.id.image_view),
                            "transitionName");

                    Intent intent = new Intent(TransitionImageFirstActivity.this, TransitionImageSecondActivity.class);
                    intent.putExtra("news_post", newsPostList.get(position));
                    startActivity(intent, transitionActivityOptions.toBundle());
                }
            }
        });
    }
}
