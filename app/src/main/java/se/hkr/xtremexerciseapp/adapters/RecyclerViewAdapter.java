package se.hkr.xtremexerciseapp.adapters;

import android.app.Activity;
import android.app.AppComponentFactory;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import se.hkr.xtremexerciseapp.AnExerciseActivity;
import se.hkr.xtremexerciseapp.DetailedRoutineActivity;
import se.hkr.xtremexerciseapp.MainActivity;
import se.hkr.xtremexerciseapp.R;
import se.hkr.xtremexerciseapp.database.Exercise;
import se.hkr.xtremexerciseapp.database.ExerciseDatabase;
import se.hkr.xtremexerciseapp.fragments.AnExerciseFragment;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private List<Exercise> exercises;
    private Activity context;
    private ExerciseDatabase database;

    public RecyclerViewAdapter(Activity context, List<Exercise> exercises){
        this.context = context;
        this.exercises = exercises;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_row_main, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Exercise exercise = exercises.get(position);

        holder.nameView.setText(exercise.getName());
        holder.descriptionView.setText(exercise.getDescription());
        holder.imageView.setImageResource(exercise.imageId);

        holder.constraintLayout.setOnClickListener(v -> {

            Intent intent = new Intent(context, AnExerciseActivity.class);
            intent.putExtra("exerciseId", exercise.exerciseId);
            context.startActivity(intent);

        });
    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView nameView, descriptionView;
        ImageView imageView;
        ConstraintLayout constraintLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nameView = itemView.findViewById(R.id.name_view);
            descriptionView = itemView.findViewById(R.id.description_view);
            imageView = itemView.findViewById(R.id.imageView);
            constraintLayout = itemView.findViewById(R.id.constraint_layout);
        }
    }


}
