package com.example.use_by;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Delete;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface FoodDao {

    @Query("SELECT * FROM Food")
    List<Food> getAllFoods();

    @Query("SELECT * FROM Food WHERE name=:name")
    Food getFoodByName(String name);

    @Query("SELECT * FROM Food WHERE location LIKE :location")
    List<Food>  findByLocation(String location);

    @Insert
    void insert(Food food);

    @Query("SELECT * FROM Food WHERE id=:id")
    Food findById(long id);


    @Delete
    void delete(Food food);

    @Update
    void update(Food... food);


}