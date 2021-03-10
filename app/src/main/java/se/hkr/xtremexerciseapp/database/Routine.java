package se.hkr.xtremexerciseapp.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Routine {
    @PrimaryKey(autoGenerate = true)
    int id;

    @ColumnInfo(name = "Exercise_List")
    String exerciseList;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExerciseList() {
        return exerciseList;
    }

    public void setExerciseList(String exerciseList) {
        this.exerciseList = exerciseList;
    }

}
