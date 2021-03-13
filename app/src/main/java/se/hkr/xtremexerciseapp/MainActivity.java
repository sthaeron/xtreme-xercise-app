package se.hkr.xtremexerciseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.security.acl.Group;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import se.hkr.xtremexerciseapp.adapters.RecyclerViewAdapter;
import se.hkr.xtremexerciseapp.database.Exercise;
import se.hkr.xtremexerciseapp.database.ExerciseCategory;
import se.hkr.xtremexerciseapp.database.ExerciseDatabase;
import se.hkr.xtremexerciseapp.fragments.AllExercisesFragment;
import se.hkr.xtremexerciseapp.fragments.BandFragment;
import se.hkr.xtremexerciseapp.fragments.BodyWeightFragment;
import se.hkr.xtremexerciseapp.fragments.CardioFragment;
import se.hkr.xtremexerciseapp.fragments.KettleBellFragment;
import se.hkr.xtremexerciseapp.fragments.RoutineFragment;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private NavigationView navigationView;

    RecyclerView recyclerView;
    MenuItem selected;

    List<Exercise> exerciseList = new ArrayList<>();
    ExerciseDatabase database;
    private static final String EMAIL = "email";
    private CallbackManager callbackManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.getMenu().setGroupCheckable(0, true, true);

        if (savedInstanceState == null) {
            setTitle("All Exercises");
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AllExercisesFragment()).commit();
            navigationView.getMenu().getItem(0).setChecked(true);
            selected = navigationView.getMenu().getItem(0);
        }


    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        selected.setChecked(false);

        switch (item.getItemId()) {
            case R.id.nav_all_exercises:
                setTitle("All Exercises");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AllExercisesFragment()).commit();
                break;
            case R.id.nav_kettle_bell:
                setTitle("Kettle Bell Exercises");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new KettleBellFragment()).commit();
                break;
            case R.id.nav_bodyweight:
                setTitle("Bodyweight Exercises");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new BodyWeightFragment()).commit();
                break;
            case R.id.nav_band:
                setTitle("Band Exercises");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new BandFragment()).commit();
                break;
            case R.id.nav_cardio:
                setTitle("Cardio Exercises");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CardioFragment()).commit();
                break;
            case R.id.nav_routine:
                setTitle("Routine");
                Intent intent = new Intent(this, RoutineActivity.class);
                startActivity(intent);
                break;
        }
        item.setChecked(true);
        selected = item;
        drawer.closeDrawer(GravityCompat.START);
        return false;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.drawer_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return false;
    }

    private void addExercisesToDatabase(){
        if (exerciseList.isEmpty()) {
            Exercise twoHandedSwing = new Exercise();
            twoHandedSwing.setName("Two Handed Swing");
            twoHandedSwing.setCategory(ExerciseCategory.KETTLEBELL);
            twoHandedSwing.setImageId(R.drawable.exercise_image_1);
            twoHandedSwing.setDescription("A fundemental kettle bell exercise that affects the lower back, shoulders, gluteusmaximus, hips and hamstrings. This exercise is suited to the beginner level and above.");
            twoHandedSwing.setInstructions("Start by picking up the kettlebell with both of your hands, stand so that the kettlebell can travel safely between your legs.\n You then slightly bend your knees and hinge at the hips, it is very important to keep a straight back.\n You then want to push through with your hips and straighten your legs\n. Bring the kettlebell as far up as you can without using your arms to move it, then swing it back and fourth between your legs.");
            twoHandedSwing.setVideoURL("https://www.youtube.com/watch?v=EIyOdqTf3r8&list=PLk4oYPJ7TXKg4lvvabXEn9BI4c9i696kx&index=3");
            database.exerciseDAO().insertExercise(twoHandedSwing);

            Exercise lunges = new Exercise();
            lunges.setName("Lunges");
            lunges.setCategory(ExerciseCategory.BODYWEIGHT);
            lunges.setImageId(R.drawable.exercise_image_2);
            lunges.setDescription("A simple leg exercise that works the quadriceps and the glutes.");
            lunges.setInstructions("Start by standing up tall, Step forward with one foot until your leg reaches a 90-degree angle.\nYour rear knee should remain parallel to the ground, lift with your front lunging leg.");
            lunges.setVideoURL("https://www.youtube.com/watch?v=QOVaHwm-Q6U");
            database.exerciseDAO().insertExercise(lunges);

            Exercise handToHandSwing = new Exercise();
            handToHandSwing.setName("Hand To Hand Swing");
            handToHandSwing.setCategory(ExerciseCategory.KETTLEBELL);
            handToHandSwing.setImageId(R.drawable.exercise_image_3);
            handToHandSwing.setDescription("An advanced kettle bell exercise that involves moving the kettle bell from one hand to another after preforming a swing.");
            handToHandSwing.setInstructions("Start with the kettlebell in one hand.\nBegin the movement by driving your hips back and swinging it between your legs, keeping your chest up.\nThrust your hips forward and using your arm like a pendulum, swing the kettlebell forward.\nWhen the kettlebell is at shoulder height, reach out and switch hands.\nTry to control the kettlebell back down.");
            handToHandSwing.setVideoURL("https://www.youtube.com/watch?v=QOVaHwm-Q6U");
            database.exerciseDAO().insertExercise(handToHandSwing);

            Exercise kettlebellClean = new Exercise();
            kettlebellClean.setName("Kettlebell Clean");
            kettlebellClean.setCategory(ExerciseCategory.KETTLEBELL);
            kettlebellClean.setImageId(R.drawable.exercise_image_4);
            kettlebellClean.setDescription("The kettle bell clean is great full body exercise that activates most muscles in the body.");
            kettlebellClean.setInstructions("Take the kettlebell from the floor and into the a racked position, on the chest, in one fluid motion.\nThis should be done smoothly and not result in banging the wrist, forearm or chest.");
            kettlebellClean.setVideoURL("https://www.youtube.com/watch?v=C0B1SrcGAIA");
            database.exerciseDAO().insertExercise(kettlebellClean);

            Exercise pushUp = new Exercise();
            pushUp.setName("Push-Up");
            pushUp.setCategory(ExerciseCategory.BODYWEIGHT);
            pushUp.setImageId(R.drawable.exercise_image_5);
            pushUp.setDescription("A simple and good upper body exercise that works the pecs, front delts and the triceps");
            pushUp.setInstructions("Get down on all fours, place your hand shoulder width or slightly broader, Straighten the arms and legs, brace your core, lower yourself untill your chest touches the ground and then push with all your might!\nRemember to keep a straight back  and if your form starts to fail you can do it on your knees.");
            pushUp.setVideoURL("https://www.youtube.com/watch?v=IODxDxX7oi4");
            database.exerciseDAO().insertExercise(pushUp);

            Exercise pushUpIntoCleanPress = new Exercise();
            pushUpIntoCleanPress.setName("Push-Up Into Clean And Press");
            pushUpIntoCleanPress.setCategory(ExerciseCategory.KETTLEBELL);
            pushUpIntoCleanPress.setImageId(R.drawable.exercise_image_6);
            pushUpIntoCleanPress.setDescription("A push up, followed by you jumping up and performing a clean and press.");
            pushUpIntoCleanPress.setInstructions("A normal push-up followed by a kettlebell clean and press.\nHave your feet just over shoulder-width apart.\nBend down into a half-squat.\nKeeping your back neutral.\nHold one arm out for balance.\nGrasp the top of the kettlebell’s handle.\nDrive the kettlebell towards you, keeping your arm tucked in close to your body and your weight on your heels.\nBefore the kettlebell can fulfil the rest of the motion, rotate your arm swiftly through the window of the bell.\nDrive the kettlebell upwards.\nLock-out your arm overhead.");
            pushUpIntoCleanPress.setVideoURL("https://youtu.be/c1SYWqBt-qo");
            database.exerciseDAO().insertExercise(pushUpIntoCleanPress);

            Exercise typeWriterPushUp = new Exercise();
            typeWriterPushUp.setName("TypeWriter Push-Up");
            typeWriterPushUp.setCategory(ExerciseCategory.BODYWEIGHT);
            typeWriterPushUp.setImageId(R.drawable.exercise_image_7);
            typeWriterPushUp.setDescription("A more challening variation of the push up.");
            typeWriterPushUp.setInstructions("Start the same way you would a normal push up but instead of pressing once your chest touches the ground you lean onto one side and put all of your weight onto that side, you then keep your chest close too the ground and go from one side to the other, once you have done both go back to the starting position by pressing against the ground");
            typeWriterPushUp.setVideoURL("https://www.youtube.com/watch?v=LMlxLCVKFUs");
            database.exerciseDAO().insertExercise(typeWriterPushUp);

            Exercise resistanceBandSquats = new Exercise();
            resistanceBandSquats.setName("Resistance Band Squats");
            resistanceBandSquats.setCategory(ExerciseCategory.BAND);
            resistanceBandSquats.setImageId(R.drawable.exercise_image_8);
            resistanceBandSquats.setDescription("Resistance band squat is a lower body exercise that uses the band for extra resistance to strengthen your glutes and legs.");
            resistanceBandSquats.setInstructions("Stand on band with feet shoulder width apart and hold the handles next to your shoulders so the band is behind the back of your arms.\nSlowly sit down and back into squat position keeping abdominals thight and chest lifted.\nPress back up through heels, squeezing glutes.");
            resistanceBandSquats.setVideoURL("https://www.youtube.com/watch?v=V8AgrOyr4pI");
            database.exerciseDAO().insertExercise(resistanceBandSquats);

            Exercise resistanceBandTricepExtension = new Exercise();
            resistanceBandTricepExtension.setName("Resistance Band Tricep Extension");
            resistanceBandTricepExtension.setCategory(ExerciseCategory.BAND);
            resistanceBandTricepExtension.setImageId(R.drawable.exercise_image_9);
            resistanceBandTricepExtension.setDescription("Resistance Band Tricep Extesions are an upper body exercise that focus mainly on the triceps.");
            resistanceBandTricepExtension.setInstructions("Stand with one foot slightly in front of the other and place the center of the band under the back foot.\nBring handles together straight up above the top of your head.\nSlowly lower handles behind the back of your head until elbows are bent until 90 degrees, keeping elbows close to the side of your head.\nPress hands back up overhead slowly.");
            resistanceBandTricepExtension.setVideoURL("https://www.youtube.com/watch?v=OWV6s4FtwME");
            database.exerciseDAO().insertExercise(resistanceBandTricepExtension);

            Exercise planchePushUp = new Exercise();
            planchePushUp.setName("Planche Push-Up");
            planchePushUp.setCategory(ExerciseCategory.BODYWEIGHT);
            planchePushUp.setImageId(R.drawable.exercise_image_10);
            planchePushUp.setDescription("A super hard variation of the push up that is only for expert calisthenic practitioners");
            planchePushUp.setInstructions("Lay down on the floor, put your hands by your hips rotate your hands so that the thumb is pointing upwards.\nPush down onto the ground and lean forward, brace your core and lift your legs and at the same time push against the ground and continue to have your feet and legs elevated.");
            planchePushUp.setVideoURL("https://www.youtube.com/watch?v=C9yattH2D2U");
            database.exerciseDAO().insertExercise(planchePushUp);

            Exercise resistanceBandLungeWOverheadPress = new Exercise();
            resistanceBandLungeWOverheadPress.setName("Resistance Band Lunge With Overhead Press");
            resistanceBandLungeWOverheadPress.setCategory(ExerciseCategory.BAND);
            resistanceBandLungeWOverheadPress.setImageId(R.drawable.exercise_image_11);
            resistanceBandLungeWOverheadPress.setDescription("Resistance band with lunge with overhead press is an exercise that strengthens the legs, glutes and shoulders as well as engaging the core for stability.");
            resistanceBandLungeWOverheadPress.setInstructions("Place right foot on the center of the band on the floor and hold handles next to shoulders.\nStep your left foot behind you, keeping your heel off the ground.\nLower toward the ground, behind both knees to a 90 degree angle.\nSlowly press back up as you push your arms straight overhead.\nRepeat for desired number and switch legs.");
            resistanceBandLungeWOverheadPress.setVideoURL("https://www.youtube.com/watch?v=6rNkP1bEu6Y");
            database.exerciseDAO().insertExercise(resistanceBandLungeWOverheadPress);

            Exercise hillSprints = new Exercise();
            hillSprints.setName("Hill Sprints");
            hillSprints.setCategory(ExerciseCategory.CARDIO);
            hillSprints.setImageId(R.drawable.exercise_image_12);
            hillSprints.setDescription("You sprint up a hill, it is in the name!");
            hillSprints.setInstructions("You sprint as fast as you can up a hill.");
            hillSprints.setVideoURL("https://www.youtube.com/watch?v=AgUP_u604NA");
            database.exerciseDAO().insertExercise(hillSprints);

            Exercise normalSprints = new Exercise();
            normalSprints.setName("Normal Sprints");
            normalSprints.setCategory(ExerciseCategory.CARDIO);
            normalSprints.setImageId(R.drawable.exercise_image_13);
            normalSprints.setDescription("You sprint on a flat surface.");
            normalSprints.setInstructions("You run as fast as you can on a flat surface, preferably somewhere you know the distance so you can take the time.");
            normalSprints.setVideoURL("https://www.youtube.com/watch?v=wszfRYTWzs8");
            database.exerciseDAO().insertExercise(normalSprints);

            Exercise jumpRope = new Exercise();
            jumpRope.setName("Jump Rope");
            jumpRope.setCategory(ExerciseCategory.CARDIO);
            jumpRope.setImageId(R.drawable.exercise_image_14);
            jumpRope.setDescription("You jump with the rope, good for HIIT(High Intensive Interval Training).");
            jumpRope.setInstructions("You jump with the rope in your hands in the tempo of your rope swings, dont jump to high or too quick.\nJump while standing on your toes");
            jumpRope.setVideoURL("https://www.youtube.com/watch?v=u3zgHI8QnqE");
            database.exerciseDAO().insertExercise(jumpRope);
            exerciseList.addAll(database.exerciseDAO().getAllExercises());
        }
    }

}