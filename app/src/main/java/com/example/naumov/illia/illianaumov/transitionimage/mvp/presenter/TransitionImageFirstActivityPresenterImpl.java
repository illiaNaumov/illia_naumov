package com.example.naumov.illia.illianaumov.transitionimage.mvp.presenter;

import com.example.naumov.illia.illianaumov.transitionimage.mvp.model.entities.NewsPost;
import com.example.naumov.illia.illianaumov.transitionimage.mvp.model.local.NewsLoader;
import com.example.naumov.illia.illianaumov.transitionimage.mvp.view.IFirstActivityView;
import com.example.naumov.illia.illianaumov.transitionimage.utils.Utility;

import rx.Observable;
import rx.Subscription;

/**
 * Created by illia_naumov.
 */

public class TransitionImageFirstActivityPresenterImpl implements IFirstActivityPresenter {

    private IFirstActivityView firstActivityView;
    private Subscription subs;

    @Override
    public void setView(IFirstActivityView firstActivityView) {
        this.firstActivityView = firstActivityView;
    }

    @Override
    public void loadNews() {
        subs = Observable.just(NewsLoader.generateNews()).flatMap(Observable::from)
                .filter(newsPost -> Utility.isRecentDate(newsPost.getDate()))
                .take(10)
                .subscribe(
                        this::showNewsPost,
                        System.out::println

                );
    }

    private void showNewsPost(NewsPost newsPost){
        if(isViewBind()) {
            firstActivityView.showNewsPost(newsPost);
        }
    }

    @Override
    public void destroy() {
        subs.unsubscribe();
        firstActivityView = null;
    }

    private boolean isViewBind() {
        return firstActivityView != null;
    }


}
