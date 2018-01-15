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
        Intent intent =new Intent(DirectoryActivity.this,BuisnessDirectoryActivity.class);
        startActivity(intent);
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
        Intent intent=new Intent(DirectoryActivity.this,DirectoryRatesActivity.class);
        startActivity(intent);
    }

    public void showAdvancedSearchActivity(View view){
        Intent intent=new Intent(DirectoryActivity.this,AdvancedSearchActivity.class);
        startActivity(intent);
    }

    public void showBusinessServicesActivity(View view){
        Intent intent=new Intent(DirectoryActivity.this,BusinessServicesActivity.class);
        startActivity(intent);
    }

    public void showClothingAndAccessoriesActivity(View view){
        Intent intent=new Intent(DirectoryActivity.this,ClothingAndAccessoriesActivity.class);
        startActivity(intent);
    }

    public void showFinancialServicesActivity(View view){
        Intent intent=new Intent(DirectoryActivity.this,FinancialServicesActivity.class);
        startActivity(intent);
    }

    public void showGovernmentAndInstitutionsActivity(View view){
        Intent intent=new Intent(DirectoryActivity.this,GovernmentAndInstitutionsActivity.class);
        startActivity(intent);
    }

    public void showHeathAndBeautyActivity(View view){
        Intent intent=new Intent(DirectoryActivity.this,HealthAndBeautyActivity.class);
        startActivity(intent);
    }

    public void showHomeAndGardenActivity(View view){
        Intent intent=new Intent(DirectoryActivity.this,HomeAndGardenActivity.class);
        startActivity(intent);
    }

    public void showJewishCommunityServicesActivity(View view){
        Intent intent=new Intent(DirectoryActivity.this,JewishCommunityServicesActivity.class);
        startActivity(intent);
    }

    public void showKosherFoodsActivity(View view){
        Intent intent=new Intent(DirectoryActivity.this,KosherFoodsActivity.class);
        startActivity(intent);
    }

    public void showPropertyAndAccommodationsActivity(View view){
        Intent intent=new Intent(DirectoryActivity.this,PropertyAndAccomodationsActivity.class);
        startActivity(intent);
    }

    public void showServicesActivity(View view){
        Intent intent=new Intent(DirectoryActivity.this,ServicesActivity.class);
        startActivity(intent);
    }

    public void showShoppingActivity(View view){
        Intent intent=new Intent(DirectoryActivity.this,ShoppingActivity.class);
        startActivity(intent);
    }

    public void showSimchasActivity(View view){
        Intent intent=new Intent(DirectoryActivity.this,SimchasActivity.class);
        startActivity(intent);
    }

    public void showSportAndLeisureActivity(View view){
        Intent intent=new Intent(DirectoryActivity.this,SportAndLeisureActivity.class);
        startActivity(intent);
    }

    public void showTransportAndAutoActivity(View view){
        Intent intent=new Intent(DirectoryActivity.this,TransportAndAutoActivity.class);
        startActivity(intent);
    }

}

