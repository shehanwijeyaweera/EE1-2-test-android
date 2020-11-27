package com.example.ee1_2_test.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.ee1_2_test.Fragments.AboutUsFragmentUser;
import com.example.ee1_2_test.Fragments.BooksFragmentUser;
import com.example.ee1_2_test.Fragments.PastOrdersFragmentUser;
import com.example.ee1_2_test.Fragments.ProfileInfoFragmentUser;
import com.example.ee1_2_test.Fragments.RefundResponseFragmentUser;
import com.example.ee1_2_test.Fragments.RequestBookFragmentUser;
import com.example.ee1_2_test.Model.User;
import com.example.ee1_2_test.R;
import com.example.ee1_2_test.Sessions.SessionManagement;
import com.google.android.material.navigation.NavigationView;

public class Userhomepage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;

    androidx.appcompat.widget.Toolbar toolbar;
    NavigationView navigationView;
    TextView userfullname, useremail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userhomepage);

        userfullname = findViewById(R.id.nav_user_fullname);
        useremail = findViewById(R.id.nav_user_email);


        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        setUserdetailsNav();

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //save the state when application rotates
        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_contrainer, new BooksFragmentUser()).commit();
            navigationView.setCheckedItem(R.id.nav_view_all_books);
        }
    }

    private void setUserdetailsNav() {
       navigationView = findViewById(R.id.nav_view);
       View headerView = navigationView.getHeaderView(0);
        userfullname = headerView.findViewById(R.id.nav_user_fullname);
        useremail = headerView.findViewById(R.id.nav_user_email);

        SessionManagement sessionManagement = new SessionManagement(Userhomepage.this);
        final User user = sessionManagement.getSession();

        userfullname.setText(user.getUserFName()+" "+user.getUserLName());
        useremail.setText(user.getUserEmail());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_view_all_books:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_contrainer,new BooksFragmentUser()).commit();
                break;
            case R.id.nav_about_us:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_contrainer,new AboutUsFragmentUser()).commit();
                break;
            case R.id.nav_past_orders:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_contrainer,new PastOrdersFragmentUser()).commit();
                break;
            case R.id.nav_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_contrainer,new ProfileInfoFragmentUser()).commit();
                break;
            case R.id.nav_refund_request:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_contrainer,new RefundResponseFragmentUser()).commit();
                break;
            case R.id.nav_request_book:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_contrainer,new RequestBookFragmentUser()).commit();
                break;
            case R.id.nav_share:
                Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_logout:
                Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();

                SessionManagement sessionManagement = new SessionManagement(Userhomepage.this);
                sessionManagement.removeSession();
                Intent intent = new Intent(Userhomepage.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                //moveToLogin();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
        super.onBackPressed();
    }

    private void moveToLogin() {

    }
}