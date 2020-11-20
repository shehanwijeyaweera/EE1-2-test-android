package com.example.ee1_2_test.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ee1_2_test.API.BookstoreApi;
import com.example.ee1_2_test.Model.ApiClient;
import com.example.ee1_2_test.Model.Book;
import com.example.ee1_2_test.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DisplayBooks extends AppCompatActivity {

    private TextView textViewBooks;

    private BookstoreApi bookstoreApi;

    ArrayList<Book> books = new ArrayList<>();

    private BooksAdapter booksAdapter;

    private RecyclerView books_recyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_books);

        books_recyclerview=(RecyclerView)findViewById(R.id.books_recyclerview);
        books_recyclerview.setLayoutManager(new LinearLayoutManager(this));

        getResponse();

    }

    private void getResponse() {
        bookstoreApi = ApiClient.getClient().create(BookstoreApi.class);

        Call<List<Book>> call = bookstoreApi.getBooks();

        call.enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                books = new ArrayList<>(response.body());
                booksAdapter= new BooksAdapter(DisplayBooks.this, books);
                books_recyclerview.setAdapter(booksAdapter);
                Toast.makeText(DisplayBooks.this, "Success",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                Toast.makeText(DisplayBooks.this, "Failed",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        // Do Here what ever you want do on back press;
    }

    private void getbooks() {
        Call<List<Book>> call = bookstoreApi.getBooks();

        call.enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                if(!response.isSuccessful()){
                    textViewBooks.setText("Code: " + response.code());
                    return;
                }

                List<Book> books = response.body();

                for(Book book : books){
                    String content ="";
                    content += "ID: "+ book.getBookId() + "\n";
                    content += "Title: "+ book.getTitle() + "\n";
                    content += "description: "+ book.getDescription() + "\n";
                    content += "price(Rs.): "+ book.getPrice() + "\n";
                    content += "publisher: "+ book.getPublisher() + "\n";
                    content += "pubdate: "+ book.getPubdate() + "\n";
                    content += "isbn: "+ book.getIsbn() + "\n";
                    content += "Logo Image path: " + book.getLogoImagepathApi() + "\n\n";

                    textViewBooks.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                textViewBooks.setText(t.getMessage());
            }
        });
    }

}