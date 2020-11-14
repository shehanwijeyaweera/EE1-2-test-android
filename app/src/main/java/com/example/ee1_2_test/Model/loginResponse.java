package com.example.ee1_2_test.Model;

import com.google.gson.annotations.SerializedName;

public class loginResponse {

    @SerializedName("Response")
    private String Response;

    @SerializedName("username")
    private String Username;

    public String getResponse() {
        return Response;
    }

    public void setResponse(String response) {
        Response = response;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }
}
