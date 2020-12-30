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
import com.example.ee1_2_test.Adapters.RefundAdapter;
import com.example.ee1_2_test.Adapters.RefundAdapterAdmin;
import com.example.ee1_2_test.Model.ApiClient;
import com.example.ee1_2_test.Model.Refund;
import com.example.ee1_2_test.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RefundFragmentAdmin extends Fragment {

    private RecyclerView refundRequests_recyclerview_admin;
    private BookstoreApi bookstoreApi;
    private RefundAdapterAdmin refundAdapter;
    ArrayList<Refund> refunds = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("View All Refund Requests");

        View view = inflater.inflate(R.layout.admin_fragment_refunds,container,false);

        refundRequests_recyclerview_admin = view.findViewById(R.id.viewallrefunds_recyclerview_admin);
        refundRequests_recyclerview_admin.setLayoutManager(new LinearLayoutManager(getContext()));

        getResponse();

        return view;
    }

    private void getResponse() {
        bookstoreApi = ApiClient.getClient().create(BookstoreApi.class);

        Call<List<Refund>> call = bookstoreApi.getAllNonResponededRefundRequests();

        call.enqueue(new Callback<List<Refund>>() {
            @Override
            public void onResponse(Call<List<Refund>> call, Response<List<Refund>> response) {
                refunds = new ArrayList<>(response.body());
                refundAdapter = new RefundAdapterAdmin(refunds,getContext());
                refundRequests_recyclerview_admin.setAdapter(refundAdapter);
            }

            @Override
            public void onFailure(Call<List<Refund>> call, Throwable t) {
                Toast.makeText(getContext(), "Failed to receive data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
