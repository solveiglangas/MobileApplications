package com.example.use_by;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Button;
import android.widget.ListView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button fridge;
    Button freezer;
    Button pantry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fridge = (Button) findViewById(R.id.button_refrigerator);
        freezer = (Button) findViewById(R.id.button_freezer);
        pantry = (Button) findViewById(R.id.button_pantry);

        fridge.setOnClickListener(this);
        freezer.setOnClickListener(this);
        pantry.setOnClickListener(this);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 19);
        calendar.set(Calendar.MINUTE, 24);
        calendar.set(Calendar.SECOND, 30);

        if (Calendar.getInstance().after(calendar)) {
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        Intent intent = new Intent(MainActivity.this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);
        }
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.button_refrigerator: {
                Intent i = new Intent(this, OpenListActivity.class);

                i.putExtra("list","refrigerator");

                i.putExtra("key",1);

                startActivity(i);
                break;
            }
            case R.id.button_pantry: {
                Intent i = new Intent(this, OpenListActivity.class);

                i.putExtra("list", "pantry");

                i.putExtra("key", 2);

                startActivity(i);
                break;
            }
            case R.id.button_freezer: {
                Intent i = new Intent(this, OpenListActivity.class);

                i.putExtra("list", "freezer");

                i.putExtra("key", 3);

                startActivity(i);
                break;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
