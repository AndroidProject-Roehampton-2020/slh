package com.firebase.studentapp;

//making necessary imports
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class Main_Menu extends AppCompatActivity {
    //Declaring initial objects to be used inside this class

    private Button timetable, floormap, forum, library, freetime;

    //Declaring button actions on click listener
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__menu);

        // Time Table Button
        timetable = findViewById(R.id.time_table_button);
        timetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tableselect();
            }
        });
        // Floor Map Button
        floormap = findViewById(R.id.floor_map_button);
        floormap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mapselect();
            }
        });
        // Forum1 Button
        forum = findViewById(R.id.forum_button);
        forum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forumselect();
            }
        });
        //Library Button
        library = findViewById(R.id.lybrary_button);
        library.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                libraryselect();
            }
        });
        //Free Time Button
        freetime = findViewById(R.id.free_time_button);
        freetime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                freeselect();
            }
        });
    }//end of Override

    //Declaring the above specific methods for every click on every button
    public void tableselect() {
        Intent intent = new Intent(this, Time_Table.class);
        startActivity(intent);
    } // end of time_table action

    public void mapselect() {
        Intent intent = new Intent(this, Floor_Map.class);
        startActivity(intent);
    } // end of floor_map action

    public void forumselect() {
        Intent intent = new Intent(this, Forum_Main_Menu.class);
        startActivity(intent);
    } // end of FORUM action

    public void libraryselect() {
        Intent intent = new Intent(this, Library.class);
        startActivity(intent);
    }// end of Library action

    public void freeselect() {
        Intent intent = new Intent(this, FreeTime.class);
        startActivity(intent);
    } // end of Free Time action


} //end of Main_Menu CLASS