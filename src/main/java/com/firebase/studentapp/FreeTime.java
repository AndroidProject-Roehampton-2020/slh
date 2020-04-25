package com.firebase.studentapp;
//Making necessary imports

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class FreeTime extends AppCompatActivity {
    //Declaring initial objects to be used inside this class
    private Button btn_fb, btn_google, btn_tw, btn_link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_time);
        //Pointing objects to XML counterpart
        btn_fb = findViewById(R.id.button_social_facebook);
        btn_google = findViewById(R.id.button_social_google);
        btn_tw = findViewById(R.id.button_social_twetter);
        btn_link = findViewById(R.id.button_social_linkedin);
        //Defining onClickListeners
        btn_fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mfacebook();
            }
        });
        btn_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mgoogle();
            }
        });
        btn_tw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mtwetter();
            }
        });
        btn_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mlink();
            }
        });
    }//end of onCreate

    //Declare the Free Time methods
    public void mfacebook() {
        Intent intent = new Intent(this, social1.class);
        startActivity(intent);
    } // end of facebook method

    public void mgoogle() {
        Intent intent = new Intent(this, social2.class);
        startActivity(intent);
    } // end of google method

    public void mtwetter() {
        Intent intent = new Intent(this, social4.class);
        startActivity(intent);
    } // end of  twitter method

    public void mlink() {
        Intent intent = new Intent(this, social3.class);
        startActivity(intent);
    } // end of linkedin method
}//end of Class
