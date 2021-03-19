package com.demo.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class LocationPreference {
    private static final String SETTINGS_NAME = "location_settings";
    private static final String LIST_NAME = "location_list";
    private static LocationPreference locationPreference = new LocationPreference();
    private static SharedPreferences mPref;
    private static SharedPreferences.Editor editor;


    private LocationPreference() {}

    public static LocationPreference getInstance(Context context){
        if (mPref == null) {
            mPref = context.getSharedPreferences(SETTINGS_NAME, context.MODE_PRIVATE );
            editor = mPref.edit();
        }
        return locationPreference;
    }

    public static void clearAll(){
        editor.clear();
        editor.apply();
    }

    public static void saveListLocation(Location location){
        List<Location> locationList = getListLocation();
        if(locationList==null) locationList = new ArrayList<>();
        locationList.add(location);
        if (locationList.size()>50) locationList.remove(0);
        String locationJSONList = new Gson().toJson(locationList);
        editor.putString(LIST_NAME, locationJSONList);
        editor.apply();
    }

    public static List<Location> getListLocation(){
        String locationJSONList = mPref.getString(LIST_NAME, null);
        List<Location> locationList =
                new Gson().fromJson(locationJSONList, new TypeToken<List<Location>>(){}.getType());
        return locationList;
    }


}