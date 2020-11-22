package com.example.ee1_2_test.Sessions;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.ee1_2_test.Model.User;
import com.google.gson.Gson;

public class SessionManagement {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String SHARED_PREF_NAME = "session";
    String SESSION_KEY = "session_user";

    public SessionManagement(Context context){
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

    }

    public void saveSession(User user){
        editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(user);
        editor.putString(SESSION_KEY, json);
        editor.commit();
    }

    public User getSession(){

        Gson gson = new Gson();
        String json = sharedPreferences.getString(SESSION_KEY, "");
        User user = gson.fromJson(json, User.class);

        return user;
    }

    public void removeSession(){

        editor.putString(SESSION_KEY, null).commit();
    }
}
