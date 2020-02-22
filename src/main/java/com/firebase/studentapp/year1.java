package com.firebase.studentapp;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class year1 extends AppCompatActivity {

    /*Declaring the buttons
     */
    private Button y1b1, y1b2, y1b3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_year1);

        //Creating a Dialog in order to disply the images on click
        final Dialog y1_map1 = new Dialog(this);
        final Dialog y1_map2 = new Dialog(this);
        final Dialog y1_map3 = new Dialog(this);
        //Here I will define the buttons first, and only after I will declare the onClick listener

        //Buttons first
        y1b1 = findViewById(R.id.y1_button1);
        y1b2 = findViewById(R.id.y1_button2);
        y1b3 = findViewById(R.id.y1_button3);

        //Onclick listeners

        y1b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                y1_map1.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
                y1_map1.setContentView(getLayoutInflater().inflate(R.layout.year1map1
                        , null));
                y1_map1.show();
            }
        });
        y1b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                y1_map2.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
                y1_map2.setContentView(getLayoutInflater().inflate(R.layout.year1map2
                        , null));
                y1_map2.show();
            }
        });
        y1b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                y1_map3.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
                y1_map3.setContentView(getLayoutInflater().inflate(R.layout.year1map3
                        , null));
                y1_map3.show();
            }
        });

    }
}
