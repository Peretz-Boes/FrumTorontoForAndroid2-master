package com.boes.peretz.frumtoronto;

/**
 * Created by alanrabinowitz on 2017-12-20.
 */

public class Constants {
    public static final int CONNECTION_TIMEOUT=10000;
    public static final int READ_TIMEOUT=100000;
    public static final int STATUS_ERROR=400;
    public static final int STATUS_UNAUTHORIZED=401;
    public static final String APP_KEY="950d9bf3677f46368febe9e45ea7f845";
    public static final String APP_SECRET="eb7b16f26175497fb8e40ebbf7942b2e";
    public static final String END_POINT ="http://mobileapps.x10host.com/home/mobilea9/public_html";
    public static final String LOGIN_URL=END_POINT+"/login.php";
    public static final String REGISTRATION_URL= END_POINT +"/signup.php";
    public static final String INFO_URL=END_POINT+"/info.php";
    public static final String UPDATE_URL=END_POINT+"/update.php";
    public static final String DELETE_URL=END_POINT+"/delete.php";
    public static final String RESET_URL=END_POINT+"/reset.php";
    public static final String AUTHORIZATION="Authorization";
    public static final String USERNAME="username";
    public static final String PASSWORD="password";
    public static final String GRANT_TYPE="grant_type";
    public static final String CLIENT_CREDENTIALS="client_credentials";
    public static final String ACCESS_TOKEN="access_token";
    public static final String ACCESS="access";
    public static final String INFO="info";
    public static final String STATUS="status";
    public static final String MESSAGE="message";
    public static final String ID="id";
    public static final String ID_INFO="ID";
    public static final String PHONE_NUMBER="phoneNumber";
    public static final String NOTE="note";
    public static final String NAME="name";
}