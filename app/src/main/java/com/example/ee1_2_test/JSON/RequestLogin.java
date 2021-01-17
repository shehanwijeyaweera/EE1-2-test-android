package com.example.ee1_2_test.JSON;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RequestLogin implements Serializable {
    @SerializedName("username")
    @Expose
    String Username;
    @SerializedName("password")
    @Expose
    String Password;

    public RequestLogin(String username, String password) {
        Username = username;
        Password = password;
    }

    public RequestLogin() {
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
