package com.example.use_by;

import android.app.IntentService;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

public class MyService extends IntentService {

    private static final String CHANNEL_ID = "io.github.bonigarcia.android.notification.notify_001";
    private static final String CHANNEL_NAME = "My notification channel";

    private NotificationManager notificationManager;
    private int notificationId;

    public MyService() {
        super("My Service");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        System.out.println("0000000000");

        AppDatabase db;

        db = AppDatabase.getInstance(getApplicationContext());

        db.foodDao().getAllFoods();

        Context context = getApplicationContext();

        notificationManager = (NotificationManager) context
                .getSystemService(context.NOTIFICATION_SERVICE);

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context, CHANNEL_ID);
        builder.setContentTitle("My notification title");
        builder.setContentText("My notification content");
        builder.setSmallIcon(R.drawable.ic_notifications);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
            builder.setChannelId(CHANNEL_ID);
        }

        notificationId = 0;
        notificationManager.notify(notificationId, builder.build());

        Log.i("My service", "Service running");
        System.out.println("11111111");
    }
}