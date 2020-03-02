package com.firebase.studentapp;
//Making necessary imports
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
public class Floor_Map extends AppCompatActivity {
    //Declaring initial objects to be used inside this class
    private Button lg_map, g_map, ff_map, sf_map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floor_map);
        //Creating a Dialog in order to disply the images on click
        final Dialog builder_map = new Dialog(this);
        final Dialog builder_map1 = new Dialog(this);
        final Dialog builder_map2 = new Dialog(this);
        final Dialog builder_map3 = new Dialog(this);
        //Here I choose to definde the onClick Listner next to button declaration
        // For Lower Ground Map
        lg_map = findViewById(R.id.lg_map_button);
        lg_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder_map.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
                builder_map.setContentView(getLayoutInflater().inflate(R.layout.activity_lg_floor
                        , null));
                builder_map.show(); }});//end of lg_map setOnClickListener
        //For Ground Level Map
        g_map = findViewById(R.id.g_map_button);
        g_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder_map1.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
                builder_map1.setContentView(getLayoutInflater().inflate(R.layout.activity_ground_floor
                        , null));
                builder_map1.show(); }});//end of g_map setOnClickListener
        //For First Floor Map
        ff_map = findViewById(R.id.first_floor_map_button);
        ff_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder_map2.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
                builder_map2.setContentView(getLayoutInflater().inflate(R.layout.activity_firstfloor
                        , null));
                builder_map2.show(); }});//end of  ff_map setOnClickListener
        //For Second Floor Map
        sf_map = findViewById(R.id.second_floor_map_button);
        sf_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder_map3.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
                builder_map3.setContentView(getLayoutInflater().inflate(R.layout.activity_second_floor
                        , null));
                builder_map3.show(); }});//end of sf_map setOnClickListener
    }//end of Override
}//end of Class
