package com.boes.peretz.frumtoronto;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class ZmanimActivity extends AppCompatActivity {

    public static final String LOG_TAG=ZmanimActivity.class.getSimpleName();
    WebView webView;
    String result="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zmanim);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        webView=(WebView)findViewById(R.id.zmanim_web_view);
        webView.setWebViewClient(new WebViewClient());
        Toast.makeText(getApplicationContext(),R.string.web_view_warning_message,Toast.LENGTH_LONG).show();
        if (getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        if (isInternetServiceAvailable()) {
            ParseZmanimWebPage parseZmanimWebPage = new ParseZmanimWebPage();
            Toast.makeText(getApplicationContext(), R.string.loading_message,Toast.LENGTH_LONG).show();
            parseZmanimWebPage.execute();
        }else {
            Toast.makeText(getApplicationContext(), R.string.internet_connection_error_message, Toast.LENGTH_LONG).show();
        }
    }

    public class ParseZmanimWebPage extends AsyncTask<String,Void,String> {
        @Override
        protected String doInBackground(String... strings) {
            Document document;
            try {
                document= Jsoup.connect("http://www.frumtoronto.com/Zmanim.asp#today").get();
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
