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
import com.example.ee1_2_test.Model.ApiClient;
import com.example.ee1_2_test.Model.Refund;
import com.example.ee1_2_test.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RejectedRefundFragmentAdmin extends Fragment {

    private RecyclerView refundRequests_recyclerview_user;
    private BookstoreApi bookstoreApi;
    private RefundAdapter refundAdapter;
    ArrayList<Refund> refunds = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Rejected Refund Requests");

        View view = inflater.inflate(R.layout.admin_fragment_rejected_refunds,container,false);

        refundRequests_recyclerview_user = view.findViewById(R.id.viewallRejectedrefunds_recyclerview_admin);
        refundRequests_recyclerview_user.setLayoutManager(new LinearLayoutManager(getContext()));

        getResponse();

        return view;
    }

    private void getResponse() {
        bookstoreApi = ApiClient.getClient().create(BookstoreApi.class);

        Call<List<Refund>> call = bookstoreApi.getRejectedRefundRequests();

        call.enqueue(new Callback<List<Refund>>() {
            @Override
            public void onResponse(Call<List<Refund>> call, Response<List<Refund>> response) {
                refunds = new ArrayList<>(response.body());
                refundAdapter = new RefundAdapter(refunds,getContext());
                refundRequests_recyclerview_user.setAdapter(refundAdapter);
            }

            @Override
            public void onFailure(Call<List<Refund>> call, Throwable t) {
                Toast.makeText(getContext(), "Failed to receive data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
