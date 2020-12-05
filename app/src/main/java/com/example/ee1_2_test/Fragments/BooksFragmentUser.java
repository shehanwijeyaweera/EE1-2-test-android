package com.example.ee1_2_test.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ee1_2_test.API.BookstoreApi;
import com.example.ee1_2_test.Activity.Userhomepage;
import com.example.ee1_2_test.Activity.viewAllBooksUser;
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

public class BooksFragmentUser extends Fragment {

    private BookstoreApi bookstoreApi;
    ArrayList<Book> books = new ArrayList<>();
    ArrayList<Customer_orders> customer_orders = new ArrayList<>();
    private userViewAllBooksAdapter ViewAllBooksAdapter;
    private RecyclerView books_recyclerview_user;
    private ProgressBar loadingbookdata;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        getActivity().setTitle("Books");

        View view = inflater.inflate(R.layout.fragment_view_all_books,container,false);

        books_recyclerview_user = view.findViewById(R.id.books_recyclerview_user);
        books_recyclerview_user.setLayoutManager(new GridLayoutManager(getContext(),3));
        loadingbookdata = view.findViewById(R.id.progressBar_user);

        loadingbookdata.setVisibility(View.VISIBLE);
        getResponse();
        //getResponseTest();
        loadingbookdata.setVisibility(View.INVISIBLE);

        return view;
    }

    private void getResponse() {
        bookstoreApi = ApiClient.getClient().create(BookstoreApi.class);

        Call<List<Book>> call = bookstoreApi.getBooks();

        call.enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                books = new ArrayList<>(response.body());
                ViewAllBooksAdapter = new userViewAllBooksAdapter(getActivity(), books);
                books_recyclerview_user.setAdapter(ViewAllBooksAdapter);
                Toast.makeText(getActivity(), "Success",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                Toast.makeText(getActivity(), "Failed",Toast.LENGTH_SHORT).show();
            }
        });
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
