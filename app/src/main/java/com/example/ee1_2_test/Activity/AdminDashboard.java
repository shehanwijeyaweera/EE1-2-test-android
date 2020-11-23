package com.example.ee1_2_test.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.OnClickAction;
import android.view.View;
import android.widget.Button;

import com.example.ee1_2_test.R;
import com.example.ee1_2_test.Sessions.SessionManagement;

public class AdminDashboard extends AppCompatActivity {

    private Button Logoutbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        Logoutbtn = findViewById(R.id.logoutbutton);


    }

    public void Logout1(View view) {
        SessionManagement sessionManagement = new SessionManagement(AdminDashboard.this);
        sessionManagement.removeSession();

        moveToLogin();
    }

    private void moveToLogin() {
        Intent intent = new Intent(AdminDashboard.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}