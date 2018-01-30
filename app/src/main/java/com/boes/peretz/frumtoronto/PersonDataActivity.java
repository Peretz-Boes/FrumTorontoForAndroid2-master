package com.boes.peretz.frumtoronto;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PersonDataActivity extends AppCompatActivity {

    public static final String LOG_TAG=PersonDataActivity.class.getSimpleName();
    DatabaseReference databaseReference;
    FirebaseUser firebaseUser;
    private EditText usernameText;
    private EditText addressText;
    private EditText phoneNumberText;
    String firebaseUserId="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_data);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        initializeViews();
        databaseReference= FirebaseDatabase.getInstance().getReference();
        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser!=null){
            firebaseUserId=firebaseUser.getUid();
        }
        Log.d(LOG_TAG,"User id "+firebaseUserId);
    }

    private void initializeViews(){
        usernameText=(EditText)findViewById(R.id.username_data_field);
        addressText=(EditText)findViewById(R.id.address_data_field);
        phoneNumberText=(EditText)findViewById(R.id.phone_number_data_field);
    }

    public void clickUpdateButton(View view){
        databaseReference.child("users").child(firebaseUserId).child("address").setValue(addressText.getText().toString());
        databaseReference.child("users").child(firebaseUserId).child("phoneNumber").setValue(phoneNumberText.getText().toString());
        databaseReference.child("users").child(firebaseUserId).child("username").setValue(usernameText.getText().toString());
        Toast.makeText(getApplicationContext(), R.string.update_successful_message,Toast.LENGTH_LONG).show();
    }

    public void clickSignoutButton(View view){
        showLoginScreen();
    }

    private void showLoginScreen(){
        FirebaseAuth.getInstance().signOut();
        NavUtils.navigateUpFromSameTask(this);
    }

}
