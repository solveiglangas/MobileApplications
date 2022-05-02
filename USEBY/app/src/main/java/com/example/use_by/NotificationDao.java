package com.example.use_by;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Delete;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NotificationDao {

    @Query("SELECT * FROM notification_table")
    List<Notification> getAllNotifications();


    @Insert
    void insert(Notification notification);

    @Query("SELECT * FROM notification_table WHERE id=:id")
    Notification findById(long id);


    @Delete
    void delete(Notification notification);



}