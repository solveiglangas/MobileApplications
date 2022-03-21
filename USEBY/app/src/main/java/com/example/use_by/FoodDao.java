package com.example.use_by;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FoodDao {

    @Query("SELECT * FROM Food")
    List<Food> getAllFoods();

    @Query("SELECT * FROM Food WHERE location LIKE :location")
    List<Food>  findByLocation(String location);

    @Insert
    void insert(Food food);

    @Query("SELECT * FROM Food WHERE id=:id")
    Food findById(long id);

    /*@Query("SELECT * FROM Food WHERE id IN (:foodIds)")
    List<Food> loadAllByIds(long[] foodIds);
=======
>>>>>>> ff4aebabaa34efc85cb65a52a3de24f9018fe621

    @Query("SELECT * FROM Food WHERE id=:id")
    Food findById(long id);

    /*@Query("SELECT * FROM Food WHERE id IN (:foodIds)")
    List<Food> loadAllByIds(long[] foodIds);

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