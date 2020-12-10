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
import com.example.ee1_2_test.Adapters.OrderItemAdapter;
import com.example.ee1_2_test.Adapters.OrdersAdapter;
import com.example.ee1_2_test.Model.ApiClient;
import com.example.ee1_2_test.Model.Book;
import com.example.ee1_2_test.Model.Customer_orders;
import com.example.ee1_2_test.Model.User;
import com.example.ee1_2_test.R;
import com.example.ee1_2_test.Sessions.SessionManagement;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PastOrdersFragmentUser extends Fragment {

    private RecyclerView pastOrders_recyclerview_user;
    private BookstoreApi bookstoreApi;
    private OrdersAdapter ordersAdapter;
    ArrayList<Customer_orders> customer_orders = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        getActivity().setTitle("Past Orders");

        View view = inflater.inflate(R.layout.fragment_past_orders,container,false);

        pastOrders_recyclerview_user = view.findViewById(R.id.pastorders_recyclerview_user);
        pastOrders_recyclerview_user.setLayoutManager(new LinearLayoutManager(getContext()));

        getResponse();

        return view;
    }

    private void getResponse() {
        SessionManagement sessionManagement = new SessionManagement(getContext());
        User user = sessionManagement.getSession();
        bookstoreApi = ApiClient.getClient().create(BookstoreApi.class);

        Call<List<Customer_orders>> call = bookstoreApi.getCustomerPastOrders(user.getId());

        call.enqueue(new Callback<List<Customer_orders>>() {
            @Override
            public void onResponse(Call<List<Customer_orders>> call, Response<List<Customer_orders>> response) {
                customer_orders = new ArrayList<>(response.body());
                ordersAdapter = new OrdersAdapter(getContext(), customer_orders);
                pastOrders_recyclerview_user.setAdapter(ordersAdapter);
            }

            @Override
            public void onFailure(Call<List<Customer_orders>> call, Throwable t) {
                Toast.makeText(getContext(), "Failed to receive data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
