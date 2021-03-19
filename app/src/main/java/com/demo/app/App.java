package com.demo.app;

import android.app.Application;

public class App extends Application {
    public static LocationPreference locationPreference;

    @Override
    public void onCreate() {
        super.onCreate();
        locationPreference = LocationPreference.getInstance(getApplicationContext());
    }



}
