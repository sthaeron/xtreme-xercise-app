package se.hkr.xtremexerciseapp.database;

import androidx.room.TypeConverter;

import java.util.Arrays;
import java.util.List;

public class ExerciseConverter {
        @TypeConverter
        public Exercises storedStringToExerciseList(String value) {
            List<String> strings = Arrays.asList(value.split("\\s*,\\s*"));
            return new Exercises(strings);
        }

        @TypeConverter
        public String exerciseListToStoredString(Exercises exercises) {
            String value = "";

            for (String lang :exercises.getExerciseList())
                value += lang + ",";

            return value;
        }
}
