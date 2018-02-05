package com.boes.peretz.frumtoronto;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void showTorontoWeather(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.theweathernetwork.com/ca/weather/ontario/toronto"));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), R.string.no_web_browser_error_message, Toast.LENGTH_LONG).show();
        }
    }

    public void showWeatherNetworkWebsite(View view){
        Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.theweathernetwork.com/ca"));
        if (intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }else {
            Toast.makeText(getApplicationContext(),R.string.no_web_browser_error_message,Toast.LENGTH_LONG).show();
        }
    }

    public void showAlertsActiviy(View view){
        Intent intent=new Intent(MainActivity.this,AlertsActivity.class);
        startActivity(intent);
    }

    public void showCalendarActivity(View view){
        Intent intent=new Intent(MainActivity.this,CalendarActivity.class);
        startActivity(intent);
    }

    public void showClassifiedActivity(View view){
        Intent intent=new Intent(MainActivity.this,ClassifiedActivity.class);
        startActivity(intent);
    }

    public void showContactUsActivity(View view){
        Intent intent=new Intent(MainActivity.this,ContactUsActivity.class);
        startActivity(intent);
    }

    public void showDirectoryActivity(View view){
        Intent intent=new Intent(MainActivity.this,DirectoryActivity.class);
        startActivity(intent);
    }

    public void showShulsAndTefillosActivity(View view){
        Intent intent=new Intent(MainActivity.this,ShulsAndTefillosActivity.class);
        startActivity(intent);
    }

    public void showAskTheRabbiWebpage(View view){
        Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.frumtoronto.com/Blogger.asp?BlogCategoryID=98"));
        if (intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }else {
            Toast.makeText(getApplicationContext(), R.string.no_web_browser_error_message,Toast.LENGTH_LONG).show();
        }
    }

    public void showBecomeAMemberWebpage(View view){
        Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.frumtoronto.com/MemberApplicationForm.asp?Task=NewMember"));
        if (intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }else {
            Toast.makeText(getApplicationContext(),R.string.no_web_browser_error_message,Toast.LENGTH_LONG).show();
        }
    }

    public void showPersonsDirectoryLoginPage(View view){
        Intent intent=new Intent(MainActivity.this,LoginActivity.class);
        startActivity(intent);
    }

    public void showMessageBoard(View view){
        Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.frumtoronto.com/Blogger.asp?BlogCategoryID=44"));
        if (intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }else {
            Toast.makeText(getApplicationContext(),R.string.no_web_browser_error_message,Toast.LENGTH_LONG).show();
        }
    }

    public void showPersonsDirectory(View view){
        Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse("http://mobileapps.x10host.com/database.php"));
        if (intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }else {
            Toast.makeText(getApplicationContext(),R.string.no_web_browser_error_message,Toast.LENGTH_LONG).show();
        }
    }

    public void showCalendarEventForm(View view){
        Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.frumtoronto.com/diary_EditE.asp?mode=Add"));
        if (intent.resolveActivity(getPackageManager())!=null){
            Toast.makeText(getApplicationContext(), R.string.calendar_login_instructions,Toast.LENGTH_LONG).show();
            startActivity(intent);
        }else {
            Toast.makeText(getApplicationContext(),R.string.no_web_browser_error_message,Toast.LENGTH_LONG).show();
        }
    }

    public void showPersonsDirectorySearchActivity(View view){
        Intent intent=new Intent(MainActivity.this,PersonsDirectorySearchActivity.class);
        startActivity(intent);
    }

}
