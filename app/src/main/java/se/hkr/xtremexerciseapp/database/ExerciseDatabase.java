package se.hkr.xtremexerciseapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Exercise.class}, version = 1)
public abstract class ExerciseDatabase extends RoomDatabase {

    public abstract ExerciseDAO exerciseDAO();

    private static ExerciseDatabase INSTANCE;

    public static ExerciseDatabase getDatabaseInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), ExerciseDatabase.class, "EXERCISE_DATABASE")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
}
