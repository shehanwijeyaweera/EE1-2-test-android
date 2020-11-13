package com.example.ee1_2_test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView textViewBooks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewBooks = findViewById(R.id.book_results);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.8.120:8080/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        BookstoreApi bookstoreApi = retrofit.create(BookstoreApi.class);

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
                    content += "Logo Image path: " + book.getImagepath() + "\n";

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