package com.example.ee1_2_test.API;

import com.example.ee1_2_test.JSON.Checkout;
import com.example.ee1_2_test.Model.AdminDashboard;
import com.example.ee1_2_test.Model.Book;
import com.example.ee1_2_test.Model.CartItem;
import com.example.ee1_2_test.Model.Customer_orders;
import com.example.ee1_2_test.Model.loginResponse;
import com.example.ee1_2_test.Model.loginResponse2;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface BookstoreApi {

    @GET("books")
    Call<List<Book>> getBooks();

    @GET("book/{id}")
    Call<Book> getBookdetails(@Path("id") int bookid);

    @GET("login/{username}/{password}")
    Call<loginResponse2> getloginRespones(@Path("username")String username, @Path("password")String password);

    @GET("admindashboard")
    Call<AdminDashboard> getDashboardstats();

    @GET("custorders")
    Call<List<Customer_orders>> getAllOrders();

    @GET("custpendingorders")
    Call<List<Customer_orders>> getAllPendingOrders();

    @GET("custshippedorders")
    Call<List<Customer_orders>> getAllShippedOrders();

    @GET("customerorders/{userid}")
    Call<List<Customer_orders>> getCustomerPastOrders(@Path("userid") Integer userID);

    @POST("checkout/{username}/{total}")
    Call<loginResponse> checkoutCart(@Path("username") String username, @Path("total") double total, @Body List<CartItem> cartItems);
}
