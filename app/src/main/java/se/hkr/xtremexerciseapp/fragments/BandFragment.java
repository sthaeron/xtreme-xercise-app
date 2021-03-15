package se.hkr.xtremexerciseapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import se.hkr.xtremexerciseapp.R;
import se.hkr.xtremexerciseapp.adapters.RecyclerViewAdapter;
import se.hkr.xtremexerciseapp.adapters.SimpleRecyclerViewAdapter;
import se.hkr.xtremexerciseapp.database.Exercise;
import se.hkr.xtremexerciseapp.database.ExerciseCategory;
import se.hkr.xtremexerciseapp.database.ExerciseDatabase;

public class BandFragment extends Fragment {

    private View view;
    private RecyclerView recyclerView;
    List<Exercise> sortedExerciseList = new ArrayList<>();
    ExerciseDatabase database;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_exercises, container, false);

        // Update sortedExercises list
        database = ExerciseDatabase.getDatabaseInstance(BandFragment.this.getContext());
        if(sortedExerciseList.isEmpty()){
            sortedExerciseList.addAll(database.exerciseDAO().getCategoryExercises(ExerciseCategory.BAND));
        }
        // Set up RecyclerView
        recyclerView = view.findViewById(R.id.recyclerViewForExercises);
        SimpleRecyclerViewAdapter adapter = new SimpleRecyclerViewAdapter(getActivity(), sortedExerciseList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        return view;
    }
}
