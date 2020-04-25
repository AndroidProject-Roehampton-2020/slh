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

public class Forum1 extends AppCompatActivity {
    //Declaring initial objects to be used inside this class
    Button post_btn, fetch;
    EditText message;
    DatabaseReference forum1;
    TextView post_update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum1);
        //Pointing each object to its correct counterpart from XML file
        post_btn = findViewById(R.id.post_btn);
        message = findViewById(R.id.comment_area);
        fetch = findViewById(R.id.btnFetch);
        post_update = findViewById(R.id.tvValue);
        /*creating an predified String to be able to say the difference between the user
            (Who is saying this??)
            Without this parameter will be no distinctions between the messages*/
        final String post_title = "Student " + Session.LiveSession.user.getFn() +
                " " + Session.LiveSession.user.getSn() + " says: ";
        //Declaring the object witch will act as a Database Reference and create a new "root"
        forum1 = FirebaseDatabase.getInstance().getReference("_forum1_");
        //Create a new message
        post_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*Push the message into de Database under the "_forum1_" root
                Construct the Child to containd "title" and "message"
                This will help me to create an better output*/
                Message comment = new Message(post_title, message.getText().toString());
                forum1.child(forum1.push().getKey()).setValue(comment);
            }//end of OnClick
        });//end of OnClickListener
        //In order to see what outher are posting, please click this button
        fetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Add a EventListener to retreive data from the  "root"
                forum1.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    //Make a database snapshot every time when  data is changing
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        /*Special method to fetch the data
                            I use HashMap and Array in order to fetch my data*/
                        collect_comments((Map<String, Object>) dataSnapshot.getValue());
                    }//end of onDataChange

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }//end of onCanceld
                });//end of Listener
            }//end of OnClick
        });//end of OnClickListener
    }//end of onCreate

    //Its time to fetch my data
    private void collect_comments(Map<String, Object> users) {
        //Create an Array to fetch de data from Firebase
        ArrayList<String> fetch_title = new ArrayList<>();
        //iterate through each user, ignoring their UID
        for (Map.Entry<String, Object> entry : users.entrySet()) {
            //Get comments titles and messages
            Map title = (Map) entry.getValue();
            Map msg = (Map) entry.getValue();
            /*Put data in order fallowed by a new line, so I can control
            the output in the TextVeiew*/
            fetch_title.add((String) title.get("post_title") + msg.get("message") + "\n");
            /* Output the Array into a TextView (No formating is requierd)
             With no "\n" in the Array , the text will require an output format.*/
            post_update.setText(fetch_title.toString());
        }//end of for loop
    }//end of collect_comments method
} //end of Class


