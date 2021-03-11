package se.hkr.xtremexerciseapp.database;

import androidx.room.TypeConverter;

import java.util.Arrays;
import java.util.List;

public class ExerciseConverter {
        @TypeConverter
        public Exercises storedStringToExerciseList(String value) {
            List<String> list = Arrays.asList(value.split("\\s*,\\s*"));
            return new Exercises(list);
        }

        @TypeConverter
        public String exerciseListToStoredString(List<String> exercisesName) {
            String value = "";

            for (String name :exercisesName)
                value += name + ",";

            return value;
        }
}
