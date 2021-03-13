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

    @Query("SELECT * FROM routine")
    List<Routine> getAllRoutines();

    @Insert
    void insertRoutine(Routine... routine);

    @Delete
    void deleteRoutine(Routine routine);

    @Query("UPDATE routine SET Name = :name WHERE id = :id")
    void updateExerciseName(String name, int id);

    @Query("UPDATE routine SET Description = :description WHERE id = :id")
    void updateExerciseDescription(String description, int id);

    @Query("UPDATE routine SET Exercise_List = :exercises WHERE id = :id")
    void updateExerciseListById(String exercises, int id);

    // Unsure if this is correct
    @Query("SELECT * FROM exercise WHERE category = :category")
    List<Exercise> getCategoryExercises(int category);

    @Query("SELECT * FROM Routine WHERE id = :id")
    Routine getRoutineById(int id);

    @Query("SELECT * FROM exercise WHERE exerciseId = :id")
    Exercise getExerciseById(int id);
}
