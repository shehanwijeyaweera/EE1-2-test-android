package com.example.ee1_2_test.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ee1_2_test.API.BookstoreApi;
import com.example.ee1_2_test.Model.ApiClient;
import com.example.ee1_2_test.Model.Role;
import com.example.ee1_2_test.Model.User;
import com.example.ee1_2_test.Model.loginResponse;
import com.example.ee1_2_test.Model.loginResponse2;
import com.example.ee1_2_test.R;
import com.example.ee1_2_test.Sessions.SessionManagement;
import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
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

    private TextView register;

    private BookstoreApi bookstoreApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        username = findViewById(R.id.usernameet);
        password = findViewById(R.id.passwordet);
        loginbtn = findViewById(R.id.cardView);
        register = findViewById(R.id.textView2);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, UserRegister.class);
                MainActivity.this.startActivity(myIntent);
            }
        });

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username1 = username.getText().toString();
                String password1 = password.getText().toString();

                if (!username1.isEmpty() && !password1.isEmpty()) {
                    loginfunction(username1, password1);
                } else {
                    Toast.makeText(getApplicationContext(), "Enter Username and password", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void loginfunction(String username, String password) {


        bookstoreApi = ApiClient.getClient().create(BookstoreApi.class);

        Call<loginResponse2> call = bookstoreApi.getloginRespones(username, password);

        call.enqueue(new Callback<loginResponse2>() {
            @Override
            public void onResponse(Call<loginResponse2> call, Response<loginResponse2> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Error Response", Toast.LENGTH_SHORT).show();
                    return;
                }

                loginResponse2 loginRes = response.body();


                if (loginRes.getResponse().equalsIgnoreCase("Correct")) {
                    Toast.makeText(getApplicationContext(), "Login Success : " + loginRes.getUser().getUsername(), Toast.LENGTH_SHORT).show();
                    SessionManagement sessionManagement = new SessionManagement(MainActivity.this);
                    sessionManagement.saveSession(loginRes.getUser(), loginRes.getRole());
                    String role = sessionManagement.getRole();
                    if (role.equalsIgnoreCase("Admin")) {
                        moveToAdminDashboard();
                    } else if (role.equalsIgnoreCase("User")) {
                        if(loginRes.getUser().getEnabled().equals(Boolean.TRUE)) {
                            moveToMainActivity();
                        }else {
                            moveToVerifyPage();
                        }
                    } else if (role.equalsIgnoreCase("Storeworker")) {
                        moveToStoreworkerDashboard();
                    }
                } else if (loginRes.getResponse().equalsIgnoreCase("Wrong")) {
                    Toast.makeText(getApplicationContext(), "Login Error : " + loginRes.getUser().getUsername(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Login Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<loginResponse2> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Respones Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void moveToVerifyPage() {
        Intent myIntent = new Intent(MainActivity.this, verifyEmailUser.class);
        MainActivity.this.startActivity(myIntent);
    }

    private void moveToMainActivity() {
        Intent myIntent = new Intent(MainActivity.this, Userhomepage.class);
        MainActivity.this.startActivity(myIntent);
    }

    @Override
    protected void onStart() {
        //check if user has logged in
        super.onStart();

        checkSession();
    }

    private void checkSession() {
        SessionManagement sessionManagement = new SessionManagement(MainActivity.this);
        final User user = sessionManagement.getSession();
        final String role = sessionManagement.getRole();

        if (user != null) {

            if (role.equalsIgnoreCase("Admin")) {
                moveToAdminDashboard();
            } else if (role.equalsIgnoreCase("User")) {
                if(user.getEnabled().equals(Boolean.TRUE)) {
                    Intent myIntent = new Intent(MainActivity.this, Userhomepage.class);
                    MainActivity.this.startActivity(myIntent);
                    moveToMainActivity();
                }else {
                    moveToVerifyPage();
                }
            } else if (role.equalsIgnoreCase("Storeworker")) {
                moveToStoreworkerDashboard();
            }
        } else {
            //do nothing
        }
    }

    private void moveToAdminDashboard() {
        Intent myIntent = new Intent(MainActivity.this, AdminDashboard.class);
        MainActivity.this.startActivity(myIntent);
    }

    private void moveToStoreworkerDashboard() {
        Intent myIntent = new Intent(MainActivity.this, StoreworkerDashboard.class);
        MainActivity.this.startActivity(myIntent);
    }
}