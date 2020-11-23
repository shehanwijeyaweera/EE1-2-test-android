package com.example.ee1_2_test.Sessions;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.ee1_2_test.Model.Role;
import com.example.ee1_2_test.Model.User;
import com.google.gson.Gson;

import java.util.List;

public class SessionManagement {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String SHARED_PREF_NAME = "session";
    String SESSION_KEY = "session_user";
    String SESSION_ROLE = "session_role";

    public SessionManagement(Context context){
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

    }

    public void saveSession(User user, List<Role> role){
        editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json_user = gson.toJson(user);
        String json_role = role.get(0).getName();
        editor.putString(SESSION_KEY, json_user);
        editor.putString(SESSION_ROLE, json_role);
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
        editor.putString(SESSION_ROLE, null).commit();
    }

    public String getRole(){
        String role = sharedPreferences.getString(SESSION_ROLE, "");

        return role;
    }
}
