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

public class Math_Books extends AppCompatActivity {


    Button math_info1, math_info2, math_add1, math_add2, math_cancel1, math_cancel2, math_review_btn, math_read_reviews;
    EditText post_review;
    TextView math_review_board;
    DatabaseReference mathematics_reservations, math_reserv_number, mathematics_reviews;
    AlertDialog.Builder math_info_popup1, math_info_popup2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math__books);
        //Pointing each object to its correct counterpart from XML file
        math_info1 = findViewById(R.id.mathematics_info1_btn);
        math_info2 = findViewById(R.id.mathematics_info2_btn);
        math_add1 = findViewById(R.id.mathematics_reserve_btn1);
        math_add2 = findViewById(R.id.mathematics_reserve_btn2);
        math_cancel1 = findViewById(R.id.mathematics_cancel_btn1);
        math_cancel2 = findViewById(R.id.mathematics_cancel_btn2);
        math_review_btn = findViewById(R.id.mathematics_review_btn);
        post_review = findViewById(R.id.post_mathematics_review);
        math_review_board = findViewById(R.id.mathematics_review_board);
        math_read_reviews = findViewById(R.id.mathematics_read_reaviews_btn);


        //setting up PopUP builders
        math_info_popup1 = new AlertDialog.Builder(this);
        math_info_popup2 = new AlertDialog.Builder(this);



        //Setting up OnClick listeners for Info Buttons:

        //Info1
        math_info1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Setting message manually and performing action on button click
                math_info_popup1.setMessage("Who invented zero? Why 60 seconds in a minute? How big is infinity? Where do parallel lines meet? \n And can a butterfly's wings really cause a storm on the far side of the world?")

                        .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Toast.makeText(getApplicationContext(), "Reserve?",
                                        Toast.LENGTH_LONG).show();
                            }
                        });

                //Creating dialog box
                AlertDialog alert = math_info_popup1.create();
                //Setting the title manually
                alert.setTitle("50 Maths Ideas You Really Need to Know");
                alert.show();
            }
        });//end of info1


        //Info2
        math_info2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Setting message manually and performing action on button click
                math_info_popup2.setMessage("It's a big step up from GCSE Maths to the new AS-Level and A-level Maths courses - which is why we've rustled up this brilliant Head Start book! It recaps all the crucial topics students will need to remember from GCSE, and gives them a taste of how they're used at A-Level. \n Packed with study notes, examples and practice questions, it's the perfect way for students to get off to a flying start in September.")

                        .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Toast.makeText(getApplicationContext(), "Reserve?",
                                        Toast.LENGTH_LONG).show();
                            }
                        });

                //Creating dialog box
                AlertDialog alert = math_info_popup2.create();
                //Setting the title manually
                alert.setTitle("New Head Start to A-Level Maths");
                alert.show();
            }
        });//end of info2


        //Declaring the object witch will act as a Database Reference and create a new "root"
        mathematics_reservations = FirebaseDatabase.getInstance().getReference("_mathematics_reservations_");
        math_reserv_number = mathematics_reservations.child(Session.LiveSession.user.getFn() + " " + Session.LiveSession.user.getSn());
        mathematics_reviews = FirebaseDatabase.getInstance().getReference("_mathematics_reviews_");



        //Creating the Add and Remove Reservation for the books in this category
        math_add1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int value = 1;
                math_reserv_number.child("ISBN:978-0747595823").setValue("You have " + value + " reservations");
                Toast.makeText(Math_Books.this, "You add a reservation. Remamber that you can have ONLY ONE reservation", Toast.LENGTH_LONG).show();
            }
        });//end of setOnClickListener


        math_add2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int value = 1;

                math_reserv_number.child("ISBN:168-0747595826").setValue("You have " + value + " reservations");
                Toast.makeText(Math_Books.this, "You add a reservation. Remember that you can have ONLY ONE reservation", Toast.LENGTH_LONG).show();
            }
        });//end of setOnClickListener


        math_cancel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int value = 0;

                math_reserv_number.child("ISBN:978-0747595823").setValue("You have " + value + " reservations");
                Toast.makeText(Math_Books.this, "You cancel a reservation. You can now reserve a new copy.", Toast.LENGTH_LONG).show();
            }
        });//end of setOnClickListener

        math_cancel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int value = 0;

                math_reserv_number.child("ISBN:168-0747595826").setValue("You have " + value + " reservations");
                Toast.makeText(Math_Books.this, "You cancel a reservation. You can now reserve a new copy.", Toast.LENGTH_LONG).show();
            }
        });//end of setOnClickListener

        math_review_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*Push the message into de Database under the "_" root
                Construct the Child to containd "title" and "message"
                This will help me to create an better output
                */
                Reviews review = new Reviews(post_review.getText().toString());
                mathematics_reviews.child(mathematics_reviews.push().getKey()).setValue(review);

            }//end of OnClick
        });//end of OnClickListener

        //In order to see what outher are posting, please click this button
        math_read_reviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Add a EventListener to retreive data from the  "root"
                mathematics_reviews.addListenerForSingleValueEvent(new ValueEventListener() {
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
        ArrayList<String> fetch_mathematics_reviews = new ArrayList<>();

        //iterate through each user, ignoring their UID
        for (Map.Entry<String, Object> entry : users.entrySet()) {

            //Get comments titles and messages
            Map mathematics_reviews = (Map) entry.getValue();


            /*Put data in order fallowed by a new line, so I can control
            the output in the TextVeiew*/

            fetch_mathematics_reviews.add(mathematics_reviews.get("review") + "\n");

            /* Output the Array into a TextView (No formating is requierd)

             *With no "\n" in the Array , the text will require an output format.
             */
            math_review_board.setText(fetch_mathematics_reviews.toString());


        }//end of for loop

    }//end of collet_Comments method

}//end of Class
