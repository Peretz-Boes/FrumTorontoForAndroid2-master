package com.boes.peretz.frumtoronto;

import android.content.ContentValues;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.util.Base64;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;

/**
 * Created by alanrabinowitz on 2018-01-02.
 */

public class WebServicesUtils {
    public static final String LOG_TAG=WebServicesUtils.class.getSimpleName();
    public enum METHOD {
        POST, GET, DELETE
    }

    public static JSONObject requestJSONObject(String serviceUrl, METHOD method, ContentValues headerValues, boolean hasAuthorization){
        return requestJSONObject(serviceUrl,method,headerValues,null,null,hasAuthorization);
    }

    public static JSONObject requestJSONObject(String serviceUrl,METHOD method, ContentValues urlValues, ContentValues bodyValues){
        return requestJSONObject(serviceUrl,method,null,urlValues,bodyValues,false);
    }

    public static JSONObject requestJSONObject(String serviceUrl,METHOD method,ContentValues headerValues, ContentValues urlValues, ContentValues bodyValues, boolean hasAuthorization){
        HttpURLConnection urlConnection=null;
        try {
            if (urlValues!=null){
                serviceUrl=addParametersToUrl(serviceUrl,urlValues);
            }
            URL urlToRequest=new URL(serviceUrl);
            urlConnection=(HttpURLConnection)urlToRequest.openConnection();
            urlConnection.setConnectTimeout(Constants.CONNECTION_TIMEOUT);
            urlConnection.setReadTimeout(Constants.READ_TIMEOUT);
            urlConnection.setRequestMethod(method.toString());
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            if (hasAuthorization){
                addBasicAuthorization(urlConnection);
            }
            if (headerValues!=null){
                Uri.Builder builder=new Uri.Builder();
                for (String key:headerValues.keySet()){
                    builder.appendQueryParameter(key,headerValues.getAsString(key));
                }
                String query=builder.build().getEncodedQuery();
                OutputStream outputStream=urlConnection.getOutputStream();
                BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                writer.write(query);
                writer.flush();
                writer.close();
                if (bodyValues!=null){
                    JSONObject jsonObject=new JSONObject();
                    for (String key:bodyValues.keySet()){
                        jsonObject.put(key,bodyValues.getAsString(key));
                    }
                    String str=jsonObject.toString();
                    urlConnection.setRequestProperty("Content-type","application/json");
                    OutputStreamWriter outputStreamWriter=new OutputStreamWriter(urlConnection.getOutputStream());
                    outputStreamWriter.write(str);
                    outputStreamWriter.flush();
                    outputStreamWriter.close();
                }
                int statusCode=urlConnection.getResponseCode();
                if (statusCode==HttpURLConnection.HTTP_UNAUTHORIZED){
                    Log.d(LOG_TAG,"Unauthorized access");
                }else if (statusCode!=HttpURLConnection.HTTP_OK){
                    Log.d(LOG_TAG,"URL response error");
                }
                InputStream inputStream=new BufferedInputStream(urlConnection.getInputStream());
                return new JSONObject(convertInputStreamToString(inputStream));
            }
        }catch (MalformedURLException malformedURLException){
            Log.d(LOG_TAG,malformedURLException.getMessage());
        }catch (SocketTimeoutException socketTimeoutException){
            Log.d(LOG_TAG,socketTimeoutException.getMessage());
        }catch (JSONException jsonException){
            Log.d(LOG_TAG,jsonException.getMessage());
        }catch (IOException ioException){
            Log.d(LOG_TAG,ioException.getMessage());
        }finally {
            if (urlConnection!=null){
                urlConnection.disconnect();
            }
        }
        return null;
    }

    private static String convertInputStreamToString(InputStream inputStream){
        StringBuilder stringBuilder=new StringBuilder();
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
        String responseText;
        try {
            while ((responseText=bufferedReader.readLine())!=null){
                stringBuilder.append(responseText);
            }
        }catch (IOException ioException){
            Log.d(LOG_TAG,"Error in convertInputStreamToString");
            ioException.printStackTrace();
        }
        return stringBuilder.toString();
    }

    private static String addParametersToUrl(String url,ContentValues urlValues){
        StringBuffer stringBuffer=new StringBuffer(url);
        stringBuffer.append("?");
        for (String key:urlValues.keySet()){
            stringBuffer.append(key);
            stringBuffer.append("=");
            stringBuffer.append(urlValues.getAsString(key));
            stringBuffer.append("&");
        }
        stringBuffer.replace(stringBuffer.length()-1,stringBuffer.length()-1,"");
        return stringBuffer.toString();
    }

    private static void addBasicAuthorization(HttpURLConnection httpURLConnection){
        final String basicAuthorization="Basic "+ Base64.encodeToString((Constants.APP_KEY+":"+Constants.APP_SECRET).getBytes(),Base64.NO_WRAP);
        httpURLConnection.setRequestProperty(Constants.AUTHORIZATION,basicAuthorization);
    }

    public static boolean isInternetServiceAvailable(Context context){
        ConnectivityManager connectivityManager=((ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE));
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
        if (connectivityManager.getActiveNetworkInfo()==null){
            return false;
        }
        return networkInfo.isConnected()||networkInfo.isConnectedOrConnecting();
    }
}
