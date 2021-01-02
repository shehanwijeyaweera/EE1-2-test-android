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
import com.example.ee1_2_test.Adapters.adminViewAllUsersAdapter;
import com.example.ee1_2_test.Model.AdminUserView;
import com.example.ee1_2_test.Model.ApiClient;
import com.example.ee1_2_test.Model.loginResponse;
import com.example.ee1_2_test.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserFragmentAdmin extends Fragment {

    private BookstoreApi bookstoreApi;
    ArrayList<AdminUserView> userViews = new ArrayList<>();
    private RecyclerView user_recyclerview;
    private adminViewAllUsersAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("View All Users");

        View view = inflater.inflate(R.layout.admin_fragment_users, container, false);

        user_recyclerview = view.findViewById(R.id.user_recyclerview_admin);
        user_recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));

        getResponseData();

        return view;
    }

    private void getResponseData() {
        bookstoreApi = ApiClient.getClient().create(BookstoreApi.class);

        Call<List<AdminUserView>> call = bookstoreApi.getAllUsersAdmin();

        call.enqueue(new Callback<List<AdminUserView>>() {
            @Override
            public void onResponse(Call<List<AdminUserView>> call, Response<List<AdminUserView>> response) {
                userViews = new ArrayList<>(response.body());
                adapter = new adminViewAllUsersAdapter(userViews, getContext());
                user_recyclerview.setAdapter(adapter);
                Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();

                //delete button set on click listner
                adapter.setOnItemClickListener(new adminViewAllUsersAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        int id = userViews.get(position).getId();
                        removeItem(position);
                        deleteItem(id);
                    }
                });
            }

            @Override
            public void onFailure(Call<List<AdminUserView>> call, Throwable t) {
                Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void deleteItem(int id) {
        bookstoreApi = ApiClient.getClient().create(BookstoreApi.class);

        Call<loginResponse> call = bookstoreApi.deleteUser(id);

        call.enqueue(new Callback<loginResponse>() {
            @Override
            public void onResponse(Call<loginResponse> call, Response<loginResponse> response) {
                loginResponse loginRes = response.body();

                if(loginRes.getResponse().matches("Correct")){
                    Toast.makeText(getContext(), "User Deleted", Toast.LENGTH_SHORT).show();
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
        userViews.remove(position);
        adapter.notifyItemRemoved(position);
        onResume();
    }
}
