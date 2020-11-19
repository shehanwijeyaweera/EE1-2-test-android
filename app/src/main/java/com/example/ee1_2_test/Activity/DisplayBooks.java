package com.example.ee1_2_test.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.ee1_2_test.API.BookstoreApi;
import com.example.ee1_2_test.Model.Book;
import com.example.ee1_2_test.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DisplayBooks extends AppCompatActivity {

    private TextView textViewBooks;

    private BookstoreApi bookstoreApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_books);

        textViewBooks = findViewById(R.id.book_results);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.8.120:8080/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        bookstoreApi = retrofit.create(BookstoreApi.class);

        getbooks();
        //getbookdetails();
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
                    content += "Logo Image path: " + book.getImagepath() + "\n\n";

                    textViewBooks.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                textViewBooks.setText(t.getMessage());
            }
        });
    }

    private void getbookdetails(){
        Call<Book> call = bookstoreApi.getBookdetails(33);

        call.enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
                if(!response.isSuccessful()){
                    textViewBooks.setText("Code: " + response.code());
                    return;
                }

                Book booksobj = response.body();


                String content ="";
                content += "ID: "+ booksobj.getBookId() + "\n";
                content += "Title: "+ booksobj.getTitle() + "\n";
                content += "description: "+ booksobj.getDescription() + "\n";
                content += "price(Rs.): "+ booksobj.getPrice() + "\n";
                content += "publisher: "+ booksobj.getPublisher() + "\n";
                content += "pubdate: "+ booksobj.getPubdate() + "\n";
                content += "isbn: "+ booksobj.getIsbn() + "\n";
                content += "Logo Image path: " + booksobj.getImagepath() + "\n\n";

                textViewBooks.append(content);

            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {
                textViewBooks.setText(t.getMessage());
            }
        });
    }
}