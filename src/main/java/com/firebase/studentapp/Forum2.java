package com.firebase.studentapp;
// Making all imports

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class Forum2 extends AppCompatActivity {
    //Declaring initial objects to be used inside this class
    Button post_btn1, fetch1;
    EditText message1;
    DatabaseReference forum2;
    TextView post_update1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum2);
        //Pointing each object to its correct counterpart from XML file
        post_btn1 = findViewById(R.id.post_btn1);
        message1 = findViewById(R.id.comment_area1);
        fetch1 = findViewById(R.id.btnFetch1);
        post_update1 = findViewById(R.id.tvValue1);
        /*creating an predified String to be able to say the difference between the users
            (Who is saying this??)
            Without this parameter will be no distinctions between the messages*/
        final String post_title1 = "Student " + Session.LiveSession.user.getFn() +
                " " + Session.LiveSession.user.getSn() + " says: ";
        //Declaring the object witch will act as a Database Reference and create a new "root"
        forum2 = FirebaseDatabase.getInstance().getReference("_forum2_");
        //Create a new message
        post_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*Push the message into de Database under the "_forum2_" root
                Construct the Child to containd "title" and "message"
                This will help me to create an better output
                */
                Message comment = new Message(post_title1, message1.getText().toString());
                forum2.child(forum2.push().getKey()).setValue(comment);
            }//end of OnClick
        });//end of OnClickListener
        //In order to see what outher are posting, please click this button
        fetch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Add a EventListener to retreive data from the  "root"
                forum2.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    //Make a database snapshot every time when  data is changing
                    public void onDataChange(DataSnapshot dataSnapshot1) {
                        /*Special method to fetch the data
                            I use HashMap and Array in order to fetch my data*/
                        collect_comments1((Map<String, Object>) dataSnapshot1.getValue());
                    }//end of onDataChange

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }//end of onCanceld
                });//end of Listener
            }//end of OnClick
        });//end of OnClickListener
    }//end of OnCreate

    //Its time to fetch my data
    private void collect_comments1(Map<String, Object> users) {
        //Create an Array to fetch de data from Firebase
        ArrayList<String> fetch_title1 = new ArrayList<>();
        //iterate through each user, ignoring their UID
        for (Map.Entry<String, Object> entry : users.entrySet()) {
            //Get comments titles and messages
            Map title1 = (Map) entry.getValue();
            Map msg1 = (Map) entry.getValue();
            /*Put data in order fallowed by a new line, so I can control
            the output in the TextVeiew*/
            fetch_title1.add((String) title1.get("post_title") + msg1.get("message") + "\n");
            /* Output the Array into a TextView (No formating is requierd)
                With no "\n" in the Array , the text will require an output format.*/
            post_update1.setText(fetch_title1.toString());
        }//end of for loop
    }//end of collect_comments1 method
}//end of Class
