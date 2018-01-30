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

public class DirectoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directory);
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

    public void showBusinessDirectory(View view){
        Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.frumtoronto.com/BusinessDirectory.asp"));
        if (intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }else {
            Toast.makeText(getApplicationContext(),R.string.no_web_browser_error_message,Toast.LENGTH_LONG).show();
        }
    }

    public void showRegisterYourBusinessWebpage(View view){
        Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.frumtoronto.com/BusinessApplicationForm.asp?Task=NewBusiness"));
        if (intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }else {
            Toast.makeText(getApplicationContext(),R.string.no_web_browser_error_message,Toast.LENGTH_LONG).show();
        }
    }

    public void showDirectoryRatesActivity(View view){
        Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.frumtoronto.com/BusinessDirectoryRates.asp"));
        if (intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }else {
            Toast.makeText(getApplicationContext(),R.string.no_web_browser_error_message,Toast.LENGTH_LONG).show();
        }
    }

    public void showAdvancedSearchActivity(View view){
        Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.frumtoronto.com/DirectoryListing.asp"));
        if (intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }else {
            Toast.makeText(getApplicationContext(),R.string.no_web_browser_error_message,Toast.LENGTH_LONG).show();
        }
    }

    public void showBusinessServicesActivity(View view){
        Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.frumtoronto.com/BusinessDirectory.asp?Section=8"));
        if (intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }else {
            Toast.makeText(getApplicationContext(),R.string.no_web_browser_error_message,Toast.LENGTH_LONG).show();
        }
    }

    public void showClothingAndAccessoriesActivity(View view){
        Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.frumtoronto.com/BusinessDirectory.asp?Section=11"));
        if (intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }else {
            Toast.makeText(getApplicationContext(),R.string.no_web_browser_error_message,Toast.LENGTH_LONG).show();
        }
    }

    public void showFinancialServicesActivity(View view){
        Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.frumtoronto.com/BusinessDirectory.asp?Section=4"));
        if (intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }else {
            Toast.makeText(getApplicationContext(),R.string.no_web_browser_error_message,Toast.LENGTH_LONG).show();
        }
    }

    public void showGovernmentAndInstitutionsActivity(View view){
        Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.frumtoronto.com/BusinessDirectory.asp?Section=5"));
        if (intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }else {
            Toast.makeText(getApplicationContext(),R.string.no_web_browser_error_message,Toast.LENGTH_LONG).show();
        }
    }

    public void showHeathAndBeautyActivity(View view){
        Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.frumtoronto.com/BusinessDirectory.asp?Section=16"));
        if (intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }else {
            Toast.makeText(getApplicationContext(),R.string.no_web_browser_error_message,Toast.LENGTH_LONG).show();
        }
    }

    public void showHomeAndGardenActivity(View view){
        Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.frumtoronto.com/BusinessDirectory.asp?Section=13"));
        if (intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }else {
            Toast.makeText(getApplicationContext(),R.string.no_web_browser_error_message,Toast.LENGTH_LONG).show();
        }
    }

    public void showJewishCommunityServicesActivity(View view){
        Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.frumtoronto.com/BusinessDirectory.asp?Section=3"));
        if (intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }else {
            Toast.makeText(getApplicationContext(),R.string.no_web_browser_error_message,Toast.LENGTH_LONG).show();
        }
    }

    public void showKosherFoodsActivity(View view){
        Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.frumtoronto.com/BusinessDirectory.asp?Section=15"));
        if (intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }else {
            Toast.makeText(getApplicationContext(),R.string.no_web_browser_error_message,Toast.LENGTH_LONG).show();
        }
    }

    public void showPropertyAndAccommodationsActivity(View view){
        Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.frumtoronto.com/BusinessDirectory.asp?Section=6"));
        if (intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }else {
            Toast.makeText(getApplicationContext(),R.string.no_web_browser_error_message,Toast.LENGTH_LONG).show();
        }
    }

    public void showServicesActivity(View view){
        Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.frumtoronto.com/BusinessDirectory.asp?Section=7"));
        if (intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }else {
            Toast.makeText(getApplicationContext(),R.string.no_web_browser_error_message,Toast.LENGTH_LONG).show();
        }
    }

    public void showShoppingActivity(View view){
        Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.frumtoronto.com/BusinessDirectory.asp?Section=12"));
        if (intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }else {
            Toast.makeText(getApplicationContext(),R.string.no_web_browser_error_message,Toast.LENGTH_LONG).show();
        }
    }

    public void showSimchasActivity(View view){
        Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.frumtoronto.com/BusinessDirectory.asp?Section=9"));
        if (intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }else {
            Toast.makeText(getApplicationContext(),R.string.no_web_browser_error_message,Toast.LENGTH_LONG).show();
        }
    }

    public void showSportAndLeisureActivity(View view){
        Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.frumtoronto.com/BusinessDirectory.asp?Section=10"));
        if (intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }else {
            Toast.makeText(getApplicationContext(),R.string.no_web_browser_error_message,Toast.LENGTH_LONG).show();
        }
    }

    public void showTransportAndAutoActivity(View view){
        Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.frumtoronto.com/BusinessDirectory.asp?Section=14"));
        if (intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }else {
            Toast.makeText(getApplicationContext(),R.string.no_web_browser_error_message,Toast.LENGTH_LONG).show();
        }
    }

}

