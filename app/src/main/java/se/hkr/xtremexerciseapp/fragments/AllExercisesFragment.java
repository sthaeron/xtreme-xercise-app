package se.hkr.xtremexerciseapp.fragments;

import android.os.Bundle;
import android.util.Log;
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
import java.util.logging.Logger;

import se.hkr.xtremexerciseapp.R;
import se.hkr.xtremexerciseapp.adapters.RecyclerViewAdapter;
import se.hkr.xtremexerciseapp.database.Exercise;
import se.hkr.xtremexerciseapp.database.ExerciseDatabase;

public class AllExercisesFragment extends Fragment {

    private View view;
    private RecyclerView recyclerView;
    List<Exercise> sortedExerciseList = new ArrayList<>();
    ExerciseDatabase database;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_exercises, container, false);

        // Update sortedExercises list
        database = ExerciseDatabase.getDatabaseInstance(getContext());
        sortedExerciseList.addAll(database.exerciseDAO().getAllExercises());

        // Set up RecyclerView
        recyclerView = view.findViewById(R.id.recyclerViewForExercises);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(AllExercisesFragment.this.getActivity(), sortedExerciseList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(AllExercisesFragment.this.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        return view;
    }



}
