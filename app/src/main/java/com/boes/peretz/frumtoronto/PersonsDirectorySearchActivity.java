package com.boes.peretz.frumtoronto;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Iterator;

public class PersonsDirectorySearchActivity extends AppCompatActivity {

    public static final String LOG_TAG=PersonsDirectorySearchActivity.class.getSimpleName();
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persons_directory_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        editText=(EditText)findViewById(R.id.search_query_edit_text);
    }

    public void executeSearchQuery(View view){
        Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse("https://frumtoronto-a2f1d.firebaseio.com/users/"+editText.getText().toString()+".json?print=pretty"));
        if (intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }else {
            Toast.makeText(getApplicationContext(),R.string.no_web_browser_error_message,Toast.LENGTH_LONG).show();
        }

    }

}
