package com.example.ee1_2_test.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ee1_2_test.API.BookstoreApi;
import com.example.ee1_2_test.Activity.Editprofile_user;
import com.example.ee1_2_test.Activity.MainActivity;
import com.example.ee1_2_test.Model.ApiClient;
import com.example.ee1_2_test.Model.User;
import com.example.ee1_2_test.R;
import com.example.ee1_2_test.Sessions.SessionManagement;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileInfoFragmentUser extends Fragment {

    private TextView fullname, email, phoneNo, username, address;
    private ProgressBar loadingdata;
    private BookstoreApi bookstoreApi;
    private Button editBtn;
    private User user = new User();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Profile");

        View view = inflater.inflate(R.layout.fragment_profile_info,container,false);

        fullname = view.findViewById(R.id.profile_fullname_user);
        email = view.findViewById(R.id.profile_email_user);
        phoneNo = view.findViewById(R.id.profile_phoneNo_user);
        username = view.findViewById(R.id.profile_username_user);
        loadingdata = view.findViewById(R.id.profile_progressBar2_user);
        address = view.findViewById(R.id.profile_userAddress_user);
        editBtn = view.findViewById(R.id.profile_editbtn_user);

        loadingdata.setVisibility(View.VISIBLE);
        getResponse();
        loadingdata.setVisibility(View.INVISIBLE);

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getContext(), Editprofile_user.class);
                getContext().startActivity(intent);
            }
        });

        return view;
    }

    private void getResponse() {
        SessionManagement sessionManagement = new SessionManagement(getContext());
        User user = sessionManagement.getSession();
        bookstoreApi = ApiClient.getClient().create(BookstoreApi.class);

        Call<User> call = bookstoreApi.getprofileInfo(user.getId());

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User newUser = response.body();
                fullname.setText("FullName :" + newUser.getUserFName()+" "+newUser.getUserLName());
                email.setText("Email :"+user.getUserEmail());
                phoneNo.setText("Phone No :"+newUser.getUserPhoneNo().toString());
                username.setText("Username :"+newUser.getUsername());
                address.setText("Address :"+newUser.getUserAddress());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getContext(), "Failed to receive data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
