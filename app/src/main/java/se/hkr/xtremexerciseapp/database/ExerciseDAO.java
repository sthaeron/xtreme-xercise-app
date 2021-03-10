package se.hkr.xtremexerciseapp.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ExerciseDAO {

    @Query("SELECT * FROM exercise")
    List<Exercise> getAllExercises();

    @Insert
    void insertExercise(Exercise... exercises);

    @Delete
    void deleteExercise(Exercise exercise);

}
