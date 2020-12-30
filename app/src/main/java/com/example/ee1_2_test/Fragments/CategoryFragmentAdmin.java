package com.example.ee1_2_test.Fragments;

import android.content.Intent;
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
import com.example.ee1_2_test.Activity.AdminDashboard;
import com.example.ee1_2_test.Activity.AdmineditCategory;
import com.example.ee1_2_test.Adapters.adminViewAllBooksAdapter;
import com.example.ee1_2_test.Adapters.adminViewAllCategoriesAdapter;
import com.example.ee1_2_test.Model.ApiClient;
import com.example.ee1_2_test.Model.Book;
import com.example.ee1_2_test.Model.Category;
import com.example.ee1_2_test.Model.Customer_orders;
import com.example.ee1_2_test.Model.loginResponse;
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
        getActivity().setTitle("View All Categories");
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

                //set on click listners
                adminViewAllCategoriesAdapter.setOnItemClickListener(new adminViewAllCategoriesAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        int id = categories.get(position).getCategoryId();
                        removeItem(position);
                        deleteItem(id);
                    }
                });
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Toast.makeText(getContext(), "Failed",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void deleteItem(int id) {
        bookstoreApi = ApiClient.getClient().create(BookstoreApi.class);

        Call<loginResponse> call = bookstoreApi.deleteCategory(id);

        call.enqueue(new Callback<loginResponse>() {
            @Override
            public void onResponse(Call<loginResponse> call, Response<loginResponse> response) {
                loginResponse loginRes = response.body();

                if(loginRes.getResponse().matches("Correct")){
                    Toast.makeText(getContext(), "Category Deleted", Toast.LENGTH_SHORT).show();
                }
                else if(loginRes.getResponse().matches("Failed")){
                    //error message
                }
            }

            @Override
            public void onFailure(Call<loginResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void removeItem(int position) {
        categories.remove(position);
        adminViewAllCategoriesAdapter.notifyItemRemoved(position);
        onResume();
    }
}
