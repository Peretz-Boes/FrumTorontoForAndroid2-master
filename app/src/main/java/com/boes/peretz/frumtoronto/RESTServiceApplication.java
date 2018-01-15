package com.boes.peretz.frumtoronto;

import android.app.Application;

/**
 * Created by alanrabinowitz on 2018-01-02.
 */

public class RESTServiceApplication extends Application {
    private static RESTServiceApplication instance;
    private User user;
    private String accessToken;

    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
        user=new User();
    }

    public static RESTServiceApplication getInstance(){
        return instance;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
