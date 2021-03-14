package se.hkr.xtremexerciseapp.adapters;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.util.List;

import se.hkr.xtremexerciseapp.R;
import se.hkr.xtremexerciseapp.database.Exercise;
import se.hkr.xtremexerciseapp.database.ExerciseDatabase;
import se.hkr.xtremexerciseapp.fragments.AnExerciseFragment;

public class SimpleRecyclerViewAdapter extends RecyclerView.Adapter<SimpleRecyclerViewAdapter.MyViewHolder> {

    private List<Exercise> exercises;
    private Activity context;
    private ExerciseDatabase database;

    public SimpleRecyclerViewAdapter(Activity context, List<Exercise> exercises){
        this.context = context;
        this.exercises = exercises;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.grid_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Exercise exercise = exercises.get(position);

        holder.nameView.setText(exercise.getName());
        holder.imageView.setImageResource(exercise.imageId);

        holder.cardView.setOnClickListener(v -> {

            //Open AnExerciseFragment
            AnExerciseFragment fragment = new AnExerciseFragment();
            Bundle b = new Bundle();
            b.putInt("exerciseID", exercise.exerciseId);
            fragment.setArguments(b);
            AppCompatActivity activity = (AppCompatActivity) v.getContext();
            activity.getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(
                            R.anim.slide_in,  // enter
                            R.anim.fade_out,  // exit
                            R.anim.fade_in,   // popEnter
                            R.anim.slide_out  // popExit
                    )
                    .replace(R.id.fragment_container, fragment)
                    .addToBackStack(null)
                    .commit();
        });
    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView nameView;
        ImageView imageView;

        MaterialCardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nameView = itemView.findViewById(R.id.cardTitle);
            imageView = itemView.findViewById(R.id.cardImage);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }


}
