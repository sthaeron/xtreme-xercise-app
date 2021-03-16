package se.hkr.xtremexerciseapp.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Exercise {

    @PrimaryKey(autoGenerate = true)
    public int exerciseId;

    @ColumnInfo(name = "Name")
    public String name;

    @ColumnInfo(name = "Category")
    public int category;
    // 1 = kettle bell
    // 2 = bodyweight
    // 3 = band
    // 4 = cardio

    @ColumnInfo(name = "Image_Id")
    public int imageId;

    @ColumnInfo(name = "Description")
    public String description;

    @ColumnInfo(name = "Instructions")
    public String instructions;

    @ColumnInfo(name = "Video_URL")
    public String videoURL;
    @ColumnInfo (name = "Image_URL")
    public String imageURL;

    public int getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public String getImageURL(){ return imageURL;}

    public void setImageURL(String imageURL) { this.imageURL = imageURL; }
}
