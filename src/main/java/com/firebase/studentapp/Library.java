package com.firebase.studentapp;
//Making necessary imports

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Library extends AppCompatActivity {
    // Declaring private buttons to be used only by this class:
    private Button economy, maths, programming, managment, back_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);
        //Pointing each object to its correct counterpart from XML file
        economy = findViewById(R.id.economy_books);
        managment = findViewById(R.id.managment_books);
        programming = findViewById(R.id.programming_books);
        maths = findViewById(R.id.math_books);
        back_btn = findViewById(R.id.back_2);
        //setup listeners for OnClick action
        economy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                economy_click();
            }
        });
        managment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                managment_click();
            }
        });
        maths.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                maths_click();
            }
        });
        programming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                programming_click();
            }
        });
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back_click();
            }
        });
    }//end of Override

    //defining click methods
    public void economy_click() {
        Intent intent = new Intent(this, Economy_books.class);
        startActivity(intent);
    }//end of economy_click

    public void managment_click() {
        Intent intent = new Intent(this, Managment_Books.class);
        startActivity(intent);
    }//end of managment_click

    public void maths_click() {
        Intent intent = new Intent(this, Math_Books.class);
        startActivity(intent);
    }//end of math_click

    public void programming_click() {
        Intent intent = new Intent(this, Programming_Books.class);
        startActivity(intent);
    }//end of programming_click

    public void back_click() {
        Intent intent = new Intent(this, Main_Menu.class);
        startActivity(intent);
    }//end of back_click
}//end of CLASS


