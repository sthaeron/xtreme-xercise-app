package se.hkr.xtremexerciseapp.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import se.hkr.xtremexerciseapp.ExerciseListener;
import se.hkr.xtremexerciseapp.R;
import se.hkr.xtremexerciseapp.database.Exercise;
import se.hkr.xtremexerciseapp.database.ExerciseDatabase;
import se.hkr.xtremexerciseapp.database.Exercises;

public class AddingRoutineViewAdapter extends RecyclerView.Adapter<AddingRoutineViewAdapter.MyViewHolder> {

    private List<Exercise> exercises;
    private Activity context;
    private ExerciseListener exerciseListener;
    private List<Integer> selectedExercisesID = new ArrayList<>();

    public AddingRoutineViewAdapter(Activity context, List<Exercise> exercises, ExerciseListener exerciseListener, String routineExerciseList, int opt){
        this.context = context;
        this.exercises = exercises;
        this.exerciseListener = exerciseListener;
        if (opt == 1){
            //Update routine selection
            //Register exercisesId into selectedExercisesID
            addSelectedExercises(routineExerciseList, exercises);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_optional, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Exercise exercise = exercises.get(position);

        holder.nameView.setText(exercise.getName());
        holder.descriptionView.setText(exercise.getDescription());
        holder.imageView.setImageResource(exercise.imageId);

        if (selectedExercisesID.contains(exercise.exerciseId)){
            holder.cardView.setBackgroundResource(R.drawable.selected_exercise);
            holder.imageSelected.setVisibility(View.VISIBLE);
        } else{
            holder.cardView.setBackgroundResource(R.drawable.optional_view_bg);
            holder.imageSelected.setVisibility(View.GONE);
        }
        holder.layout.setOnClickListener(v -> {
            if (selectedExercisesID.contains(exercise.exerciseId)){
                holder.cardView.setBackgroundResource(R.drawable.optional_view_bg);
                holder.imageSelected.setVisibility(View.GONE);
                selectedExercisesID.remove((Object) exercise.exerciseId);
                if (getSelectedExercises().size() == 0){
                    exerciseListener.onExerciseSelectedAction(false);
                }
            } else{
                holder.cardView.setBackgroundResource(R.drawable.selected_exercise);
                holder.imageSelected.setVisibility(View.VISIBLE);
                selectedExercisesID.add(exercise.exerciseId);
                exerciseListener.onExerciseSelectedAction(true);
            }
        });
    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }

    public List<Exercise> getSelectedExercises(){
        List<Exercise> selectedExercises = new ArrayList<>();
        for (Exercise exercise : exercises){
            if (selectedExercisesID.contains(exercise.exerciseId)){
                selectedExercises.add(exercise);
            }
        }
        return selectedExercises;
    }

    private void addSelectedExercises(String value, List<Exercise> fullExerciseList) {
        List<String> list = Arrays.asList(value.split("\\s*,\\s*"));

        for (Exercise exercise : fullExerciseList){
            String name = exercise.getName().toString().trim();
            if (list.contains(name)){
                selectedExercisesID.add(exercise.getExerciseId());
            }
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView nameView, descriptionView;
        ImageView imageView, imageSelected;
        ConstraintLayout layout;
        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nameView = itemView.findViewById(R.id.name_view);
            descriptionView = itemView.findViewById(R.id.description_view);
            imageView = itemView.findViewById(R.id.imageView);
            imageSelected = itemView.findViewById(R.id.image_selected);
            layout = itemView.findViewById(R.id.exercise_layout);
            cardView = itemView.findViewById(R.id.optional_card_view);
        }
    }
}
