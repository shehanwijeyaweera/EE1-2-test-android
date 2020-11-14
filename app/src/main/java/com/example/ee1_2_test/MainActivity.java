package com.example.ee1_2_test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.8.120:8080/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username1 = username.getText().toString();
                String password1 = password.getText().toString();

                if(username1.equals("Admin") && password1.equals("Admin")){
                    Intent myIntent = new Intent(MainActivity.this, DisplayBooks.class);
                    MainActivity.this.startActivity(myIntent);
                }
                else {
                    Toast.makeText(getApplicationContext(),"Invalid Username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}