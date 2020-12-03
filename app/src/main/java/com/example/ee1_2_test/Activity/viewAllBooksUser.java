package com.example.ee1_2_test.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.ee1_2_test.API.BookstoreApi;
import com.example.ee1_2_test.Adapters.userViewAllBooksAdapter;
import com.example.ee1_2_test.Model.ApiClient;
import com.example.ee1_2_test.Model.Book;
import com.example.ee1_2_test.Model.Customer_orders;
import com.example.ee1_2_test.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class viewAllBooksUser extends AppCompatActivity {

    private BookstoreApi bookstoreApi;
    ArrayList<Book> books = new ArrayList<>();
    ArrayList<Customer_orders> customer_orders = new ArrayList<>();
    private userViewAllBooksAdapter ViewAllBooksAdapter;
    private RecyclerView books_recyclerview;
    private ProgressBar loadingbookdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_books_user);

        books_recyclerview = findViewById(R.id.books_recyclerview_user_view_all_books);
        books_recyclerview.setLayoutManager(new GridLayoutManager(this,3));
        loadingbookdata = findViewById(R.id.progressBar);

        loadingbookdata.setVisibility(View.VISIBLE);
        //getResponse();
        getResponseTest();
        loadingbookdata.setVisibility(View.INVISIBLE);
    }

    private void getResponse() {
        bookstoreApi = ApiClient.getClient().create(BookstoreApi.class);

        Call<List<Book>> call = bookstoreApi.getBooks();

        call.enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                books = new ArrayList<>(response.body());
                ViewAllBooksAdapter = new userViewAllBooksAdapter(viewAllBooksUser.this, books);
                books_recyclerview.setAdapter(ViewAllBooksAdapter);
                Toast.makeText(viewAllBooksUser.this, "Success",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                Toast.makeText(viewAllBooksUser.this, "Failed",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        // Do Here what ever you want do on back press;
    }

    private void getResponseTest(){
        bookstoreApi = ApiClient.getClient().create(BookstoreApi.class);

        Call<List<Customer_orders>> call = bookstoreApi.getAllOrders();

        call.enqueue(new Callback<List<Customer_orders>>() {
            @Override
            public void onResponse(Call<List<Customer_orders>> call, Response<List<Customer_orders>> response) {
                customer_orders = new ArrayList<>(response.body());
                String function = "test";
            }

            @Override
            public void onFailure(Call<List<Customer_orders>> call, Throwable t) {

            }
        });
    }
}