package com.example.use_by;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FoodDao {

    @Query("SELECT * FROM Food")
    List<Food> getAllFoods();

    @Query("SELECT * FROM Food WHERE location LIKE :location")
    List<Food>  findByLocation(String location);

    /*
    @Query("SELECT * FROM Food WHERE id=:id")
    Food findById(long id);

    @Query("SELECT * FROM Food WHERE name LIKE :name LIMIT 1")
    Food findByName(String name);

    @Insert
    long insert(Food food);

    @Insert
    long[] insertAll(Food... food);

    @Update
    public void update(Food... food);

    @Delete
    void delete(Food food);*/

}