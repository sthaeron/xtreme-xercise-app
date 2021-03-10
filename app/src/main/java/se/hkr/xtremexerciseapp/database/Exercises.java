package se.hkr.xtremexerciseapp.database;

import java.util.List;

public class Exercises {
    private List<String> exerciseList;

    public Exercises(List<String> exerciseList) {
        this.exerciseList = exerciseList;
    }

    public List<String> getExerciseList() {
        return exerciseList;
    }

    public void setExerciseList(List<String> exerciseList) {
        this.exerciseList = exerciseList;
    }
}
