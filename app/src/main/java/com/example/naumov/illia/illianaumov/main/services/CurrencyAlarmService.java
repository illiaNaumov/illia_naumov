package com.example.naumov.illia.illianaumov.main.services;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import com.example.naumov.illia.illianaumov.R;
import com.example.naumov.illia.illianaumov.main.MyApp;
import com.example.naumov.illia.illianaumov.main.mvp.model.entities.UiCurrency;
import com.example.naumov.illia.illianaumov.main.mvp.view.activity.CurrencyActivity;
import com.example.naumov.illia.illianaumov.main.retrofit.CurrencyApi;
import com.example.naumov.illia.illianaumov.main.utils.MappingUtility;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by illia_naumov.
 */

public class CurrencyAlarmService extends Service {

    @Inject
    public CurrencyApi currencyApi;

    Subscription subscription;

    @Override
    public void onCreate() {
        Timber.e("onCreate");

        MyApp.plusCurrencyComponent().inject(this);
        super.onCreate();
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Timber.e("onStartCommand");

        subscription = currencyApi.getCurrentDayCurrencies()
                .flatMap(Observable::from)
                .filter(curr -> curr.getCcy().equals("USD"))
                .map(MappingUtility::mapCurrentDayCurrency)
                .take(1)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(
                        this::checkCurrencyRate,
                        Timber::e
                );


        return super.onStartCommand(intent, flags, startId);
    }

    private void checkCurrencyRate(UiCurrency uiCurrency) {
        if (uiCurrency.getCurrencyBuyRate() > 10) {
            sendNotification();
        }
    }

    private void sendNotification() {
        Timber.e("sendNotification");

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("My notification")
                        .setContentText("Hello World!");
// Creates an explicit intent for an Activity in your app
        Intent resultIntent = new Intent(this, CurrencyActivity.class);

// The stack builder object will contain an artificial back stack for the
// started Activity.
// This ensures that navigating backward from the Activity leads out of
// your application to the Home screen.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
// Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(CurrencyActivity.class);
// Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
// mId allows you to update the notification later on.
        mNotificationManager.notify(1, mBuilder.build());
    }

    @Override
    public void onDestroy() {
        subscription.unsubscribe();
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
