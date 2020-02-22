package com.firebase.studentapp;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class Managment_Books extends AppCompatActivity {


    Button mgn_info1, mgn_info2, mgn_add1, mgn_add2, mgn_cancel1, mgn_cancel2, mgn_review_btn, mgn_read_reviews;
    EditText post_review;
    TextView mgn_review_board;
    DatabaseReference managment_reservations, mgn_reserv_number, managment_reviews;
    AlertDialog.Builder mng_info_popup1, mng_info_popup2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_managment__books);
        //Pointing each object to its correct counterpart from XML file
        mgn_info1 = findViewById(R.id.managment_info1_btn);
        mgn_info2 = findViewById(R.id.managment_info2_btn);
        mgn_add1 = findViewById(R.id.managment_reserve_btn1);
        mgn_add2 = findViewById(R.id.managment_reserve_btn2);
        mgn_cancel1 = findViewById(R.id.managment_cancel_btn1);
        mgn_cancel2 = findViewById(R.id.managment_cancel_btn2);
        mgn_review_btn = findViewById(R.id.managment_review_btn);
        post_review = findViewById(R.id.post_managment_review);
        mgn_review_board = findViewById(R.id.managment_review_board);
        mgn_read_reviews = findViewById(R.id.managment_read_reaviews_btn);

        //setting up PopUP builders
        mng_info_popup1 = new AlertDialog.Builder(this);
        mng_info_popup2 = new AlertDialog.Builder(this);


        //Setting up OnClick listeners for Info Buttons:

        //Info1
        mgn_info1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Setting message manually and performing action on button click
                mng_info_popup1.setMessage("Interwoven with the fictional story of a woman who becomes CEO of a struggling, high-profile company with a dysfunctional executive team is an analysis of the five corruptions, diagnostic questions to help readers assess their organizations, and a teamwork model of the action steps to overcome the corruptions.")

                        .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Toast.makeText(getApplicationContext(), "Reserve?",
                                        Toast.LENGTH_LONG).show();
                            }
                        });

                //Creating dialog box
                AlertDialog alert = mng_info_popup1.create();
                //Setting the title manually
                alert.setTitle("The Five Dysfunctions of a Team\n");
                alert.show();
            }
        });//end of info1


        //Info2
        mgn_info2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Setting message manually and performing action on button click
                mng_info_popup2.setMessage("Shares the secret to sales success: don't just build relationships with customers. \\n This title argues that classic relationship-building is the wrong approach.")

                        .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Toast.makeText(getApplicationContext(), "Reserve?",
                                        Toast.LENGTH_LONG).show();
                            }
                        });

                //Creating dialog box
                AlertDialog alert = mng_info_popup2.create();
                //Setting the title manually
                alert.setTitle("The Challenger Sale \n");
                alert.show();
            }
        });//end of info2


        //Declaring the object witch will act as a Database Reference and create a new "root"
        managment_reservations = FirebaseDatabase.getInstance().getReference("_managment_reservations_");
        mgn_reserv_number = managment_reservations.child(Session.LiveSession.user.getFn() + " " + Session.LiveSession.user.getSn());
        managment_reviews = FirebaseDatabase.getInstance().getReference("_managment_reviews_");

        //Creating the Add and Remove Reservation for the books in this category
        mgn_add1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int value = 1;

                mgn_reserv_number.child("ISBN:378-0747595829").setValue("You have " + value + " reservations");
                Toast.makeText(Managment_Books.this, "You add a reservation. Remamber that you can have ONLY ONE reservation", Toast.LENGTH_LONG).show();
            }
        });//end of setOnClickListener


        mgn_add2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int value = 1;

                mgn_reserv_number.child("ISBN:368-0747595846").setValue("You have " + value + " reservations");
                Toast.makeText(Managment_Books.this, "You add a reservation. Remember that you can have ONLY ONE reservation", Toast.LENGTH_LONG).show();
            }
        });//end of setOnClickListener


        mgn_cancel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int value = 0;

                mgn_reserv_number.child("ISBN:378-0747595829").setValue("You have " + value + " reservations");
                Toast.makeText(Managment_Books.this, "You cancel a reservation. You can now reserve a new copy.", Toast.LENGTH_LONG).show();
            }
        });//end of setOnClickListener

        mgn_cancel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int value = 0;

                mgn_reserv_number.child("ISBN:368-0747595846").setValue("You have " + value + " reservations");
                Toast.makeText(Managment_Books.this, "You cancel a reservation. You can now reserve a new copy.", Toast.LENGTH_LONG).show();
            }
        });//end of setOnClickListener

        //Setup the Review Button, so the user can post a review on this books
        mgn_review_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*Push the message into de Database under the "_" root
                Construct the Child to containd "title" and "message"
                This will help me to create an better output
                */
                Reviews review = new Reviews(post_review.getText().toString());
                managment_reviews.child(managment_reviews.push().getKey()).setValue(review);

            }//end of OnClick
        });//end of OnClickListener

        //In order to see what outher are posting, please click this button
        mgn_read_reviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Add a EventListener to retreive data from the  "root"
                managment_reviews.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    //Make a database snapshot every time when  data is changing
                    public void onDataChange(DataSnapshot dataSnapshot4) {
                        /*Special method to fetch the data
                            I use HashMap and Array in order to fetch my data*/
                        collect_reviews((Map<String, Object>) dataSnapshot4.getValue());
                    }//end of onDataChange

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }//end of onCanceld
                });//end of Listener
            }//end of OnClick
        });//end of OnClickListener


    }//end of onCreate


    //Its time to fetch my data
    private void collect_reviews(Map<String, Object> users) {


        //Create an Array to fetch de data from Firebase
        ArrayList<String> fetch_managment_reviews = new ArrayList<>();

        //iterate through each user, ignoring their UID
        for (Map.Entry<String, Object> entry : users.entrySet()) {

            //Get comments titles and messages
            Map managment_reviews = (Map) entry.getValue();


            /*Put data in order fallowed by a new line, so I can control
            the output in the TextVeiew*/

            fetch_managment_reviews.add(managment_reviews.get("review") + "\n");

            /* Output the Array into a TextView (No formatting is required)
            *  *With no "\n" in the Array , the text will require an output format.
             */
            mgn_review_board.setText(fetch_managment_reviews.toString());


        }//end of for loop

    }//end of collect_Comments method

}//end of Class

