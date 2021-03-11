package se.hkr.xtremexerciseapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import se.hkr.xtremexerciseapp.adapters.AddingRoutineViewAdapter;
import se.hkr.xtremexerciseapp.adapters.RecyclerViewAdapter;
import se.hkr.xtremexerciseapp.database.Exercise;
import se.hkr.xtremexerciseapp.database.ExerciseDatabase;
import se.hkr.xtremexerciseapp.database.Exercises;
import se.hkr.xtremexerciseapp.database.Routine;

public class EditRoutineActivity extends AppCompatActivity implements ExerciseListener {

    RecyclerView editRecyclerView;
    Button updateButton;
    EditText titleEdit, descriptionEdit;

    List<Exercise> exerciseList = new ArrayList<>();
    ExerciseDatabase database;
    Routine routine;
    List<String> names = new ArrayList<>();
    Exercises exercisesToSave = new Exercises(names);

    int routineId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_routine);

        editRecyclerView = findViewById(R.id.edit_recyclerView);
        updateButton = findViewById(R.id.update_bt);
        titleEdit = findViewById(R.id.edit_title);
        descriptionEdit = findViewById(R.id.edit_description);

        getData();

        database = ExerciseDatabase.getDatabaseInstance(this);

        exerciseList.addAll(database.exerciseDAO().getAllExercises());

        routine = database.exerciseDAO().getRoutineById(routineId);

        titleEdit.setText(routine.getName());

        descriptionEdit.setText(routine.getDescription());

        AddingRoutineViewAdapter adapter = new AddingRoutineViewAdapter(this, exerciseList, this, routine.getExerciseList(), 1);
        editRecyclerView.setAdapter(adapter);
        editRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        updateButton.setOnClickListener(v -> {
            if (!titleEdit.getText().toString().equals("")) {
                List<Exercise> selectedExercises = adapter.getSelectedExercises();
                for (Exercise exercise : selectedExercises) {
                    names.add(exercise.getName());
                    System.out.println(exercise.getName());
                }
                exercisesToSave.setExerciseList(names);
                System.out.println("Exercises: " + exercisesToSave.getExerciseList());
                String str = convertToString(exercisesToSave.getExerciseList());

                Routine routine = new Routine();
                routine.setName(titleEdit.getText().toString().trim());
                if(!descriptionEdit.getText().toString().equals("")) routine.setDescription(descriptionEdit.getText().toString().trim());
                else routine.setDescription("");
                routine.setExerciseList(str);
                System.out.println("Routine: " + routine.getExerciseList());
                database.exerciseDAO().updateExerciseName(routine.getName(), routineId);
                database.exerciseDAO().updateExerciseDescription(routine.getDescription(), routineId);
                database.exerciseDAO().updateExerciseListById(routine.getExerciseList(), routineId);

                openDetailedRoutineActivity();

            } else{
                Toast.makeText(this, "Please input a title!", Toast.LENGTH_SHORT).show();
            }


        });
    }

    private void openDetailedRoutineActivity() {
        Intent intent = new Intent(this, DetailedRoutineActivity.class);
        intent.putExtra("routineID", routineId);
        startActivity(intent);
    }

    private void getData(){
        if (getIntent().hasExtra("routineID")){
            routineId = getIntent().getIntExtra("routineID", 1);
        } else {
            Toast.makeText(this, "No data Retrieved!", Toast.LENGTH_SHORT).show();
        }
    }

    private String convertToString(List<String> exercisesName){
        String value = "";

        for (String name :exercisesName)
            value += name + ",";

        return value;
    }

    @Override
    public void onExerciseSelectedAction(Boolean isSelected) {
        if (isSelected){
            updateButton.setVisibility(View.VISIBLE);
        } else {
            updateButton.setVisibility(View.GONE);
        }
    }
}