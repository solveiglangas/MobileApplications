package com.example.use_by;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notification_table")
public class Notification {

    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name="message")
    public String message;

    @ColumnInfo(name="header")
    public String header;


    public Notification(){

    }

    public Notification(long id, String message, String header) {
        this.id = id;
        this.message = message;
        this.header = header;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

}
