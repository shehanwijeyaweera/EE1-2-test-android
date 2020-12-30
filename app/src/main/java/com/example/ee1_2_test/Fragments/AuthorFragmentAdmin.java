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
import com.example.ee1_2_test.Adapters.adminViewAllAuthorsAdapter;
import com.example.ee1_2_test.Adapters.adminViewAllCategoriesAdapter;
import com.example.ee1_2_test.Model.ApiClient;
import com.example.ee1_2_test.Model.Author;
import com.example.ee1_2_test.Model.Category;
import com.example.ee1_2_test.Model.Customer_orders;
import com.example.ee1_2_test.Model.loginResponse;
import com.example.ee1_2_test.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthorFragmentAdmin extends Fragment {

    private BookstoreApi bookstoreApi;
    ArrayList<Author> authors = new ArrayList<>();
    private RecyclerView authors_recyclerview;
    private ProgressBar loadingcategorydata;
    private adminViewAllAuthorsAdapter adminViewAllAuthorsAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("View All Authors");

        View view = inflater.inflate(R.layout.admin_fragment_author,container,false);

        authors_recyclerview = view.findViewById(R.id.author_recyclerview_admin);
        authors_recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));

        getResponseData();

        return view;
    }

    private void getResponseData() {
        bookstoreApi = ApiClient.getClient().create(BookstoreApi.class);

        Call<List<Author>> call = bookstoreApi.getAllAuthors();

        call.enqueue(new Callback<List<Author>>() {
            @Override
            public void onResponse(Call<List<Author>> call, Response<List<Author>> response) {
                authors = new ArrayList<>(response.body());
                adminViewAllAuthorsAdapter = new adminViewAllAuthorsAdapter(authors, getContext());
                authors_recyclerview.setAdapter(adminViewAllAuthorsAdapter);
                Toast.makeText(getContext(), "Success",Toast.LENGTH_SHORT).show();

                //set on click listners
                adminViewAllAuthorsAdapter.setOnItemClickListener(new adminViewAllAuthorsAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        int id = authors.get(position).getAuthorId();
                        removeItem(position);
                        deleteItem(id);
                    }
                });
            }

            @Override
            public void onFailure(Call<List<Author>> call, Throwable t) {
                Toast.makeText(getContext(), "Failed",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void deleteItem(int id) {
        bookstoreApi = ApiClient.getClient().create(BookstoreApi.class);

        Call<loginResponse> call = bookstoreApi.deleteAuthor(id);

        call.enqueue(new Callback<loginResponse>() {
            @Override
            public void onResponse(Call<loginResponse> call, Response<loginResponse> response) {
                loginResponse loginRes = response.body();

                if(loginRes.getResponse().matches("Correct")){
                    Toast.makeText(getContext(), "Author Deleted", Toast.LENGTH_SHORT).show();
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
        authors.remove(position);
        adminViewAllAuthorsAdapter.notifyItemRemoved(position);
        onResume();
    }
}
