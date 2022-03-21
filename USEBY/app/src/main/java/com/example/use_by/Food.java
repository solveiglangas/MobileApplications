package com.example.use_by;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Date;
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

}
