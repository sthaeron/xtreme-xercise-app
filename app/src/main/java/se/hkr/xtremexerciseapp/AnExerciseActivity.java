package se.hkr.xtremexerciseapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.facebook.share.widget.ShareButton;

import se.hkr.xtremexerciseapp.database.Exercise;
import se.hkr.xtremexerciseapp.database.ExerciseDatabase;

public class AnExerciseActivity extends AppCompatActivity {

    private int exerciseId;
    private ExerciseDatabase database;
    private Exercise exercise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_an_exercise);
        setTitle("Your Routine");

        if (getIntent().hasExtra("exerciseId")) {
            exerciseId = getIntent().getIntExtra("exerciseId", 1);
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        database = ExerciseDatabase.getDatabaseInstance(this);

        exercise = database.exerciseDAO().getExerciseById(exerciseId);

        ImageView exerciseImage = findViewById(R.id.exercise_image);
        TextView exerciseName = findViewById(R.id.exerciseName);
        TextView description = findViewById(R.id.description);
        TextView instructions = findViewById(R.id.instructions);
        ShareButton shareButton = findViewById(R.id.share_button);
        Button videoButton = findViewById(R.id.video_button);

        exerciseImage.setImageResource(exercise.getImageId());
        exerciseName.setText(exercise.getName());
        description.setText(exercise.getDescription());
        instructions.setText(exercise.getInstructions());

        videoButton.setOnClickListener(v -> {
            System.out.println("Tried to open video");

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(exercise.getVideoURL()));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setPackage("com.google.android.youtube");
            startActivity(intent);
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}