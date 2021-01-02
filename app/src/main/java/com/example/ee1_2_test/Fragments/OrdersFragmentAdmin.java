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
import com.example.ee1_2_test.Model.loginResponse;
import com.example.ee1_2_test.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrdersFragmentAdmin extends Fragment {

    private RecyclerView orders_recyclerview_admin;
    private BookstoreApi bookstoreApi;
    private OrdersAdapter ordersAdapter;
    private OrdersAdapterAdmin ordersAdapterAdmin;
    ArrayList<Customer_orders> customer_orders = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("View All Orders");

        View view = inflater.inflate(R.layout.admin_fragment_orders,container,false);

        orders_recyclerview_admin = view.findViewById(R.id.orders_recyclerview_admin);
        orders_recyclerview_admin.setLayoutManager(new LinearLayoutManager(getContext()));

        getResponse();

        return view;
    }

    private void getResponse() {

        bookstoreApi = ApiClient.getClient().create(BookstoreApi.class);

        Call<List<Customer_orders>> call = bookstoreApi.getAllOrders();

        call.enqueue(new Callback<List<Customer_orders>>() {
            @Override
            public void onResponse(Call<List<Customer_orders>> call, Response<List<Customer_orders>> response) {
                customer_orders = new ArrayList<>(response.body());
                ordersAdapterAdmin = new OrdersAdapterAdmin(customer_orders, getContext());
                orders_recyclerview_admin.setAdapter(ordersAdapterAdmin);

                ordersAdapterAdmin.setOnItemClickListener(new OrdersAdapterAdmin.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        int id = customer_orders.get(position).getId();
                        removeItem(position);
                        markAsShipped(id);
                    }
                });
            }

            @Override
            public void onFailure(Call<List<Customer_orders>> call, Throwable t) {
                Toast.makeText(getContext(), "Failed to get data from API", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void markAsShipped(int id) {
        bookstoreApi = ApiClient.getClient().create(BookstoreApi.class);

        Call <loginResponse> call = bookstoreApi.markOrderAsShipped(id);

        call.enqueue(new Callback<loginResponse>() {
            @Override
            public void onResponse(Call<loginResponse> call, Response<loginResponse> response) {
                loginResponse loginRes = response.body();

                if(loginRes.getResponse().matches("Correct")){
                    Toast.makeText(getContext(), "Marked as Shipped", Toast.LENGTH_SHORT).show();
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
        customer_orders.remove(position);
        ordersAdapterAdmin.notifyItemRemoved(position);
        onResume();
    }
}
