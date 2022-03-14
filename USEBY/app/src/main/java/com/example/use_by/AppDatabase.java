package com.example.use_by;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import domains.Food;
import domains.FoodDao;

@Database(entities = {Food.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract FoodDao foodDao();
}
