package com.example.use_by;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

@Entity
public class Food {

    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name="name")
    public String name;

    @ColumnInfo(name="date")
    public String date;

    @ColumnInfo(name="quantity")
    public int quantity;

    @ColumnInfo(name="location")
    public String location;

    public Food(){

    }

    public Food(long id, String name, int quantity, String date, String location) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.date = date;
        this.location = location;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public int getDaysUntilExpired() {
        LocalDateTime currentDate = LocalDateTime.now();

        String dateFormat = "dd/MM/yyyy";

        DateTimeFormatter dtf = new DateTimeFormatterBuilder()
                .appendPattern(dateFormat)
                .parseDefaulting(ChronoField.HOUR_OF_DAY, 23)
                .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 59)
                .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 59)
                .toFormatter();

        String expirationString = getDate();
        LocalDateTime expirationDate = LocalDateTime.parse(expirationString, dtf);

        if (expirationDate.compareTo(currentDate) < 0) {
            return expirationDate.compareTo(currentDate);
        }

        return (int) Duration.between(currentDate, expirationDate).toDays();
    }

}
