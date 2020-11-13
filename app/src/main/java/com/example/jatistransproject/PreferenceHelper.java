package com.example.jatistransproject;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceHelper {

    private final String INTRO = "intro";
    private final String EMAIL = "email";
    private final String PASS  = "password";

    // Preference Register
    private final String NOTLP = "phone_no";
    private final String REGIS = "regis";
    private final String UPDATE = "update";
    private final String USERNAME = "user_name";

    private SharedPreferences app_prefs;
    public Context context;

    public PreferenceHelper(Context context) {
        app_prefs = context.getSharedPreferences("shared",
                Context.MODE_PRIVATE);
        this.context = context;
    }


    public void putIsLogin(boolean loginorout) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putBoolean(INTRO, loginorout);
        edit.commit();
    }

    public boolean getIsLogin() {
        return app_prefs.getBoolean(INTRO, false);
    }

    public boolean getIsUpdate() {
        return app_prefs.getBoolean(UPDATE, false);
    }

    public void putIsUpdate(boolean loginorout) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putBoolean(UPDATE, loginorout);
        edit.apply();
    }

    public void putUsername(String loginorout) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(USERNAME, loginorout);
        edit.commit();
    }

    public String getUsername() {
        return app_prefs.getString(USERNAME, "");

    }

    public void putNumber (String loginorout) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(NOTLP, loginorout);
        edit.commit();
    }

    public String getNumber (){
        return app_prefs.getString(NOTLP, "");
    }


    public void putEmail(String loginorout) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(EMAIL, loginorout);
        edit.commit();
    }

    public String getEmail() {
        return app_prefs.getString(EMAIL, "" );
    }

    public void putPass(String loginorout) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(PASS, loginorout);
        edit.commit();
    }

    public String getPass() {
        return app_prefs.getString(PASS, "");
    }

    //    Regis
    public void putIsRegis(boolean loginorout) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putBoolean(REGIS, loginorout);
        edit.commit();
    }

    public boolean getIsRegis() {
        return app_prefs.getBoolean(REGIS, false);
    }


}
