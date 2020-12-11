package com.example.ee1_2_test.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ee1_2_test.API.BookstoreApi;
import com.example.ee1_2_test.JSON.UserRegisterJSON;
import com.example.ee1_2_test.Model.ApiClient;
import com.example.ee1_2_test.Model.RegisterResponse;
import com.example.ee1_2_test.Model.User;
import com.example.ee1_2_test.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRegister extends AppCompatActivity {

    private EditText firstName, lastName, email, address, phoneNo, username, password;

    private Button submit;

    private TextView error_blankFields, error_usernameTaken;

    private BookstoreApi bookstoreApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);

        firstName = findViewById(R.id.firstName_register_user);
        lastName = findViewById(R.id.lastName_register_user);
        email = findViewById(R.id.email_register_user);
        address = findViewById(R.id.phoneNo_register_user);
        phoneNo = findViewById(R.id.phoneNo_register_user);
        username = findViewById(R.id.username_register_user);
        password = findViewById(R.id.password_register_user);
        submit = findViewById(R.id.registerbtn_register_user);

        error_blankFields = findViewById(R.id.error_fillallfeilds_register_user);
        error_usernameTaken = findViewById(R.id.error_usernametaken_register_user);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                error_blankFields.setVisibility(View.INVISIBLE);
                error_usernameTaken.setVisibility(View.INVISIBLE);
                User user = new User();
                user = getInputValues();
                boolean validate = validateInputFields(user);
                if(validate == true){
                    saveUserDetails(user);
                }
                else {
                    //do nothing yet!
                    error_blankFields.setVisibility(View.VISIBLE);
                }

            }
        });
    }

    private void saveUserDetails(User user) {
        bookstoreApi = ApiClient.getClient().create(BookstoreApi.class);

        Call<RegisterResponse> call = bookstoreApi.getRegisterResponse(user);

        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                RegisterResponse registerResponse = response.body();
                if(registerResponse.getResponse().matches("Username Already taken")){
                    //error message appear
                    error_usernameTaken.setVisibility(View.VISIBLE);
                } else if(registerResponse.getResponse().matches("Correct")){
                    moveToLoginpage();
                }

            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {

            }
        });
    }

    private User getInputValues() {
        User user = new User();
        user.setUserFName(firstName.getText().toString());
        user.setUserLName(lastName.getText().toString());
        user.setUserEmail(email.getText().toString());
        user.setUserAddress(address.getText().toString());
        try {
            user.setUserPhoneNo(Integer.parseInt(phoneNo.getText().toString()));
        } catch (NumberFormatException e) {
            //do nothing
        }
        user.setUsername(username.getText().toString());
        user.setPassword(password.getText().toString());

        return user;
    }

    private boolean validateInputFields(User user) {
        if(user.getUserLName().matches("") || user.getUserLName().matches("") || user.getUserEmail().matches("") || user.getUserAddress().matches("")||
        user.getUserPhoneNo().equals(null)||user.getUsername().matches("")||user.getPassword().matches("")){

            return false;
        }else {
            return true;
        }
    }

    private void getRegisterResponse(User user) {
        bookstoreApi = ApiClient.getClient().create(BookstoreApi.class);

        Call<RegisterResponse> call = bookstoreApi.getRegisterResponse(user);

        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                RegisterResponse registerResponse = response.body();

                if(registerResponse.getResponse().equalsIgnoreCase("Username Already taken")){
                    Toast.makeText(UserRegister.this, "Username Already taken", Toast.LENGTH_SHORT).show();
                }
                else if (registerResponse.getResponse().equalsIgnoreCase("Correct")){
                    Toast.makeText(UserRegister.this, "Verify Email", Toast.LENGTH_SHORT).show();
                    Intent myIntent = new Intent(UserRegister.this, MainActivity.class);
                    UserRegister.this.startActivity(myIntent);
                }
                else {
                    Toast.makeText(UserRegister.this, "Error register", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Toast.makeText(UserRegister.this, "Register function Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void moveToLoginpage() {
        Intent myIntent = new Intent(UserRegister.this, MainActivity.class);
        UserRegister.this.startActivity(myIntent);
    }
}