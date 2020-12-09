package com.example.ee1_2_test.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ee1_2_test.JSON.UserRegisterJSON;
import com.example.ee1_2_test.Model.User;
import com.example.ee1_2_test.R;

public class UserRegister extends AppCompatActivity {

    private EditText firstName, lastName, email, address, phoneNo, username, password;

    private Button submit;

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

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                user.setUserFName(firstName.getText().toString());

            }
        });
    }
}