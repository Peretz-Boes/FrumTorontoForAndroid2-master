package com.boes.peretz.frumtoronto;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class PersonDataActivity extends AppCompatActivity {

    private UserInfoTask userInfoTask=null;
    private UserEditTask userEditTask=null;
    private UserResetTask userResetTask=null;
    private UserDeleteTask userDeleteTask=null;
    private EditText usernameText;
    private EditText passwordText;
    private EditText addressText;
    private EditText phoneNumberText;

    private interface ConfirmationListener{
        void onConfirmation(boolean isConfirmed);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_data);
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
        initializeViews();
        showProgress(true);
        userInfoTask=new UserInfoTask();
        userInfoTask.execute();
    }

    private void initializeViews(){
        usernameText=(EditText)findViewById(R.id.username_data_field);
        passwordText=(EditText)findViewById(R.id.password_data_field);
        addressText=(EditText)findViewById(R.id.address_data_field);
        phoneNumberText=(EditText)findViewById(R.id.phone_number_data_field);
    }

    private void showProgress(final boolean isShow){
        findViewById(R.id.progress).setVisibility(isShow?View.VISIBLE:View.GONE);
        findViewById(R.id.info_form).setVisibility(isShow?View.GONE:View.VISIBLE);
    }

    private void populateText(){
        User user=RESTServiceApplication.getInstance().getUser();
        usernameText.setText(user.getUsername()==null?"":user.getUsername());
        passwordText.setText(user.getPassword());
        phoneNumberText.setText(user.getPhoneNumber()==null?"":user.getPhoneNumber());
        addressText.setText(user.getAddress());
    }

    public void clickUpdateButton(View view){
        if (passwordText.getText().toString().trim().length()>=5){
            showProgress(true);
            userEditTask=new UserEditTask();
            userEditTask.execute();
        }else {
            Toast.makeText(getApplicationContext(),R.string.password_error_message,Toast.LENGTH_LONG).show();
        }
    }

    public void clickDeleteButton(View view){
        showConfigurationDialog(new ConfirmationListener(){
            @Override
            public void onConfirmation(boolean isConfirmed){
                showProgress(true);
                userDeleteTask=new UserDeleteTask();
                userDeleteTask.execute();
            }
        });
    }

    public void clickResetButton(View view){
        showConfigurationDialog(new ConfirmationListener() {
            @Override
            public void onConfirmation(boolean isConfirmed) {
                if (isConfirmed){
                    showProgress(true);
                    userResetTask.execute();
                }
            }
        });
    }

    public void clickSignoutButton(View view){
        showLoginScreen();
    }

    private void showLoginScreen(){
        Intent intent=new Intent(PersonDataActivity.this,LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void showConfigurationDialog(final ConfirmationListener confirmationListener){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Confirmation");
        builder.setMessage("Are you sure? This operation cannot be undone.");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                confirmationListener.onConfirmation(true);
                dialogInterface.dismiss();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                confirmationListener.onConfirmation(false);
                dialogInterface.dismiss();
            }
        });
        AlertDialog alertDialog=builder.create();
        alertDialog.setCancelable(true);
        alertDialog.show();
    }

    private abstract class ActivityWebServiceTask extends WebServicesTask{
        public ActivityWebServiceTask(WebServicesTask webServicesTask) {
            super(PersonDataActivity.this);
        }

        @Override
        public void showProgress() {
            PersonDataActivity.this.showProgress(true);
        }

        @Override
        public void hideProgress() {
            PersonDataActivity.this.showProgress(false);
        }

        @Override
        public void performSuccessfulOperation() {
            populateText();
        }
    }

    private class UserInfoTask extends ActivityWebServiceTask{
        public UserInfoTask() {
            super(userInfoTask);
        }

        @Override
        public boolean performRequest() {
            ContentValues contentValues = new ContentValues();
            User user = RESTServiceApplication.getInstance().getUser();
            contentValues.put(Constants.ID, user.getId());
            contentValues.put(Constants.ACCESS_TOKEN, RESTServiceApplication.getInstance().getAccessToken());
            JSONObject object = WebServicesUtils.requestJSONObject(Constants.INFO_URL, WebServicesUtils.METHOD.GET, contentValues, null);
            if (!hasError(object)) {
                JSONArray jsonArray = object.optJSONArray(Constants.INFO);
                JSONObject jsonObject = jsonArray.optJSONObject(0);
                user.setName(jsonObject.optString(Constants.NAME));
                if (user.getName().equalsIgnoreCase("null")) {
                    user.setName(null);
                }
                user.setPhoneNumber(jsonObject.optString(Constants.PHONE_NUMBER));
                if (user.getPhoneNumber().equalsIgnoreCase("null")) {
                    user.setPhoneNumber(null);
                }
                user.setId(object.optLong(Constants.ID_INFO));
                return true;
            }
            return false;
        }

    }

    private class UserEditTask extends ActivityWebServiceTask {
        public UserEditTask() {
            super(userEditTask);
        }

        @Override
        public boolean performRequest() {
            ContentValues contentValues=new ContentValues();
            User user=RESTServiceApplication.getInstance().getUser();
            contentValues.put(Constants.ID,user.getId());
            contentValues.put(Constants.PASSWORD,passwordText.getText().toString());
            contentValues.put(Constants.NAME,usernameText.getText().toString());
            ContentValues urlValues=new ContentValues();
            urlValues.put(Constants.ACCESS_TOKEN,RESTServiceApplication.getInstance().getAccessToken());
            JSONObject object=WebServicesUtils.requestJSONObject(Constants.UPDATE_URL,WebServicesUtils.METHOD.POST,urlValues,contentValues);
            if (!hasError(object)){
                JSONArray jsonArray=object.optJSONArray(Constants.INFO);
                JSONObject jsonObject=jsonArray.optJSONObject(0);
                user.setName(jsonObject.optString(Constants.NAME));
                user.setPhoneNumber(jsonObject.optString(Constants.PHONE_NUMBER));
                user.setPassword(jsonObject.optString(Constants.PASSWORD));
            }
            return false;
        }
    }

    private class UserResetTask extends ActivityWebServiceTask{
        public UserResetTask() {
            super(userResetTask);
        }

        @Override
        public boolean performRequest() {
            ContentValues contentValues=new ContentValues();
            User user=RESTServiceApplication.getInstance().getUser();
            contentValues.put(Constants.ID,user.getId());
            contentValues.put(Constants.ACCESS_TOKEN,RESTServiceApplication.getInstance().getAccessToken());
            JSONObject object=WebServicesUtils.requestJSONObject(Constants.RESET_URL,WebServicesUtils.METHOD.POST,contentValues,null);
            if (!hasError(object)){
                user.setName("");
                user.setPhoneNumber("");
                user.setAddress("");
                user.setUsername("");
                return true;
            }
            return false;
        }
    }

    private class UserDeleteTask extends ActivityWebServiceTask{

        public UserDeleteTask(){
            super(userDeleteTask);
        }

        @Override
        public void performSuccessfulOperation() {
            showLoginScreen();
        }

        @Override
        public boolean performRequest() {
            ContentValues contentValues=new ContentValues();
            User user=RESTServiceApplication.getInstance().getUser();
            contentValues.put(Constants.ID,user.getId());
            contentValues.put(Constants.ACCESS_TOKEN,RESTServiceApplication.getInstance().getAccessToken());
            JSONObject object=WebServicesUtils.requestJSONObject(Constants.DELETE_URL,WebServicesUtils.METHOD.DELETE,contentValues,null);
            if (!hasError(object)){
                RESTServiceApplication.getInstance().setUser(null);
                return true;
            }
            return false;
        }
    }

}
