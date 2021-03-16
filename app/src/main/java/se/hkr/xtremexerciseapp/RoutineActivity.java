package se.hkr.xtremexerciseapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import se.hkr.xtremexerciseapp.adapters.RoutineViewAdapter;
import se.hkr.xtremexerciseapp.database.ExerciseDatabase;
import se.hkr.xtremexerciseapp.database.Routine;

public class RoutineActivity extends AppCompatActivity {

    RecyclerView routine_recyclerView;

    List<Routine> routineList = new ArrayList<>();
    ExerciseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routine);
        setTitle("Your Routines");

        routine_recyclerView = findViewById(R.id.routine_recyclerView);

        database = ExerciseDatabase.getDatabaseInstance(this);

        routineList.addAll(database.exerciseDAO().getAllRoutines());

        addFirstRoutine();

        RoutineViewAdapter adapter = new RoutineViewAdapter(this, routineList);
        routine_recyclerView.setAdapter(adapter);
        routine_recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }

    public void addFirstRoutine(){
        if (routineList.isEmpty()){
            Routine routine = new Routine();
            routine.setName("Add Your Own Routine");
            routine.setDescription("");
            routine.setExerciseList("");
            database.exerciseDAO().insertRoutine(routine);

            routineList.addAll(database.exerciseDAO().getAllRoutines());
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}