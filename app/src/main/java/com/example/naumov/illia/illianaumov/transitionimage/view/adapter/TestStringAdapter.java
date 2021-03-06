package com.example.naumov.illia.illianaumov.transitionimage.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.naumov.illia.illianaumov.R;
import com.example.naumov.illia.illianaumov.transitionimage.domain.NewsPost;
import com.squareup.picasso.Picasso;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by illia_naumov.
 */

public class TestStringAdapter extends RecyclerView.Adapter <RecyclerView.ViewHolder> {

    private static final int HEADER_TYPE = 0;
    private static final int LIST_ITEM_TYPE = 1;

    private Context mContext;
    private List<NewsPost> newsPostList;
    private LayoutInflater layoutInflater;

    private int lastPosition;

    public TestStringAdapter(Context context) {
        this.mContext = context;

        layoutInflater = LayoutInflater.from(mContext);
    }

    public void setNewsPostList(List<NewsPost> newsPostList) {
        this.newsPostList = newsPostList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;

        switch(viewType){
            case HEADER_TYPE:
                v = layoutInflater.inflate(R.layout.recycler_view_header, parent, false);
                return new TestStringViewHeaderHolder(v);
            case LIST_ITEM_TYPE:
                v = layoutInflater.inflate(R.layout.recycler_view_item, parent, false);
                return new TestStringViewHolder(v);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof TestStringViewHeaderHolder){

            ((TestStringViewHeaderHolder) holder).onBind(newsPostList.get(position));

        }else if(holder instanceof TestStringViewHolder){

            ((TestStringViewHolder) holder).onBind(newsPostList.get(position));

        }

        addAnimation(holder, position);
    }



    @Override
    public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.itemView.clearAnimation();
    }

    @Override
    public int getItemCount() {
        return newsPostList.size();
    }

    @Override
    public int getItemViewType(int position) {
        switch(position){
            case 0:
                return HEADER_TYPE;
            default:
                return LIST_ITEM_TYPE;
        }
    }

    private void addAnimation(RecyclerView.ViewHolder viewHolder, int position){
        Animation animation = AnimationUtils.loadAnimation(mContext,
                (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);

        viewHolder.itemView.startAnimation(animation);

        lastPosition = position;
    }

    static class TestStringViewHeaderHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.image_view)
        ImageView ivTest;

        TestStringViewHeaderHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void onBind(final NewsPost newsPost){
            Picasso.with(ivTest.getContext())
                    .load(newsPost.getImageUrl()).into(ivTest);
        }
    }

    static class TestStringViewHolder extends RecyclerView.ViewHolder{
        View itemView;
        @BindView(R.id.image_view)
        ImageView ivTest;
        @BindView(R.id.date)
        TextView tvDate;

        TestStringViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            ButterKnife.bind(this, itemView);
        }

        void onBind(final NewsPost newsPost){
            Picasso.with(ivTest.getContext())
                    .load(newsPost.getImageUrl()).into(ivTest);

            tvDate.setText(new SimpleDateFormat("dd.MM.yyyy", Locale.US).format(Calendar.getInstance().getTime()));

        }
    }

}
