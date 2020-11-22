package com.example.ee1_2_test.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ee1_2_test.R;
import com.example.ee1_2_test.Sessions.SessionManagement;
import com.squareup.picasso.Picasso;

public class Single_book_view extends AppCompatActivity {

    ImageView mainImageview;
    TextView title, price, isbn, desc;
    Button logout_btn;

    String title1, desc1, imagelocation1;
    double price1;
    int isbn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_book_view);

        mainImageview = findViewById(R.id.mainImageview);
        title = findViewById(R.id.tvTitle);
        price = findViewById(R.id.tvPrice);
        isbn = findViewById(R.id.tvIsbn);
        desc = findViewById(R.id.tvDesc);
        logout_btn = findViewById(R.id.logoutbtn);



        getData();
        setData();
    }

    private void getData() {
        if (getIntent().hasExtra("title") && getIntent().hasExtra("price") &&
                getIntent().hasExtra("isbn") && getIntent().hasExtra("desc") &&
                getIntent().hasExtra("imageLocation")) {
            title1 = getIntent().getStringExtra("title");
            price1 = getIntent().getDoubleExtra("price", 0.00);
            isbn1 = getIntent().getIntExtra("isbn", 0);
            desc1 = getIntent().getStringExtra("desc");
            imagelocation1 = getIntent().getStringExtra("imageLocation");
        } else {
            Toast.makeText(this, "Data Not found", Toast.LENGTH_SHORT).show();
        }
    }

    private void setData() {
        String stringdouble = Double.toString(price1);
            title.setText(title1);
            price.setText(stringdouble);
            isbn.setText(String.valueOf(isbn1));
            desc.setText(desc1);
            Picasso.get().load(imagelocation1).into(mainImageview);
    }

    public void Logout(View view) {
        SessionManagement sessionManagement = new SessionManagement(Single_book_view.this);
        sessionManagement.removeSession();
        
        moveToLogin();
    }

    private void moveToLogin() {
        Intent intent = new Intent(Single_book_view.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}