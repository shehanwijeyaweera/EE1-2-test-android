package com.example.ee1_2_test.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ee1_2_test.API.BookstoreApi;
import com.example.ee1_2_test.Model.AdminDashboard;
import com.example.ee1_2_test.Model.ApiClient;
import com.example.ee1_2_test.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardFragmentStoreworker extends Fragment {

    private BookstoreApi bookstoreApi;
    private TextView Sales, Orders, Books, Clients, Requests, Refund;
    AdminDashboard adminDashboard = new AdminDashboard();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle(" ");
        View view = inflater.inflate(R.layout.storeworker_fragment_dashboard,container,false);

        Sales = view.findViewById(R.id.storeworker_dashboard_sales);
        Orders = view.findViewById(R.id.storeworker_dashboard_orders);
        Books = view.findViewById(R.id.storeworker_dashboard_totalbooks);
        Clients = view.findViewById(R.id.storeworker_dashboard_clients);

        getDashboardData();

        return view;
    }

    private void getDashboardData() {

        bookstoreApi = ApiClient.getClient().create(BookstoreApi.class);

        Call<AdminDashboard> call = bookstoreApi.getDashboardstats();

        call.enqueue(new Callback<AdminDashboard>() {
            @Override
            public void onResponse(Call<AdminDashboard> call, Response<AdminDashboard> response) {
                adminDashboard = response.body();
                String stringdouble = Double.toString(adminDashboard.getTotalsales());
                String orders = Integer.toString(adminDashboard.getListOrders());
                String allbooks = Integer.toString(adminDashboard.getAllbooks());
                String clients = Integer.toString(adminDashboard.getAllcustomers());
                Sales.setText("Rs."+stringdouble+"0");
                Orders.setText(orders);
                Books.setText(allbooks);
                Clients.setText(clients);
            }

            @Override
            public void onFailure(Call<AdminDashboard> call, Throwable t) {
                Toast.makeText(getActivity(), "Failed",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
