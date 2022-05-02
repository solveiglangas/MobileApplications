package com.example.use_by;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ViewNotificationsActivity extends AppCompatActivity {

    private AppDatabase db;
    private ListView listView;
    List<String> notificationMessages;
    List<String> notificationHeaders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_notifications);

        db = AppDatabase.getInstance(getApplicationContext());
        listView = findViewById(R.id.notificationList);

        fillData();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        fillData();
    }

    private void fillData(){
        // Insert test data
//        Notification newNotification = new Notification();
//        newNotification.setHeader("Katunarja");
//        newNotification.setMessage("Katunarja po lu menc alarm");
//
//        db.notificationDao().insert(newNotification);

        List<Notification> notifications = db.notificationDao().getAllNotifications();
//        System.out.println(notifications.get(0).getMessage());
        notificationHeaders = new ArrayList<>();
        notificationMessages = new ArrayList<>();

        for (Notification n:notifications){
            notificationMessages.add(n.getMessage());
            notificationHeaders.add(n.getHeader());
        }

        ArrayAdapter<String> listAdapter = new ArrayAdapter<>(listView.getContext(),
                android.R.layout.simple_list_item_1, notificationMessages);
        listView.setAdapter(listAdapter);

    }

}


