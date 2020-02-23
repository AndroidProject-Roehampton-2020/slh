package com.firebase.studentapp;
//Making necessary imports

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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainRegister extends AppCompatActivity {

    //Declaring initial objects to be used inside this class
    FirebaseAuth fbAuth;
    private EditText id, fn, sn, em, pw, cpw;
    private Button btn_reg, btn_login,btn_ghost ;
    AlertDialog.Builder ghost_popup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_register);
        //initialise our fbAuth
        fbAuth = FirebaseAuth.getInstance();

        //Pointing each object to its correct counterpart from XML file
        id = findViewById(R.id.et_studentid_register);
        fn = findViewById(R.id.et_fn_register);
        sn = findViewById(R.id.et_sn_register);
        em = findViewById(R.id.et_em_register);
        pw = findViewById(R.id.et_pw_register);
        cpw = findViewById(R.id.et_cpw_register);
        btn_login=findViewById(R.id.registration_login_btn);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login_page();
            }
        });
        btn_ghost=findViewById(R.id.registration_login_btn2);


        btn_reg = findViewById(R.id.btn_register_register);

        //Setup Register Button
        btn_reg.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                    fbAuth.createUserWithEmailAndPassword(em.getText().toString(), pw.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            fbAuth.signInWithEmailAndPassword(em.getText().toString(), pw.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    User u = new User(fn.getText().toString(), sn.getText().toString(), id.getText().toString());
                                    DatabaseReference dbref = FirebaseDatabase.getInstance().getReference("_user_");
                                    dbref.child(fbAuth.getUid()).setValue(u);
                                    fbAuth.signOut();
                                    Toast.makeText(MainRegister.this, "Registration Successful", Toast.LENGTH_LONG).show();
                                }
                            });//end of signInWithEmailAndPassword
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            if (e instanceof FirebaseAuthInvalidCredentialsException)
                                Toast.makeText(MainRegister.this, "Email badly formatted", Toast.LENGTH_LONG).show();
                        }
                    });//end of OnFailureListener



            }//end of onClick
        });//end of setOnClickListener





        //setting Ghost Popup
        ghost_popup = new AlertDialog.Builder(this);
        btn_ghost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Setting message manually and performing action on button click
                ghost_popup.setMessage("Each individual has a core of underlying values that contribute to his or her system of beliefs, ideas and/or opinions. \n The word ITEGRITY is defined as adherence to moral principles and the quality of being unimpaired. \n The term of INTEGRITY originates from the Latin adjective INTEGER meaning whole and complete.\n" +
                        "As you can see, INTEGRITY is a human behavior characteristic and not just a skill. \n Student or not, you dear user, are a human in the first place. \n How do you live your life outside the school, in truth or in a lie? \n As in everyday life, your are free to choose your actions. You will steal this program , or learn from it??")

                        .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Toast.makeText(getApplicationContext(), "THANK YOU FOR NOT CHEATING",
                                        Toast.LENGTH_LONG).show();
                            }
                        });//end of PositiveButton

                //Creating dialog box
                AlertDialog alert = ghost_popup.create();
                //Setting the title manually
                alert.setTitle("INTEGRITY IS NOT JUST AN ACADEMIC SKILL");
                alert.show();
            }//end of OnClick
        });//end of onClickListener
    }//end of onCreate

    //Declaring the above specific methods for every click on every button
    public void login_page() {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    } // end of login_page

}//end of Class
