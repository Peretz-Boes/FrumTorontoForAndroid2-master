package com.boes.peretz.frumtoronto;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONObject;

/**
 * Created by alanrabinowitz on 2017-12-20.
 */

public abstract class WebServicesTask extends AsyncTask<Void,Void,Boolean> {

    public static final String LOG_TAG=WebServicesTask.class.getSimpleName();
    public abstract void showProgress();
    public abstract boolean performRequest();
    public abstract void performSuccessfulOperation();
    public abstract void hideProgress();
    private String message;
    private Context context;

    public WebServicesTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        showProgress();
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        if (!WebServicesUtils.isInternetServiceAvailable(context)){
            message=String.valueOf(R.string.internet_connection_error_message);
            return false;
        }
        return performRequest();
    }

    @Override
    protected void onPostExecute(Boolean success) {
        hideProgress();
        if (success){
            performSuccessfulOperation();
        }
        if (message!=null&&!message.isEmpty()){
            Toast.makeText(context,message,Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onCancelled(Boolean aBoolean) {
        hideProgress();
    }

    public boolean hasError(JSONObject jsonObject){
        if (jsonObject!=null){
            int status=jsonObject.optInt(Constants.STATUS);
            Log.d(LOG_TAG,"Response "+jsonObject.toString());
            message=jsonObject.optString(Constants.MESSAGE);
            if (status==Constants.STATUS_ERROR||status==Constants.STATUS_UNAUTHORIZED){
                return true;
            }else {
                return false;
            }
        }
        message=context.getString(R.string.url_not_found_error_message);
        return true;
    }

}
