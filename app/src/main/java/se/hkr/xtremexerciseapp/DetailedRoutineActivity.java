package se.hkr.xtremexerciseapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import se.hkr.xtremexerciseapp.adapters.RecyclerViewAdapter;
import se.hkr.xtremexerciseapp.database.Exercise;
import se.hkr.xtremexerciseapp.database.ExerciseDatabase;
import se.hkr.xtremexerciseapp.database.Exercises;
import se.hkr.xtremexerciseapp.database.Routine;

public class DetailedRoutineActivity extends AppCompatActivity {

    RecyclerView exerciseRecyclerView;
    TextView routineTitle, routineDescription;
    ImageView routineImage;

    List<Exercise> exerciseList = new ArrayList<>();
    ExerciseDatabase database;
    Exercises exercisesInRoutine;
    Routine routine;

    int routineId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_routine);

        exerciseRecyclerView = findViewById(R.id.exercise_recyclerView);
        routineDescription = findViewById(R.id.routine_description);
        routineTitle = findViewById(R.id.routine_title);
        routineImage = findViewById(R.id.routine_image);

        getData();

        setData();
    }

    private void getData(){
        if (getIntent().hasExtra("routineID")){
            routineId = getIntent().getIntExtra("routineID", 1);
        } else {
            Toast.makeText(this, "No data Retrieved!", Toast.LENGTH_SHORT).show();
        }
    }

    private void setData(){
        database = ExerciseDatabase.getDatabaseInstance(this);

        exerciseList.addAll(database.exerciseDAO().getAllExercises());

        routine = database.exerciseDAO().getRoutineById(routineId);

        routineTitle.setText(routine.getName());

        routineDescription.setText(routine.getDescription());

        //Get the string separated into a List<String>
        exercisesInRoutine = storedStringToExerciseList(routine.getExerciseList());

        exerciseList = stringListToExerciseList(exercisesInRoutine, exerciseList);

        exerciseRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, exerciseList);

        exerciseRecyclerView.setAdapter(adapter);
    }

    private Exercises storedStringToExerciseList(String value) {
        List<String> list = Arrays.asList(value.split("\\s*,\\s*"));
        return new Exercises(list);
    }

    private List<Exercise> stringListToExerciseList(Exercises exercises, List<Exercise> fullExerciseList){
        List<Exercise> routineExerciseList = new ArrayList<>();

        for (Exercise exercise : fullExerciseList){
            String name = exercise.getName().toString().trim();
            if (exercises.getExerciseList().contains(name)){
                routineExerciseList.add(exercise);
            }
        }

        return routineExerciseList;
    }

}