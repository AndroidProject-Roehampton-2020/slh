package com.firebase.studentapp;
//Making necessary imports
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Forum_Main_Menu extends AppCompatActivity {

    //Pointing each object to its correct counterpart from XML file
    private Button button_forum1, button_forum2, button_forum4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum__main__menu);

        //Pointing objects to XML counterpart
        button_forum1 = findViewById(R.id.button_forum1);
        button_forum2 = findViewById(R.id.button_forum2);
        button_forum4 = findViewById(R.id.button_forum4);

        //Defining onClickListeners
        button_forum1 = findViewById(R.id.button_forum1);
        button_forum1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forum1();
            }
        });

        button_forum2 = findViewById(R.id.button_forum2);
        button_forum2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forum2();
            }
        });

        button_forum4 = findViewById(R.id.button_forum4);
        button_forum4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forum4();
            }
        });
    }//end of onCreate


    //Declare the forum methods
    public void forum1() {
        Intent intent = new Intent(this, Forum1.class);
        startActivity(intent);
    } // end of forum1

    public void forum2() {
        Intent intent = new Intent(this, Forum2.class);
        startActivity(intent);
    } // end of forum2


    public void forum4() {
        Intent intent = new Intent(this, Forum4.class);
        startActivity(intent);
    } // end of forum4


}//end of Class
