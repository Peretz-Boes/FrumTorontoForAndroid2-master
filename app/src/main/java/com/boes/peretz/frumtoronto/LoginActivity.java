package com.boes.peretz.frumtoronto;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements VerificationDialogFragment.NoticeDialogListener {

    public static final String LOG_TAG=LoginActivity.class.getSimpleName();
    EditText name;
    EditText password;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        name=(EditText)findViewById(R.id.name_edit_text);
        password=(EditText)findViewById(R.id.password_edit_text);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void attemptRegistration(View view) {
        VerificationDialogFragment verificationDialogFragment=new VerificationDialogFragment();
        verificationDialogFragment.show(getSupportFragmentManager(),"verification");
    }

    public void attemptLogin(View view){
        firebaseAuth=FirebaseAuth.getInstance();
        if (WebServicesUtils.isInternetServiceAvailable(getApplicationContext())) {
            firebaseAuth.signInWithEmailAndPassword(name.getText().toString(),password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Intent intent = new Intent(LoginActivity.this, PersonDataActivity.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(getApplicationContext(), R.string.log_in_error_message,Toast.LENGTH_LONG).show();
                        Log.d(LOG_TAG,"Error "+task.getException());
                    }
                }
            });
        }else {
            Toast.makeText(getApplicationContext(),R.string.internet_connection_error_message,Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialogFragment) {
        firebaseAuth = FirebaseAuth.getInstance();
        if (WebServicesUtils.isInternetServiceAvailable(getApplicationContext())) {
            firebaseAuth.createUserWithEmailAndPassword(name.getText().toString(), password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), R.string.registration_successful_message, Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), R.string.registration_error_message, Toast.LENGTH_LONG).show();
                        Log.d(LOG_TAG, "Error " + task.getException());
                    }
                }
            });
        }else {
            Toast.makeText(getApplicationContext(), R.string.internet_connection_error_message, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialogFragment) {
        dialogFragment.dismiss();
    }
}
