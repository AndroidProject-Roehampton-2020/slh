package com.firebase.studentapp;
//Making Imports

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class year2 extends AppCompatActivity {
    /*Declaring the buttons  */
    private Button y2b1, y2b2, y2b3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_year2);
        //Creating a Dialog in order to disply the images on click
        final Dialog y2_map1 = new Dialog(this);
        final Dialog y2_map2 = new Dialog(this);
        final Dialog y2_map3 = new Dialog(this);
        //Here I will define the buttons first, and only after I will declare the onClick listener
        //Buttons first
        y2b1 = findViewById(R.id.y3b1);
        y2b2 = findViewById(R.id.y2b2);
        y2b3 = findViewById(R.id.y2b3);
        //Onclick listeners
        y2b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                y2_map1.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
                y2_map1.setContentView(getLayoutInflater().inflate(R.layout.year2map1
                        , null));
                y2_map1.show();
            }
        });//end of y2b1
        y2b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                y2_map2.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
                y2_map2.setContentView(getLayoutInflater().inflate(R.layout.year2map2
                        , null));
                y2_map2.show();
            }
        });//end of y2b2
        y2b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                y2_map3.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
                y2_map3.setContentView(getLayoutInflater().inflate(R.layout.year2map3
                        , null));
                y2_map3.show();
            }
        });//end of y2b3
    }//end of onCreate
}//end of Class
