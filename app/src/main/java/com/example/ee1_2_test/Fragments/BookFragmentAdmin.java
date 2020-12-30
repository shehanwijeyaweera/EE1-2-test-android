package com.example.ee1_2_test.Fragments;

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
import com.example.ee1_2_test.Activity.viewAllBooksUser;
import com.example.ee1_2_test.Adapters.adminViewAllBooksAdapter;
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

public class BookFragmentAdmin extends Fragment {

    private BookstoreApi bookstoreApi;
    ArrayList<Book> books = new ArrayList<>();
    ArrayList<Customer_orders> customer_orders = new ArrayList<>();
    private RecyclerView books_recyclerview;
    private ProgressBar loadingbookdata;
    private adminViewAllBooksAdapter ViewAllBooksAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("View All Books");
        View view = inflater.inflate(R.layout.admin_fragment_book,container,false);

        books_recyclerview = view.findViewById(R.id.books_recyclerview_admin_view_all_books);
        books_recyclerview.setLayoutManager(new GridLayoutManager(getContext(),3));
        loadingbookdata = view.findViewById(R.id.admin_viewAllbooks_progressbar);

        loadingbookdata.setVisibility(View.VISIBLE);
        //getResponse();
        getResponseTest();
        loadingbookdata.setVisibility(View.INVISIBLE);

        return view;
    }

    private void getResponseTest() {
        bookstoreApi = ApiClient.getClient().create(BookstoreApi.class);

        Call<List<Book>> call = bookstoreApi.getBooks();

        call.enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                books = new ArrayList<>(response.body());
                ViewAllBooksAdapter = new adminViewAllBooksAdapter(books, getContext());
                books_recyclerview.setAdapter(ViewAllBooksAdapter);
                Toast.makeText(getContext(), "Success",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                Toast.makeText(getContext(), "Failed",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
