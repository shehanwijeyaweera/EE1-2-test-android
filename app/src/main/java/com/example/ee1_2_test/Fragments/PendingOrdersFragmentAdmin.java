package com.example.ee1_2_test.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ee1_2_test.API.BookstoreApi;
import com.example.ee1_2_test.Adapters.OrdersAdapter;
import com.example.ee1_2_test.Adapters.OrdersAdapterAdmin;
import com.example.ee1_2_test.Model.ApiClient;
import com.example.ee1_2_test.Model.Customer_orders;
import com.example.ee1_2_test.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PendingOrdersFragmentAdmin extends Fragment {

    private RecyclerView Pendingorders_recyclerview_admin;
    private BookstoreApi bookstoreApi;
    private OrdersAdapterAdmin ordersAdapter;
    ArrayList<Customer_orders> customer_orders = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.admin_fragment_pendingorders,container,false);

        Pendingorders_recyclerview_admin = view.findViewById(R.id.pendingorders_recyclerview_admin);
        Pendingorders_recyclerview_admin.setLayoutManager(new LinearLayoutManager(getContext()));

        getResponse();

        return view;
    }

    private void getResponse() {

        bookstoreApi = ApiClient.getClient().create(BookstoreApi.class);

        Call<List<Customer_orders>> call = bookstoreApi.getAllPendingOrders();

        call.enqueue(new Callback<List<Customer_orders>>() {
            @Override
            public void onResponse(Call<List<Customer_orders>> call, Response<List<Customer_orders>> response) {
                customer_orders = new ArrayList<>(response.body());
                ordersAdapter = new OrdersAdapterAdmin(customer_orders, getContext());
                Pendingorders_recyclerview_admin.setAdapter(ordersAdapter);
            }

            @Override
            public void onFailure(Call<List<Customer_orders>> call, Throwable t) {
                Toast.makeText(getContext(), "Failed to get data from API", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
