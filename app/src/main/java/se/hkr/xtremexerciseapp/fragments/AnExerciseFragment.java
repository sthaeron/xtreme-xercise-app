package se.hkr.xtremexerciseapp.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.facebook.CallbackManager;
import com.facebook.share.model.ShareContent;
import com.facebook.share.model.ShareHashtag;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.ShareMediaContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.MessageDialog;
import com.facebook.share.widget.ShareButton;
import com.facebook.share.widget.ShareDialog;

import se.hkr.xtremexerciseapp.R;
import se.hkr.xtremexerciseapp.database.Exercise;
import se.hkr.xtremexerciseapp.database.ExerciseDatabase;

public class AnExerciseFragment extends Fragment {

    private View view;
    ExerciseDatabase database;
    Exercise exercise;
    ImageView exerciseImage;
    TextView exerciseName, instructions, description;
    Button shareButton;
    ShareDialog shareDialog;
    Button videoButton;
    CallbackManager callbackManager;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int exerciseId = getArguments().getInt("exerciseID");
        view = inflater.inflate(R.layout.fragment_an_exercise, container, false);
        // Needs setup
        database = ExerciseDatabase.getDatabaseInstance(AnExerciseFragment.this.getContext());

        exercise = database.exerciseDAO().getExerciseById(exerciseId);

        callbackManager = CallbackManager.Factory.create();

        exerciseImage = view.findViewById(R.id.exercise_image);
        exerciseName = view.findViewById(R.id.exerciseName);
        description = view.findViewById(R.id.description);
        instructions = view.findViewById(R.id.instructions);
        shareButton = view.findViewById(R.id.share_button);
        videoButton = view.findViewById(R.id.video_button);

        exerciseImage.setImageResource(exercise.getImageId());
        exerciseName.setText(exercise.getName());
        description.setText(exercise.getDescription());
        instructions.setText(exercise.getInstructions());

        ShareLinkContent shareLinkContent = new ShareLinkContent.Builder().
                setContentUrl(Uri.parse(exercise.getImageURL())).setQuote("Omg i just finished this exercise!!!!! "+exercise.getName()).setShareHashtag(new ShareHashtag.Builder().setHashtag("#Xtremexercise").build()).build();
        shareButton.setOnClickListener(v ->{
            ShareDialog shareDialog = new ShareDialog(this);
            shareDialog.show(shareLinkContent, ShareDialog.Mode.AUTOMATIC);

        });
        videoButton.setOnClickListener(v -> {

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(exercise.getVideoURL()));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setPackage("com.google.android.youtube");
            startActivity(intent);
        });

        return view;
    }

    public static AnExerciseFragment newInstance(int id){
        AnExerciseFragment fragment = new AnExerciseFragment();
        Bundle b = new Bundle();
        b.putInt("exerciseID", id);
        fragment.setArguments(b);
        return fragment;
    }

}
