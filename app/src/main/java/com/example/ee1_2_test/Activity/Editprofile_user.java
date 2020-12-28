package com.example.ee1_2_test.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ee1_2_test.API.BookstoreApi;
import com.example.ee1_2_test.Dialog.DialogRequestBook;
import com.example.ee1_2_test.Model.ApiClient;
import com.example.ee1_2_test.Model.User;
import com.example.ee1_2_test.Model.loginResponse;
import com.example.ee1_2_test.R;
import com.example.ee1_2_test.Sessions.SessionManagement;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Editprofile_user extends AppCompatActivity {

    private TextView Username;
    private EditText firstName, lastName, email, phoneNo, address;
    private Button submit_btn;
    private ProgressBar loadingData;
    private BookstoreApi bookstoreApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofile_user);

        Username = findViewById(R.id.editprofile_username_user);
        firstName = findViewById(R.id.editprofile_fName_user);
        lastName = findViewById(R.id.editprofile_LName_user);
        email = findViewById(R.id.editprofile_email_user);
        phoneNo = findViewById(R.id.editprofile_phoneNo_user);
        address = findViewById(R.id.editprofile_address_user);
        submit_btn = findViewById(R.id.editprofile_submitbtn_user);
        loadingData = findViewById(R.id.editprofile_progressbar_user);

        loadingData.setVisibility(View.VISIBLE);
        getData();
        loadingData.setVisibility(View.INVISIBLE);

        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SessionManagement sessionManagement = new SessionManagement(Editprofile_user.this);
                User user = sessionManagement.getSession();

                sendData(user.getId());
            }
        });
    }

    private void sendData(Integer id) {
        User updatedUser = new User();

        updatedUser.setUserFName(firstName.getText().toString());
        updatedUser.setUserLName(lastName.getText().toString());
        updatedUser.setUserEmail(email.getText().toString());
        updatedUser.setUserPhoneNo(Integer.valueOf(phoneNo.getText().toString()));
        updatedUser.setUserAddress(address.getText().toString());

        bookstoreApi = ApiClient.getClient().create(BookstoreApi.class);

        Call<loginResponse> call = bookstoreApi.updateprofiledata(id, updatedUser);

        call.enqueue(new Callback<loginResponse>() {
            @Override
            public void onResponse(Call<loginResponse> call, Response<loginResponse> response) {
                loginResponse loginRes = response.body();

                if(loginRes.getResponse().matches("Correct")){
                    openDialog();
                    SessionManagement sessionManagement = new SessionManagement(Editprofile_user.this);
                    sessionManagement.removeSession();
                    Intent intent = new Intent(Editprofile_user.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
                else if(loginRes.getResponse().matches("Failed")){
                    //error message
                }
            }

            @Override
            public void onFailure(Call<loginResponse> call, Throwable t) {
                Toast.makeText(Editprofile_user.this, "Failed to receive data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void openDialog() {
        DialogRequestBook dialogRequestBook = new DialogRequestBook();
        dialogRequestBook.show(getSupportFragmentManager(),"Success");
    }

    private void getData() {
        SessionManagement sessionManagement = new SessionManagement(Editprofile_user.this);
        User user = sessionManagement.getSession();
        bookstoreApi = ApiClient.getClient().create(BookstoreApi.class);

        Call<User> call = bookstoreApi.getprofileInfo(user.getId());

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User newUser = response.body();

                firstName.setText(newUser.getUserFName());
                lastName.setText(newUser.getUserLName());
                email.setText(newUser.getUserEmail());
                phoneNo.setText(newUser.getUserPhoneNo().toString());
                address.setText(newUser.getUserAddress());
                Username.setText(newUser.getUsername());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(Editprofile_user.this, "Failed to receive data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}