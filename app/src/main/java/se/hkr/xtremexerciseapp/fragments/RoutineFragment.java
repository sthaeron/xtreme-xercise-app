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
import se.hkr.xtremexerciseapp.adapters.RoutineViewAdapter;
import se.hkr.xtremexerciseapp.database.Exercise;
import se.hkr.xtremexerciseapp.database.ExerciseCategory;
import se.hkr.xtremexerciseapp.database.ExerciseDatabase;
import se.hkr.xtremexerciseapp.database.Routine;

public class RoutineFragment extends Fragment {

    private View view;
    RecyclerView routine_recyclerView;

    List<Routine> routineList = new ArrayList<>();
    ExerciseDatabase database;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_routine, container, false);

        // Needs setup
        //TextView tempText = view.findViewById(R.id.tempText);
        //tempText.setText("Routine");

        routine_recyclerView = view.findViewById(R.id.routine_recyclerView_Frag);

        database = ExerciseDatabase.getDatabaseInstance(RoutineFragment.this.getContext());

        routineList.addAll(database.exerciseDAO().getAllRoutines());

        addFirstRoutine();

        RoutineViewAdapter adapter = new RoutineViewAdapter(RoutineFragment.this.getActivity(), routineList);
        routine_recyclerView.setAdapter(adapter);
        routine_recyclerView.setLayoutManager(new LinearLayoutManager(RoutineFragment.this.getContext(), LinearLayoutManager.HORIZONTAL, false));

        return view;
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
}
