package com.boes.peretz.frumtoronto;

import android.content.ContentValues;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import org.json.JSONObject;

import java.io.InputStream;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    private UserLoginRegisterTask userLoginRegisterTask=null;
    EditText name;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        name=(EditText)findViewById(R.id.name_edit_text);
        password=(EditText)findViewById(R.id.password_edit_text);
        if (getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    public void attemptRegistrationOrLogin(View view) {
        if (userLoginRegisterTask != null) {
            return;
        }
        name.setError(null);
        password.setError(null);
        String username = name.getText().toString();
        String passwordText = password.getText().toString();
        boolean cancel = false;
        View focusView = null;
        if (!TextUtils.isEmpty(passwordText) && !isPasswordValid(passwordText)) {
            password.setError(getString(R.string.password_error_message));
            focusView = password;
            cancel = true;
        }
        if (cancel) {
            focusView.requestFocus();
        } else {
            userLoginRegisterTask = new UserLoginRegisterTask(username, passwordText, view.getId() == R.id.login_button);
            userLoginRegisterTask.execute((Void) null);
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

    private boolean isPasswordValid(String password){
        return password.length()>4;
    }

    private void showProgress(final boolean isShow){
        findViewById(R.id.login_progress).setVisibility(isShow?View.VISIBLE:View.GONE);
        findViewById(R.id.login_form).setVisibility(isShow?View.GONE:View.VISIBLE);
    }

    private class UserLoginRegisterTask extends WebServicesTask{

        private final ContentValues contentValues=new ContentValues();
        private boolean isLoggedIn;
        UserLoginRegisterTask(String username,String password, boolean loggedIn){
            super(LoginActivity.this);
            contentValues.put(Constants.NAME,username);
            contentValues.put(Constants.PASSWORD,password);
            contentValues.put(Constants.GRANT_TYPE,Constants.CLIENT_CREDENTIALS);
            isLoggedIn=loggedIn;
        }

        @Override
        public void showProgress() {
            LoginActivity.this.showProgress(true);
        }

        @Override
        public void hideProgress() {
            LoginActivity.this.showProgress(false);
        }

        @Override
        public boolean performRequest() {
            JSONObject object=WebServicesUtils.requestJSONObject(isLoggedIn?Constants.LOGIN_URL:Constants.REGISTRATION_URL,WebServicesUtils.METHOD.POST,contentValues,true);
            userLoginRegisterTask=null;
            if (!hasError(object)){
                if (isLoggedIn){
                    User user=new User();
                    user.setId(object.optLong(Constants.ID));
                    user.setUsername(contentValues.getAsString(Constants.USERNAME));
                    user.setPassword(contentValues.getAsString(Constants.PASSWORD));
                    RESTServiceApplication.getInstance().setUser(user);
                    RESTServiceApplication.getInstance().setAccessToken(object.optJSONObject(Constants.ACCESS).optString(Constants.ACCESS_TOKEN));
                    return true;
                }else {
                    isLoggedIn=true;
                    performRequest();
                    return true;
                }
            }
            return false;
        }

        @Override
        public void performSuccessfulOperation() {
            Intent intent=new Intent(LoginActivity.this,PersonDataActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

}
