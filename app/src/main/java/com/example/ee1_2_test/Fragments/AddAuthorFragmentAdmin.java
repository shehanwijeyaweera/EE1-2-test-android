package com.example.ee1_2_test.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ee1_2_test.API.BookstoreApi;
import com.example.ee1_2_test.Model.ApiClient;
import com.example.ee1_2_test.Model.Author;
import com.example.ee1_2_test.Model.loginResponse;
import com.example.ee1_2_test.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddAuthorFragmentAdmin extends Fragment {

    private EditText authorName;
    private Button submitBtn;
    private ProgressBar loadingdata;
    private BookstoreApi bookstoreApi;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle(" ");
        View view = inflater.inflate(R.layout.admin_fragment_add_author,container,false);

        authorName = view.findViewById(R.id.AddNewAuthor_authorName_admin);
        submitBtn = view.findViewById(R.id.AddNewAuthor_submitbtn_admin);
        loadingdata = view.findViewById(R.id.addNewauthor_progressbar_admin);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingdata.setVisibility(View.VISIBLE);
                sendData();
                loadingdata.setVisibility(View.INVISIBLE);
            }
        });

        return view;
    }

    private void sendData() {
        Author newAuthor = new Author();

        newAuthor.setAuthorName(authorName.getText().toString());

        bookstoreApi = ApiClient.getClient().create(BookstoreApi.class);

        Call<loginResponse> call = bookstoreApi.addNewAuthor(newAuthor);

        call.enqueue(new Callback<loginResponse>() {
            @Override
            public void onResponse(Call<loginResponse> call, Response<loginResponse> response) {
                loginResponse loginRes = response.body();

                if (loginRes.getResponse().matches("Correct")) {
                    Toast.makeText(getContext(), "Category Added", Toast.LENGTH_SHORT).show();
                    authorName.setText("");
                } else if (loginRes.getResponse().matches("Failed")) {
                    //error message
                }
            }

            @Override
            public void onFailure(Call<loginResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
