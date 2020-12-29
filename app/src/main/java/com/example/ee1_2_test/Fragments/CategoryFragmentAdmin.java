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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ee1_2_test.API.BookstoreApi;
import com.example.ee1_2_test.Adapters.adminViewAllBooksAdapter;
import com.example.ee1_2_test.Adapters.adminViewAllCategoriesAdapter;
import com.example.ee1_2_test.Model.ApiClient;
import com.example.ee1_2_test.Model.Book;
import com.example.ee1_2_test.Model.Category;
import com.example.ee1_2_test.Model.Customer_orders;
import com.example.ee1_2_test.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryFragmentAdmin extends Fragment {

    private BookstoreApi bookstoreApi;
    ArrayList<Category> categories = new ArrayList<>();
    ArrayList<Customer_orders> customer_orders = new ArrayList<>();
    private RecyclerView categories_recyclerview;
    private ProgressBar loadingcategorydata;
    private adminViewAllCategoriesAdapter adminViewAllCategoriesAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.admin_fragment_category,container,false);

        categories_recyclerview = view.findViewById(R.id.category_recyclerview_admin);
        categories_recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        loadingcategorydata = view.findViewById(R.id.category_progressbar_admin);


        loadingcategorydata.setVisibility(View.VISIBLE);
        getResponseData();
        loadingcategorydata.setVisibility(View.INVISIBLE);
        return view;
    }

    private void getResponseData() {
        bookstoreApi = ApiClient.getClient().create(BookstoreApi.class);

        Call<List<Category>> call = bookstoreApi.getAllCategories();

        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                categories = new ArrayList<>(response.body());
                adminViewAllCategoriesAdapter = new adminViewAllCategoriesAdapter(categories, getContext());
                categories_recyclerview.setAdapter(adminViewAllCategoriesAdapter);
                Toast.makeText(getContext(), "Success",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Toast.makeText(getContext(), "Failed",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
