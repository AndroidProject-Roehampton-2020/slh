package com.firebase.studentapp;

//Making necessary imports

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

public class Economy_books extends AppCompatActivity {
    //Declaring initial objects to be used inside this class

    Button ec_info1, ec_info2, ec_add1, ec_add2, ec_cancel1, ec_cancel2, ec_review_btn, ec_read_reviews;
    EditText post_review;
    TextView ec_review_board;
    DatabaseReference economy_reservations, ec_reserv_number, economy_reviews;
    AlertDialog.Builder ec_info_popup1, ec_info_popup2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_economy_books);

        //Pointing each object to its correct counterpart from XML file
        ec_info1 = findViewById(R.id.economy_info1_btn);
        ec_info2 = findViewById(R.id.economy_info2_btn);
        ec_add1 = findViewById(R.id.economy_reserve_btn1);
        ec_add2 = findViewById(R.id.economy_reserve_btn2);
        ec_cancel1 = findViewById(R.id.economy_cancel_btn1);
        ec_cancel2 = findViewById(R.id.economy_cancel_btn2);
        ec_review_btn = findViewById(R.id.economy_review_btn);
        post_review = findViewById(R.id.post_economy_review);
        ec_review_board = findViewById(R.id.economy_review_board);
        ec_read_reviews = findViewById(R.id.economy_read_reaviews_btn);
        //setting up PopUP builders
        ec_info_popup1 = new AlertDialog.Builder(this);
        ec_info_popup2 = new AlertDialog.Builder(this);


        //Setting up OnClick listeners for Info Buttons:

        //Info1
        ec_info1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Setting message manually and performing action on button click
                ec_info_popup1.setMessage("Presents the philosophy of value investing, which helps protect investors against the areas of possible substantial error and teaches them to develop long-term strategies with which they will be comfortable down the road. \n This book enables you to make the right decisions to protect your investments and make them a success.")

                        .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Toast.makeText(getApplicationContext(), "Reserve?",
                                        Toast.LENGTH_LONG).show();
                            }
                        });

                //Creating dialog box
                AlertDialog alert = ec_info_popup1.create();
                //Setting the title manually
                alert.setTitle("The Intelligent Investor");
                alert.show();
            }
        });//end of info1


        //Info2
        ec_info2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Setting message manually and performing action on button click
                ec_info_popup2.setMessage("How to reconstruct your life? \\n Whether your dream is experiencing high-end world travel, earning a monthly five-figure income with zero management, or just living more and working less, this book teaches you how to double your income, and how to outsource your life to overseas virtual assistants for $5 per hour and do whatever you want.")

                        .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Toast.makeText(getApplicationContext(), "Reserve?",
                                        Toast.LENGTH_LONG).show();
                            }
                        });

                //Creating dialog box
                AlertDialog alert = ec_info_popup2.create();
                //Setting the title manually
                alert.setTitle("The 4-Hour Work Week");
                alert.show();
            }
        });//end of info2


        //Declaring the object witch will act as a Database Reference and create a new "root"
        economy_reservations = FirebaseDatabase.getInstance().getReference("_economy_reservations_");
        ec_reserv_number = economy_reservations.child(Session.LiveSession.user.getFn() + " " + Session.LiveSession.user.getSn());
        economy_reviews = FirebaseDatabase.getInstance().getReference("_economy_reviews_");

        //Creating the Add and Remove Reservation for the books in this category

        ec_add1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int value = 1;

                ec_reserv_number.child("ISBN:978-0747595823").setValue("You have " + value + " reservations");
                Toast.makeText(Economy_books.this, "You add a reservation. Remamber that you can have ONLY ONE reservation", Toast.LENGTH_LONG).show();
            }
        });//end of setOnClickListener


        ec_add2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int value = 1;

                ec_reserv_number.child("ISBN:978-0747595824").setValue("You have " + value + " reservations");
                Toast.makeText(Economy_books.this, "You add a reservation. Remember that you can have ONLY ONE reservation", Toast.LENGTH_LONG).show();
            }
        });//end of setOnClickListener


        ec_cancel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int value = 0;

                ec_reserv_number.child("ISBN:978-0747595823").setValue("You have " + value + " reservations");
                Toast.makeText(Economy_books.this, "You cancel a reservation. You can now reserve a new copy.", Toast.LENGTH_LONG).show();
            }
        });//end of setOnClickListener

        ec_cancel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int value = 0;

                ec_reserv_number.child("ISBN:978-0747595824").setValue("You have " + value + " reservations");
                Toast.makeText(Economy_books.this, "You cancel a reservation. You can now reserve a new copy.", Toast.LENGTH_LONG).show();
            }
        });//end of setOnClickListener


        //Setup the Review Button, so the user can post a review on this books
        ec_review_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*Push the message into de Database under the "_" root
                Construct the Child to containd "title" and "message"
                This will help me to create an better output
                */
                Reviews review = new Reviews(post_review.getText().toString());
                economy_reviews.child(economy_reviews.push().getKey()).setValue(review);

            }//end of OnClick
        });//end of OnClickListener

        //In order to see what outher are posting, please click this button
        ec_read_reviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Add a EventListener to retrieve data from the  "root"
                economy_reviews.addListenerForSingleValueEvent(new ValueEventListener() {
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
        ArrayList<String> fetch_economy_reviews = new ArrayList<>();

        //iterate through each user, ignoring their UID
        for (Map.Entry<String, Object> entry : users.entrySet()) {

            //Get comments titles and messages
            Map economy_reviews = (Map) entry.getValue();


            /*Put data in order fallowed by a new line, so I can control
            the output in the TextVeiew*/

            fetch_economy_reviews.add(economy_reviews.get("review") + "\n");

            /* Output the Array into a TextView (No formating is requierd)

             *With no "\n" in the Array , the text will require an output format.
             */
            ec_review_board.setText(fetch_economy_reviews.toString());


        }//end of for loop

    }//end of collect_Comments method

}//end of Class
