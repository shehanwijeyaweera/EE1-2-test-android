package com.example.ee1_2_test.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.OnClickAction;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ee1_2_test.Fragments.AboutUsFragmentUser;
import com.example.ee1_2_test.Fragments.AddToCartFragmentUser;
import com.example.ee1_2_test.Fragments.AuthorFragmentAdmin;
import com.example.ee1_2_test.Fragments.BookFragmentAdmin;
import com.example.ee1_2_test.Fragments.BookRequestFragmentAdmin;
import com.example.ee1_2_test.Fragments.BooksFragmentUser;
import com.example.ee1_2_test.Fragments.CategoryFragmentAdmin;
import com.example.ee1_2_test.Fragments.DashboardFragmentAdmin;
import com.example.ee1_2_test.Fragments.DashboardFragmentStoreworker;
import com.example.ee1_2_test.Fragments.OrdersFragmentAdmin;
import com.example.ee1_2_test.Fragments.PastOrdersFragmentUser;
import com.example.ee1_2_test.Fragments.ProfileInfoFragmentUser;
import com.example.ee1_2_test.Fragments.RefundFragmentAdmin;
import com.example.ee1_2_test.Fragments.RefundResponseFragmentUser;
import com.example.ee1_2_test.Fragments.RequestBookFragmentUser;
import com.example.ee1_2_test.Fragments.UserFragmentAdmin;
import com.example.ee1_2_test.Model.Role;
import com.example.ee1_2_test.Model.User;
import com.example.ee1_2_test.R;
import com.example.ee1_2_test.Sessions.SessionManagement;
import com.google.android.material.navigation.NavigationView;

public class AdminDashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    androidx.appcompat.widget.Toolbar toolbar;
    private DrawerLayout drawer;
    NavigationView navigationView;

    TextView userfullname, userRole;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        toolbar = findViewById(R.id.toolbar_admin);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout_admin);
        navigationView = findViewById(R.id.nav_view_admin);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        setUserdetailsNav();

        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_contrainer_admin,new DashboardFragmentAdmin()).commit();
            navigationView.setCheckedItem(R.id.nav_admin_dashboard);
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_admin_dashboard:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_contrainer_admin,new DashboardFragmentAdmin()).commit();
                break;
            case R.id.nav_admin_book:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_contrainer_admin,new BookFragmentAdmin()).commit();
                break;
            case R.id.nav_admin_category:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_contrainer_admin,new CategoryFragmentAdmin()).commit();
                break;
            case R.id.nav_admin_author:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_contrainer_admin,new AuthorFragmentAdmin()).commit();
                break;
            case R.id.nav_admin_orders:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_contrainer_admin,new OrdersFragmentAdmin()).commit();
                break;
            case R.id.nav_admin_refunds:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_contrainer_admin,new RefundFragmentAdmin()).commit();
                break;
            case R.id.nav_admin_bookrequest:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_contrainer_admin,new BookRequestFragmentAdmin()).commit();
                break;
            case R.id.nav_admin_users:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_contrainer_admin,new UserFragmentAdmin()).commit();
                break;
            case R.id.nav_admin_logout:
                Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();

                SessionManagement sessionManagement = new SessionManagement(AdminDashboard.this);
                sessionManagement.removeSession();
                Intent intent = new Intent(AdminDashboard.this, MainActivity.class);
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
        navigationView = findViewById(R.id.nav_view_admin);
        View headerView = navigationView.getHeaderView(0);
        userfullname = headerView.findViewById(R.id.nav_admin_fullname);
        userRole = headerView.findViewById(R.id.nav_admin_userRole);

        SessionManagement sessionManagement = new SessionManagement(AdminDashboard.this);
        final User user = sessionManagement.getSession();
        final String role = sessionManagement.getRole();

        userfullname.setText(user.getUserFName()+" "+user.getUserLName());
        userRole.setText(role);
    }
}