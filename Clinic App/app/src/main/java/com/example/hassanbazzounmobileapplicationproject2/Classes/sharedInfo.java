package com.example.hassanbazzounmobileapplicationproject2.Classes;

import android.content.Context;
import android.content.SharedPreferences;

public class sharedInfo {
    private Context context;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    public sharedInfo(Context c) {
        context = c;
        sharedPreferences = context.getSharedPreferences("login.conf", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }



    public void setUser(String name) {

        editor.putString("Name", name);
        editor.apply();
        editor.commit();

    }

    public String getUser() {
        return sharedPreferences.getString("Name", "");
    }

    public String getUserType() {
        return sharedPreferences.getString("Type", "");
    }

    public void setUserType(String name) {

        editor.putString("Type", name);
        editor.apply();
        editor.commit();

    }
    public String getPatientNameIntent() {
        return sharedPreferences.getString("Patient_name", "");
    }

    public void setPatientNameIntent(String name) {

        editor.putString("Patient_name", name);
        editor.apply();
        editor.commit();

    }

    public String getDateIntent() {
        return sharedPreferences.getString("date", "");
    }

    public void setDateIntent(String name) {

        editor.putString("date", name);
        editor.apply();
        editor.commit();

    }

    public String getSymptomIntent() {
        return sharedPreferences.getString("SymptomIntent", "");
    }

    public void setSymptomIntent(String name) {

        editor.putString("SymptomIntent", name);
        editor.apply();
        editor.commit();

    }





    public void Clear() {

//        sharedPreferences.edit().clear().apply();
//        editor.commit();
//editor.apply();

        editor.clear();
        editor.commit();

    }





}
