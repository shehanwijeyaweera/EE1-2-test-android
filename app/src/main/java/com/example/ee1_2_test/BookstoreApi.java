package com.example.ee1_2_test;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BookstoreApi {

    @GET("books")
    Call<List<Book>> getBooks();
}
