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
import se.hkr.xtremexerciseapp.database.Exercise;
import se.hkr.xtremexerciseapp.database.ExerciseConverter;
import se.hkr.xtremexerciseapp.database.ExerciseDatabase;
import se.hkr.xtremexerciseapp.database.Exercises;
import se.hkr.xtremexerciseapp.database.Routine;

public class AddingRoutineActivity extends AppCompatActivity implements ExerciseListener {

    RecyclerView exerciseRecyclerView;
    List<Exercise> exerciseList = new ArrayList<>();
    ExerciseDatabase database;
    Button saveButton;
    EditText title, description;
    List<String> names = new ArrayList<>();
    Exercises exercisesToSave = new Exercises(names);
    ExerciseConverter converter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_routine);

        title = findViewById(R.id.title_edit_text);
        description = findViewById(R.id.description_edit_text);
        saveButton = findViewById(R.id.save_bt);
        saveButton.setVisibility(View.GONE);
        exerciseRecyclerView = findViewById(R.id.e_recyclerView);

        database = ExerciseDatabase.getDatabaseInstance(this);

        exerciseList.addAll(database.exerciseDAO().getAllExercises());

        AddingRoutineViewAdapter adapter = new AddingRoutineViewAdapter(this, exerciseList, this);
        exerciseRecyclerView.setAdapter(adapter);
        exerciseRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        saveButton.setOnClickListener(v -> {
            if (!title.getText().toString().equals("")) {
                List<Exercise> selectedExercises = adapter.getSelectedExercises();
                for (Exercise exercise : selectedExercises) {
                    names.add(exercise.getName());
                    System.out.println(exercise.getName());
                }
                exercisesToSave.setExerciseList(names);
                System.out.println(exercisesToSave.getExerciseList());
                String str = convertToString(exercisesToSave.getExerciseList());

                Routine routine = new Routine();
                routine.setName(title.getText().toString().trim());
                if(!description.getText().toString().equals("")) routine.setDescription(description.getText().toString().trim());
                else routine.setDescription("");
                routine.setExerciseList(str);
                database.exerciseDAO().insertRoutine(routine);

                openMainMenu();

            } else{
                Toast.makeText(this, "Please input a title!", Toast.LENGTH_SHORT).show();
            }
        });
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
            saveButton.setVisibility(View.VISIBLE);
        } else {
            saveButton.setVisibility(View.GONE);
        }
    }

    private void openMainMenu(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}