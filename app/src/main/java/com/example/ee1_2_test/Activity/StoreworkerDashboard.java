package com.example.ee1_2_test.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ee1_2_test.R;
import com.example.ee1_2_test.Sessions.SessionManagement;

public class StoreworkerDashboard extends AppCompatActivity {

    private Button logoutbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storeworker_dashboard);

        logoutbtn = findViewById(R.id.buttonLogout);


    }

    public void Logout(View view) {
        SessionManagement sessionManagement = new SessionManagement(StoreworkerDashboard.this);
        sessionManagement.removeSession();

        moveToLogin();
    }

    private void moveToLogin() {
        Intent intent = new Intent(StoreworkerDashboard.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}