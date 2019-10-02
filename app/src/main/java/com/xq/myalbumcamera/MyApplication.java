package com.xq.myalbumcamera;

import android.app.Application;

public class MyApplication extends Application {

    private static MyApplication mMyApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mMyApplication = this;
    }

    public static MyApplication getInstance() {
        return mMyApplication;
    }
}
