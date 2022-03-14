package domains;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface FoodDao {

    @Query("SELECT * FROM food")
    List<Food> getAllFoods();

    @Query("SELECT * FROM food WHERE id IN (:foodIds)")
    List<Food> loadAllByIds(long[] foodIds);

    @Query("SELECT * FROM food WHERE id=:id")
    Food findById(long id);

    @Query("SELECT * FROM food WHERE name LIKE :name LIMIT 1")
    Food findByName(String name);

}