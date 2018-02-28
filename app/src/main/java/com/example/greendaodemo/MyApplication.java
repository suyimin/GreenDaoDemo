package com.example.greendaodemo;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        //初始化greendao
        GreenDaoManager.getInstance();
    }

    public static Context getContext() {
        return context;
    }
}