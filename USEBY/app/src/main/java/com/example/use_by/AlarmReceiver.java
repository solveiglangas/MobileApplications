package com.example.use_by;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import java.util.List;

public class AlarmReceiver extends BroadcastReceiver {

    private static final String CHANNEL_ID = "CHANNEL_ALARM";

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onReceive(Context context, Intent intent) {
        // Get id & message
        int notificationId = intent.getIntExtra("notificationId", 0);
        String message = intent.getStringExtra("message");

        System.out.println(notificationId);
        System.out.println(message);

        // Call Activity when notification is tapped.
        Intent mainIntent = new Intent(context, AddItemActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, mainIntent, 0);

        // NotificationManager
        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        // For API 26 and above
        CharSequence channelName = "My Notification";
        int importance = NotificationManager.IMPORTANCE_DEFAULT;

        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, channelName, importance);
        notificationManager.createNotificationChannel(channel);

        // Prepare Notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle("TITLE")
                .setContentText(message)
                .setContentIntent(contentIntent)
                .setWhen(System.currentTimeMillis())
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setAutoCancel(true);

        // Notify
        notificationManager.notify(notificationId, builder.build());
    }
}
