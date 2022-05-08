package com.example.use_by;

import android.app.IntentService;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import java.util.List;

public class MyService extends IntentService {

    private static final String CHANNEL_ID = "github.use-by";
    private static final String CHANNEL_NAME = "My notification channel";

    private NotificationManager notificationManager;
    private int notificationId;

    public MyService() {
        super("My Service");
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        AppDatabase db;

        db = AppDatabase.getInstance(getApplicationContext());

        List<Food> foods = db.foodDao().getAllFoods();
        System.out.println(foods.size());

        Context context = getApplicationContext();

        notificationManager = (NotificationManager) context
                .getSystemService(context.NOTIFICATION_SERVICE);

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context, CHANNEL_ID);

        builder.setSmallIcon(R.drawable.ic_notifications);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
            builder.setChannelId(CHANNEL_ID);
        }
        notificationId = 0;
        if (foods.size() > 0){
            for (Food food : foods) {
                int daysToEatNow = Integer.parseInt(getResources().getString(R.string.num_days_eat_now));
                if(food.getDaysUntilExpired() <= daysToEatNow) {
                    builder.setContentTitle("Eat now");
                    builder.setContentText(food.getName()+ " in " + food.getLocation() + " is expiring soon");
                    notificationId += 1;
                    notificationManager.notify(notificationId, builder.build());

                }
            }
        }

    }
}