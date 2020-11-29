package com.example.ee1_2_test.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ee1_2_test.Fragments.AboutUsFragmentUser;
import com.example.ee1_2_test.Fragments.AddToCartFragmentUser;
import com.example.ee1_2_test.Fragments.BookRequestsFragmentStoreworker;
import com.example.ee1_2_test.Fragments.BooksFragmentUser;
import com.example.ee1_2_test.Fragments.DashboardFragmentStoreworker;
import com.example.ee1_2_test.Fragments.OrdersFragmentStoreworker;
import com.example.ee1_2_test.Fragments.PastOrdersFragmentUser;
import com.example.ee1_2_test.Fragments.ProfileInfoFragmentUser;
import com.example.ee1_2_test.Fragments.RefundResponseFragmentUser;
import com.example.ee1_2_test.Fragments.RequestBookFragmentUser;
import com.example.ee1_2_test.Model.User;
import com.example.ee1_2_test.R;
import com.example.ee1_2_test.Sessions.SessionManagement;
import com.google.android.material.navigation.NavigationView;

public class StoreworkerDashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    androidx.appcompat.widget.Toolbar toolbar;
    private DrawerLayout drawer;
    NavigationView navigationView;

    TextView userfullname, userRole;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storeworker_dashboard);

        toolbar = findViewById(R.id.toolbar_storeworker);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout_storeworker);
        navigationView = findViewById(R.id.nav_view_storeworker);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        setUserdetailsNav();

        //save the state when application rotates
        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_contrainer_storeworker,new DashboardFragmentStoreworker()).commit();
            navigationView.setCheckedItem(R.id.nav_storeworker_dashboard);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_storeworker_dashboard:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_contrainer_storeworker,new DashboardFragmentStoreworker()).commit();
                break;
            case R.id.nav_storeworker_orders:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_contrainer_storeworker,new OrdersFragmentStoreworker()).commit();
                break;
            case R.id.nav_storeworker_bookrequest:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_contrainer_storeworker,new BookRequestsFragmentStoreworker()).commit();
                break;
            case R.id.nav_logout:
                Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();

                SessionManagement sessionManagement = new SessionManagement(StoreworkerDashboard.this);
                sessionManagement.removeSession();
                Intent intent = new Intent(StoreworkerDashboard.this, MainActivity.class);
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

    private void setUserdetailsNav() {
        navigationView = findViewById(R.id.nav_view_storeworker);
        View headerView = navigationView.getHeaderView(0);
        userfullname = headerView.findViewById(R.id.nav_admin_fullname);
        userRole = headerView.findViewById(R.id.nav_admin_userRole);

        SessionManagement sessionManagement = new SessionManagement(StoreworkerDashboard.this);
        final User user = sessionManagement.getSession();
        final String role = sessionManagement.getRole();

        userfullname.setText(user.getUserFName()+" "+user.getUserLName());
        userRole.setText(role);
    }
}