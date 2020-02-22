package com.firebase.studentapp;

//Making the necessary imports

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    //Declaring initial objects to be used inside this class
    EditText id, em, pw;
    Button btn_login, register, lost_pass;
    FirebaseAuth fbAuth; //Used to sign in to FB
    Query dbref; //Used to read record from FB db
    AlertDialog.Builder pass_popup1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Pointing each object to its correct counterpart from XML file
        id = findViewById(R.id.et_id);
        em = findViewById(R.id.et_email);
        pw = findViewById(R.id.et_pw);
        btn_login = findViewById(R.id.btn_login);
        register = findViewById(R.id.register_button);
        lost_pass=findViewById(R.id.forgot_pass);

        //Declaring the Register button onClcikListener
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register_click();
            }
        });//end of onClickListener

        //Initialise our FB auth instance
        fbAuth = FirebaseAuth.getInstance();

        //Making the Dialog Biulder and defining the Forgot Pass message

        pass_popup1 = new AlertDialog.Builder(this);
        lost_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Setting message manually and performing action on button click
                pass_popup1.setMessage("For testing purpuses, there are two premade accounts\n Student id= 666 or 999 \n E-mail= www@ww.com or test@user.com \n Pass (for both accounts) = test12")

                        .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Toast.makeText(getApplicationContext(), "This application is supporting the Univertiy ANTI-GHOSTING CAMPAIN.",
                                        Toast.LENGTH_LONG).show();
                            }
                        });//end of PositiveButton

                //Creating dialog box
                AlertDialog alert = pass_popup1.create();
                //Setting the title manually
                alert.setTitle("FOR TESTING YOU CAN USE THE FALLOWING ACCOUNTS!!");
                alert.show();
            }//end of OnClick
        });//end of onClickListener

        //Setting up the Login Button
          btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    fbAuth.signInWithEmailAndPassword(em.getText().toString(), pw.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {

                            ValueEventListener listener = new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    for (DataSnapshot dss : dataSnapshot.getChildren()) {
                                        Session.LiveSession.user = dss.getValue(User.class);
                                    }// end of DantaSnapshot

                                    //If successful
                                    if (Session.LiveSession.user != null) {
                                        Toast.makeText(Login.this, "Welcome " + Session.LiveSession.user.getFn() + " " + Session.LiveSession.user.getSn(), Toast.LENGTH_LONG).show();
                                        Toast.makeText(Login.this, "Please remember. \n Is better to be JUST ANOTHER STUDENT \n then JUST another Cheater.", Toast.LENGTH_LONG).show();
                                        main_menu_click();
                                    }//end of if
                                    //If NOT
                                    else {
                                        Toast.makeText(Login.this, "Sign in failed", Toast.LENGTH_LONG).show();
                                        //fbAuth.signOut();
                                    }//end of else

                                }//end of onDataChange

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }//end of onCancelled
                            };//end of EventListener

                            //Declaring the object witch will act as a Database Reference
                            dbref = FirebaseDatabase.getInstance().getReference("_user_").orderByChild("id").equalTo(id.getText().toString());
                            dbref.addListenerForSingleValueEvent(listener);
                        }//end of OnSuccess

                    });//end of signInWithEmailAndPassword
            }//end of OnClick
        });//end of setOnClickListener


    }//end of onCreate


    //Defining the click methods

    //For Registration Method
    public void register_click() {
        Intent intent = new Intent(this, MainRegister.class);
        startActivity(intent);
    }//end of register_click

    // Main-Menu click method to be used if login is successful
    public void main_menu_click() {
        Intent intent = new Intent(this, Main_Menu.class);
        startActivity(intent);
    }//end of main_menu_click


}//end of Class
