package com.firebase.studentapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Time_Table extends AppCompatActivity {

     /*Due to the lack of skills in Java and Android,
    I choose to let the Student choose its own Time Table */

    private Button year1, year2, back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table);

        // Year 1 Button
        year1 = findViewById(R.id.year1_button);
        year1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                year1_time_table();
            }
        });
        // Year 2 Button
        year2 = findViewById(R.id.year2_button);
        year2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                year2_time_table();
            }
        });
        // Year 3 Button

        // Back to Main Menu
        back = findViewById(R.id.back1_button);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main_menu();
            }
        });
    }//end of Override

    //Declaring the above specific methods for every click on every button

    public void year1_time_table() {
        Intent intent = new Intent(this, year1.class);
        startActivity(intent);
    } // end of year1 method

    public void year2_time_table() {
        Intent intent = new Intent(this, year2.class);
        startActivity(intent);
    } // end of year2 method


    public void main_menu() {
        Intent intent = new Intent(this, Main_Menu.class);
        startActivity(intent);
    } // end of Back method

}//end of Class