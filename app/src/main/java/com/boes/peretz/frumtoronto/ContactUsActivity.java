package com.boes.peretz.frumtoronto;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class ContactUsActivity extends AppCompatActivity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        button=(Button)findViewById(R.id.button45);
        button.setVisibility(View.GONE);
        if (getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    public void showContactInformation(View view){
        Intent intent=new Intent(ContactUsActivity.this,ContactUsWebpageActivity.class);
        startActivity(intent);
    }

    public void showAdvertisingRates(View view){
        Intent intent=new Intent(ContactUsActivity.this,AdvertisingRatesActivity.class);
        startActivity(intent);
    }

    public void showFrequentlyAskedQuestions(View view){
        Intent intent=new Intent(ContactUsActivity.this,FrequentlyAskedQuestionsActivity.class);
        startActivity(intent);
    }

    public void showAboutFrumToronto(View view){
        Intent intent=new Intent(ContactUsActivity.this,AboutUsActivity.class);
        startActivity(intent);
    }

}
