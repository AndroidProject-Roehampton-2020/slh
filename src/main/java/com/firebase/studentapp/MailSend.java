package com.firebase.studentapp;
//Making necessary imports

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MailSend extends AppCompatActivity {
    EditText receiver, mail_subject, text_mail;
    Button send_btn, inbox;
    DatabaseReference mail_root, mail_box;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Pointing each object to its correct counterpart from XML file
        setContentView(R.layout.activity_mail_send);
        inbox = findViewById(R.id.inbox_btn);
        send_btn = findViewById(R.id.mails_send_btn);
        receiver = findViewById(R.id.mail_send_receiver_input);
        mail_subject = findViewById(R.id.mail_send_subject_input);
        text_mail = findViewById(R.id.mail_text_message);
        final String sender = Session.LiveSession.user.getId();
        mail_root = FirebaseDatabase.getInstance().getReference("mail_database");
        mail_box = mail_root.child(receiver.getText().toString());
        //Send Button Click Action
        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*Push the message into de Database under the "mail_database" root
                Construct the Child to contain all fields required.
                This will help me to create an better output*/
                MailMessage newmail = new MailMessage(sender, receiver.getText().toString(),
                        mail_subject.getText().toString(), text_mail.getText().toString());
                mail_box.child(mail_box.push().getKey()).setValue(newmail);
            }//end of OnClick
        });//end of OnClickListener
        //Back to inbox button
        inbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inboxclass();
            }
        });
    }//en of onCreate

    public void inboxclass() {
        Intent intent = new Intent(this, MailReceive.class);
        startActivity(intent);
    } // end of inboxclass
}//end of Class
