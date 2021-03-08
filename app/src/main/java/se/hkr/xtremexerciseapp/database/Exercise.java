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
    public String category;

    @ColumnInfo(name = "Image_Id")
    public int imageId;

    @ColumnInfo(name = "Description")
    public String description;

    @ColumnInfo(name = "Instructions")
    public String instructions;

    @ColumnInfo(name = "Video_URL")
    public String videoURL;

}
