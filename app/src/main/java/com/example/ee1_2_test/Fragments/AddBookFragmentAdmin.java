package com.example.ee1_2_test.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ee1_2_test.API.BookstoreApi;
import com.example.ee1_2_test.ArrayAdapters.AuthorAdapter;
import com.example.ee1_2_test.ArrayAdapters.CategoryAdapter;
import com.example.ee1_2_test.Model.AddbookSpinnerData;
import com.example.ee1_2_test.Model.ApiClient;
import com.example.ee1_2_test.Model.Author;
import com.example.ee1_2_test.Model.Category;
import com.example.ee1_2_test.R;
import com.google.gson.JsonArray;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddBookFragmentAdmin extends Fragment {

    Spinner spinnerCategory, spinnerAuthor;
    ArrayList<Category> CategoryList = new ArrayList<>();
    ArrayList<Author> AuthorList = new ArrayList<>();
//    ArrayAdapter<Category> categoryAdapter;
   // ArrayAdapter<Author> authorAdapter;
    private BookstoreApi bookstoreApi;
    private AddbookSpinnerData addbookSpinnerData;
    private CategoryAdapter categoryAdapter;
    private AuthorAdapter authorAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle(" ");

        View view = inflater.inflate(R.layout.admin_fragment_add_book, container, false);

        spinnerCategory = view.findViewById(R.id.addNewBook_spinnerCategory_admin);
        spinnerAuthor = view.findViewById(R.id.addNewBook_spinnerAuthor_admin);

        getSpinnerData();

        return view;
    }

    private void getSpinnerData() {
        bookstoreApi = ApiClient.getClient().create(BookstoreApi.class);

        Call<AddbookSpinnerData> call = bookstoreApi.getAddBookSpinnerData();

        call.enqueue(new Callback<AddbookSpinnerData>() {
            @Override
            public void onResponse(Call<AddbookSpinnerData> call, Response<AddbookSpinnerData> response) {
                addbookSpinnerData = response.body();
                CategoryList = new ArrayList<>(addbookSpinnerData.getCategory());
                AuthorList = new ArrayList<>(addbookSpinnerData.getAuthor());
                setSpinnerData(CategoryList, AuthorList);
            }

            @Override
            public void onFailure(Call<AddbookSpinnerData> call, Throwable t) {
                Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setSpinnerData(ArrayList<Category> categoryList, ArrayList<Author> authorList) {

//        categoryAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, categoryList);
//        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerCategory.setAdapter(categoryAdapter);

        categoryAdapter = new CategoryAdapter(getContext(), categoryList);
        spinnerCategory.setAdapter(categoryAdapter);


//        authorAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, authorList);
//        authorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerAuthor.setAdapter(authorAdapter);

        authorAdapter = new AuthorAdapter(getContext(), authorList);
        spinnerAuthor.setAdapter(authorAdapter);
    }
}
