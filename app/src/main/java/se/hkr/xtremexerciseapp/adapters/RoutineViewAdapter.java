package se.hkr.xtremexerciseapp.adapters;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import se.hkr.xtremexerciseapp.AddingRoutineActivity;
import se.hkr.xtremexerciseapp.R;
import se.hkr.xtremexerciseapp.database.Routine;

public class RoutineViewAdapter extends RecyclerView.Adapter<RoutineViewAdapter.RoutineViewHolder> {

    List<Routine> routines;
    Activity context;

    public RoutineViewAdapter(Activity context, List<Routine> routines){
        this.context = context;
        this.routines = routines;
    }

    @NonNull
    @Override
    public RoutineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.routine_row, parent, false);
        return new RoutineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoutineViewHolder holder, int position) {
        holder.titleView.setText(routines.get(position).getName());
        holder.descriptionView.setText(routines.get(position).getDescription());

        if (position == 0) holder.iconView.setImageResource(R.drawable.ic_add_icon);
        else holder.iconView.setImageResource(R.mipmap.ic_launcher_round);

        holder.routineLayout.setOnClickListener(v -> {
            if (position == 0){
                //Add routine
                Intent intent = new Intent(context, AddingRoutineActivity.class);
                context.startActivity(intent);
            } else {
                //Chose another routine

            }
        });
    }

    @Override
    public int getItemCount() {
        return routines.size();
    }

    public class RoutineViewHolder extends RecyclerView.ViewHolder{

        TextView titleView, descriptionView;
        ImageView iconView;
        ConstraintLayout routineLayout;

        public RoutineViewHolder(@NonNull View itemView) {
            super(itemView);
            titleView = itemView.findViewById(R.id.r_name_view);
            descriptionView = itemView.findViewById(R.id.r_description_view);
            iconView = itemView.findViewById(R.id.r_imageView);
            routineLayout = itemView.findViewById(R.id.routines_layout);
        }
    }
}
