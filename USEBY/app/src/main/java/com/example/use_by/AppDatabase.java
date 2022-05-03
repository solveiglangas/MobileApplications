package com.example.use_by;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Food.class, Notification.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {

    private static final String PRELOADED_DATABASE_FILE = "databases/foodDB.db";
    private static final String DB_NAME = "foodDatabase.db";
    private static volatile AppDatabase instance;

    public abstract FoodDao foodDao();

    public abstract NotificationDao notificationDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = create(context);
        }
        return instance;
    }
    public static AppDatabase create(Context context){
        return Room.databaseBuilder(context, AppDatabase.class, DB_NAME)
//                .fallbackToDestructiveMigration()
                .createFromAsset(PRELOADED_DATABASE_FILE)
                .allowMainThreadQueries()
                .build();
    }
}
