package com.example.ee1_2_test.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.ee1_2_test.API.BookstoreApi;
import com.example.ee1_2_test.Model.ApiClient;
import com.example.ee1_2_test.Model.Book;
import com.example.ee1_2_test.Model.loginResponse;
import com.example.ee1_2_test.R;
import com.example.ee1_2_test.Sessions.SessionManagement;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminEditBook extends AppCompatActivity {

    private EditText etTitle, etPubdate, etDiscription, etPublisher, etPrice;
    private Button submitbtn;
    private ImageView bookImage;
    private ProgressBar loadingdata;
    private BookstoreApi bookstoreApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit_book);

        etTitle = findViewById(R.id.editbook_title_admin);
        etPubdate = findViewById(R.id.editbook_pubdate_admin);
        etDiscription = findViewById(R.id.editbook_description_admin);
        etPublisher = findViewById(R.id.editbook_publisher_admin);
        etPrice = findViewById(R.id.editbook_price_admin);
        bookImage = findViewById(R.id.editbook_image_admin);
        loadingdata = findViewById(R.id.editbook_progressbar_admin);
        submitbtn = findViewById(R.id.editbook_submitbtn_admin);

        Intent intent = getIntent();

        int id = intent.getExtras().getInt("ID");
        String Title = intent.getExtras().getString("Title");
        String Description = intent.getExtras().getString("Description");
        String imagepath = intent.getExtras().getString("Thumbnail");
        String category = intent.getExtras().getString("category");
        String pubdate = intent.getExtras().getString("pubdate");
        String publisher = intent.getExtras().getString("publisher");
        double price = intent.getExtras().getDouble("price");
        String author = intent.getExtras().getString("author");

        String stringdouble = Double.toString(price);

        etTitle.setText(Title);
        etPubdate.setText(pubdate);
        etDiscription.setText(Description);
        etPublisher.setText(publisher);
        etPrice.setText(stringdouble);
        Picasso.get().load(imagepath).into(bookImage);

        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingdata.setVisibility(View.VISIBLE);
                submitData(id);
                loadingdata.setVisibility(View.INVISIBLE);
            }
        });

    }

    private void submitData(int id) {
        Book newBook = new Book();
        newBook.setTitle(etTitle.getText().toString());
        newBook.setPubdate(etPubdate.getText().toString());
        newBook.setDescription(etDiscription.getText().toString());
        newBook.setPublisher(etPublisher.getText().toString());
        newBook.setPrice(Double.valueOf(etPrice.getText().toString()));

        bookstoreApi = ApiClient.getClient().create(BookstoreApi.class);

        Call<loginResponse> call = bookstoreApi.updateBookDetails(newBook, id);

        call.enqueue(new Callback<loginResponse>() {
            @Override
            public void onResponse(Call<loginResponse> call, Response<loginResponse> response) {
                loginResponse loginRes = response.body();

                if(loginRes.getResponse().matches("Correct")){
                    SessionManagement sessionManagement = new SessionManagement(AdminEditBook.this);
                    Intent intent = new Intent(AdminEditBook.this, AdminDashboard.class);
                    startActivity(intent);
                }
                else if(loginRes.getResponse().matches("Failed")){
                    //error message
                }
            }

            @Override
            public void onFailure(Call<loginResponse> call, Throwable t) {
                Toast.makeText(AdminEditBook.this, "Failed to receive data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}