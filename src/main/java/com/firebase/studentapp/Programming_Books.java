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

public class Programming_Books extends AppCompatActivity {


    Button prg_info1, prg_info2, prg_add1, prg_add2, prg_cancel1, prg_cancel2, prg_review_btn, prg_read_reviews;
    EditText post_review;
    TextView prg_review_board;
    DatabaseReference programming_reservations, prg_reserv_number, programming_reviews;
    AlertDialog.Builder prg_info_popup1, prg_info_popup2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_programming__books);
        //Pointing each object to its correct counterpart from XML file
        prg_info1 = findViewById(R.id.programming_info1_btn);
        prg_info2 = findViewById(R.id.programming_info2_btn);
        prg_add1 = findViewById(R.id.programming_reserve_btn1);
        prg_add2 = findViewById(R.id.programming_reserve_btn2);
        prg_cancel1 = findViewById(R.id.programming_cancel_btn1);
        prg_cancel2 = findViewById(R.id.programming_cancel_btn2);
        prg_review_btn = findViewById(R.id.programming_review_btn);
        post_review = findViewById(R.id.post_programming_review);
        prg_review_board = findViewById(R.id.programming_review_board);
        prg_read_reviews = findViewById(R.id.programming_read_reaviews_btn);


        final String review_title = Session.LiveSession.user.getFn() + " " + Session.LiveSession.user.getSn() + " review: ";
        //setting up PopUP builders
        prg_info_popup1 = new AlertDialog.Builder(this);
        prg_info_popup2 = new AlertDialog.Builder(this);
        //Setting up OnClick listeners for Info Buttons:

        //Info1
        prg_info1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Setting message manually and performing action on button click
                prg_info_popup1.setMessage("Even bad code can function. But if code isn't clean, it can bring a development organization to its knees. \\n Every year, countless hours and significant resources are lost because of poorly written code.\\n  But it doesn't have to be that way.")

                        .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Toast.makeText(getApplicationContext(), "Reserve?",
                                        Toast.LENGTH_LONG).show();
                            }
                        });

                //Creating dialog box
                AlertDialog alert = prg_info_popup1.create();
                //Setting the title manually
                alert.setTitle("Clean Code \n");
                alert.show();
            }
        });//end of info1


        //Info2
        prg_info2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Setting message manually and performing action on button click
                prg_info_popup2.setMessage("When it comes to game programming, C++ is the name of the game.\\n If you aspire to move from game player to game creator, it all starts with learning the fundamentals of C++ and game-programming basics.")

                        .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Toast.makeText(getApplicationContext(), "Reserve?",
                                        Toast.LENGTH_LONG).show();
                            }
                        });

                //Creating dialog box
                AlertDialog alert = prg_info_popup2.create();
                //Setting the title manually
                alert.setTitle("Beginning C++ Through Game Programming \\n");
                alert.show();
            }
        });//end of info2


        //Declaring the object witch will act as a Database Reference and create a new "root"
        programming_reservations = FirebaseDatabase.getInstance().getReference("_programming_reservations_");
        prg_reserv_number = programming_reservations.child(Session.LiveSession.user.getFn() + " " + Session.LiveSession.user.getSn());
        programming_reviews = FirebaseDatabase.getInstance().getReference("_programming_reviews_");


        //Creating the Add and Remove Reservation for the books in this category
        prg_add1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int value = 1;

                prg_reserv_number.child("ISBN:278-0747595825").setValue("You have " + value + " reservations");
                Toast.makeText(Programming_Books.this, "You add a reservation. Remamber that you can have ONLY ONE reservation", Toast.LENGTH_LONG).show();
            }
        });//end of setOnClickListener


        prg_add2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int value = 1;

                prg_reserv_number.child("ISBN:268-0747595876").setValue("You have " + value + " reservations");
                Toast.makeText(Programming_Books.this, "You add a reservation. Remember that you can have ONLY ONE reservation", Toast.LENGTH_LONG).show();
            }
        });//end of setOnClickListener


        prg_cancel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int value = 0;

                prg_reserv_number.child("ISBN:278-0747595825").setValue("You have " + value + " reservations");
                Toast.makeText(Programming_Books.this, "You cancel a reservation. You can now reserve a new copy.", Toast.LENGTH_LONG).show();
            }
        });//end of setOnClickListener

        prg_cancel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int value = 0;

                prg_reserv_number.child("ISBN:268-0747595876").setValue("You have " + value + " reservations");
                Toast.makeText(Programming_Books.this, "You cancel a reservation. You can now reserve a new copy.", Toast.LENGTH_LONG).show();
            }
        });//end of setOnClickListener

        prg_review_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*Push the message into de Database under the "_" root
                Construct the Child to containd "title" and "message"
                This will help me to create an better output
                */
                Reviews review = new Reviews(review_title,post_review.getText().toString());
                programming_reviews.child(programming_reviews.push().getKey()).setValue(review);

            }//end of OnClick
        });//end of OnClickListener

        //In order to see what outher are posting, please click this button
        prg_read_reviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Add a EventListener to retreive data from the  "root"
                programming_reviews.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    //Make a database snapshot every time when  data is changing
                    public void onDataChange(DataSnapshot dataSnapshot6) {
                        /*Special method to fetch the data
                            I use HashMap and Array in order to fetch my data*/
                        collect_reviews((Map<String, Object>) dataSnapshot6.getValue());
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
        ArrayList<String> fetch_programming_reviews = new ArrayList<>();

        //iterate through each user, ignoring their UID
        for (Map.Entry<String, Object> entry : users.entrySet()) {

            //Get comments titles and messages
            Map programming_reviews = (Map) entry.getValue();
            Map programming_reviews_title = (Map) entry.getValue();

            /*Put data in order fallowed by a new line, so I can control
            the output in the TextVeiew*/

            fetch_programming_reviews.add((String)programming_reviews.get("review") + programming_reviews_title.get("review_title") +"\n");

            /* Output the Array into a TextView (No formating is requierd)

             *With no "\n" in the Array , the text will require an output format.
             */
            prg_review_board.setText(fetch_programming_reviews.toString());


        }//end of for loop

    }//end of collet_Comments method

}//end of Class

