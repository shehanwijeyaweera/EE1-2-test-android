package com.example.ee1_2_test.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ee1_2_test.API.BookstoreApi;
import com.example.ee1_2_test.Model.ApiClient;
import com.example.ee1_2_test.Model.loginResponse;
import com.example.ee1_2_test.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private EditText username;

    private EditText password;

    private CardView loginbtn;

    private BookstoreApi bookstoreApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        username = findViewById(R.id.usernameet);
        password = findViewById(R.id.passwordet);
        loginbtn = findViewById(R.id.cardView);


        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username1 = username.getText().toString();
                String password1 = password.getText().toString();

                if(!username1.isEmpty() && !password1.isEmpty()) {
                    loginfunction(username1, password1);
                }
                else {
                    Toast.makeText(getApplicationContext(),"Enter Username and password", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void loginfunction(String username, String password) {

        
        bookstoreApi = ApiClient.getClient().create(BookstoreApi.class);

        Call<loginResponse> call = bookstoreApi.getloginRespones(username, password);

        call.enqueue(new Callback<loginResponse>() {
            @Override
            public void onResponse(Call<loginResponse> call, Response<loginResponse> response) {

                if(!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Error Response", Toast.LENGTH_SHORT).show();
                    return;
                }

                loginResponse loginRes = response.body();

                if(loginRes.getResponse().equalsIgnoreCase("Correct")){
                    Toast.makeText(getApplicationContext(),"Login Success : "+loginRes.getUsername(), Toast.LENGTH_SHORT).show();
                    Intent myIntent = new Intent(MainActivity.this, DisplayBooks.class);
                    MainActivity.this.startActivity(myIntent);
                }
                else if(loginRes.getResponse().equalsIgnoreCase("Wrong")){
                    Toast.makeText(getApplicationContext(),"Login Error : "+loginRes.getUsername(), Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Login Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<loginResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Respones Error", Toast.LENGTH_SHORT).show();
            }
        });
    }


}