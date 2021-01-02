package com.example.ee1_2_test.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ee1_2_test.R;

public class verifyEmailUser extends AppCompatActivity {

    private Button verifyBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_email_user);

        verifyBtn = findViewById(R.id.verifyemail_opengmailbtn_user);

        verifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = getPackageManager().getLaunchIntentForPackage("com.google.android.gm");
                startActivity(i);
            }
        });
    }
}