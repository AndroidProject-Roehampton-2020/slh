package com.firebase.studentapp;
//Making necessary imports

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class MailReceive extends AppCompatActivity {
    //Declaring initial objects to be used inside this class
    Button mail_receive, mail_new, back_mail;
    TextView mail_container;
    Query dbref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail_receive);
        //Pointing each object to its correct counterpart from XML file
        mail_new = findViewById(R.id.send_new_message_btn);
        mail_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new_mail();
            }
        });
        back_mail = findViewById(R.id.mail_receive_back_btn);
        back_mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });
        mail_receive = findViewById(R.id.receive_mail_btn);
        mail_container = findViewById(R.id.tvMail);
        //Declaring the object witch will act as a Query Database Reference
        dbref = FirebaseDatabase.getInstance().getReference("mail_database").
                orderByChild("receiver").equalTo(Session.LiveSession.user.getId());
        //Receive mail button
        mail_receive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Add a EventListener to retreive data from the  "mail_database"
                dbref.addListenerForSingleValueEvent(new ValueEventListener() {
                    private DataSnapshot dataSnapshot;

                    @Override
                    //Make a database snapshot every time when  data is changing
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        this.dataSnapshot = dataSnapshot;
                        /*Special method to fetch the data
                            I use HashMap and Array in order to fetch my data*/
                        collect_mails((Map<String, Object>) dataSnapshot.getValue());
                    }//end of onDataChange

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }//end of onCanceld
                });//end of Listener
            }//end of OnClick
        });//end of OnClickListener
    }//end of onCreate

    private void collect_mails(Map<String, Object> mail) {
        //Create an Array to fetch de data from Firebase
        ArrayList<String> fetch_mail = new ArrayList<>();
        for (@NonNull Map.Entry<String, Object> entry : mail.entrySet()) {
            //Get comments titles and messages
            Map sender = (Map) entry.getValue();
            Map subject = (Map) entry.getValue();
            Map mailtext = (Map) entry.getValue();
            /*Put data in order fallowed by a new line, so I can control
            the output in the TextVeiew*/
            fetch_mail.add("Message from UID: " + sender.get("sender") +
                    "\n" + "Subject:" + subject.get("subject") + "\n"
                    + "Message:" + mailtext.get("mail"));
            /* Output the Array into a TextView (No formating is requierd)
             With no "\n" in the Array , the text will require an output format.*/
            mail_container.setText(fetch_mail.toString());
        }//end of for loop
    }//end of collect_mails method

    public void new_mail() {
        Intent intent = new Intent(this, MailSend.class);
        startActivity(intent);
    } // end of new_mail action

    public void back() {
        Intent intent = new Intent(this, Main_Menu.class);
        startActivity(intent);
    } // end of back action
}//end of class

