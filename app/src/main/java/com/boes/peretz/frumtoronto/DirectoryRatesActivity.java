package com.boes.peretz.frumtoronto;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class DirectoryRatesActivity extends AppCompatActivity {

    public static final String LOG_TAG=DirectoryRatesActivity.class.getSimpleName();
    WebView webView;
    String result="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directory_rates);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        webView=(WebView)findViewById(R.id.directory_rates_web_view);
        webView.setWebViewClient(new WebViewClient());
        if (getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        if (isInternetServiceAvailable()) {
            ParseDirectoryRatesWebPage parseDirectoryRatesWebPage = new ParseDirectoryRatesWebPage();
            Toast.makeText(getApplicationContext(), R.string.loading_message,Toast.LENGTH_LONG).show();
            parseDirectoryRatesWebPage.execute();
        }else {
            Toast.makeText(getApplicationContext(), R.string.internet_connection_error_message, Toast.LENGTH_LONG).show();
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

    public class ParseDirectoryRatesWebPage extends AsyncTask<String,Void,String> {
        @Override
        protected String doInBackground(String... strings) {
            Document document;
            try {
                document= Jsoup.connect("http://www.frumtoronto.com/BusinessDirectoryRates.asp").get();
                Log.d(LOG_TAG,document.toString());
                result=document.toString();
            }catch (IOException exception){
                exception.printStackTrace();
            }
            return "Executed";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            webView.loadData(result,"text/html",null);
            if (result.equals("")){
                Toast.makeText(getApplicationContext(),R.string.query_error_message,Toast.LENGTH_LONG).show();
            }
        }
    }

    public boolean isInternetServiceAvailable(){
        ConnectivityManager connectivityManager=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
        if (connectivityManager.getActiveNetworkInfo()==null){
            return false;
        }
        return networkInfo.isConnected()||networkInfo.isConnectedOrConnecting();
    }

}