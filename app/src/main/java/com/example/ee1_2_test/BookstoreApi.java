package com.example.ee1_2_test;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface BookstoreApi {

    @GET("books")
    Call<List<Book>> getBooks();

    @GET("book/{id}")
    Call<Book> getBookdetails(@Path("id") int bookid);
}
