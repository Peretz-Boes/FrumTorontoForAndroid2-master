package com.boes.peretz.frumtoronto;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class ShulsAndTefillosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shuls_and_tefillos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
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

    public void showDaveningSchedule(View view) {
        Intent intent = new Intent(ShulsAndTefillosActivity.this, DaveningScheduleActivity.class);
        startActivity(intent);
    }

    public void showShulListings(View view) {
        Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.frumtoronto.com/DirectoryListing.asp?CategoryID=234"));
        if (intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }else {
            Toast.makeText(getApplicationContext(),R.string.no_web_browser_error_message,Toast.LENGTH_LONG).show();
        }
    }

    public void showMinchaListings(View view) {
        Intent intent = new Intent(ShulsAndTefillosActivity.this, MinchaListActivity.class);
        startActivity(intent);
    }

    public void showNewsletters(View view) {
        Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.frumtoronto.com/ShulNewsletters.asp"));
        if (intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }else {
            Toast.makeText(getApplicationContext(),R.string.no_web_browser_error_message,Toast.LENGTH_LONG).show();
        }
    }

    public void showWeeklyPublications(View view){
        Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.frumtoronto.com/WeeklyPublications.asp"));
        if (intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }else {
            Toast.makeText(getApplicationContext(),R.string.no_web_browser_error_message,Toast.LENGTH_LONG).show();
        }
    }

    public void showTehillimList(View view){
        Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.frumtoronto.com/Main.asp?Menu_Tag=daveninglist&BlogCategoryID=73"));
        if (intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }else {
            Toast.makeText(getApplicationContext(),R.string.no_web_browser_error_message,Toast.LENGTH_LONG).show();
        }
    }

}
