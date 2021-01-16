package com.example.ee1_2_test.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ee1_2_test.API.BookstoreApi;
import com.example.ee1_2_test.Adapters.adminViewAllRequestAdapter;
import com.example.ee1_2_test.Model.ApiClient;
import com.example.ee1_2_test.Model.RequestBook2;
import com.example.ee1_2_test.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookRequestsFragmentStoreworker extends Fragment {

    private RecyclerView recyclerViewRequest;
    private BookstoreApi bookstoreApi;
    private com.example.ee1_2_test.Adapters.adminViewAllRequestAdapter adminViewAllRequestAdapter;
    private ArrayList<RequestBook2> requestBooks = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("View All book requests");

        View view = inflater.inflate(R.layout.storeworker_fragment_bookrequest,container,false);

        recyclerViewRequest = view.findViewById(R.id.requestbook_recyclerview_storeworker);
        recyclerViewRequest.setLayoutManager(new LinearLayoutManager(getContext()));

        getResponseData();

        return view;
    }

    private void getResponseData() {
        bookstoreApi = ApiClient.getClient().create(BookstoreApi.class);

        Call<List<RequestBook2>> call = bookstoreApi.getAllBookRequests();

        call.enqueue(new Callback<List<RequestBook2>>() {
            @Override
            public void onResponse(Call<List<RequestBook2>> call, Response<List<RequestBook2>> response) {
                requestBooks = new ArrayList<>(response.body());
                adminViewAllRequestAdapter = new adminViewAllRequestAdapter(requestBooks, getContext());
                recyclerViewRequest.setAdapter(adminViewAllRequestAdapter);
            }

            @Override
            public void onFailure(Call<List<RequestBook2>> call, Throwable t) {

            }
        });
    }
}
