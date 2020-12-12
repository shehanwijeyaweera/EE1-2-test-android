package com.example.ee1_2_test.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ee1_2_test.API.BookstoreApi;
import com.example.ee1_2_test.Dialog.DialogRequestBook;
import com.example.ee1_2_test.Model.ApiClient;
import com.example.ee1_2_test.Model.RequestBook;
import com.example.ee1_2_test.Model.loginResponse;
import com.example.ee1_2_test.R;
import com.example.ee1_2_test.Sessions.SessionManagement;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestBookFragmentUser extends Fragment {

    private EditText bookName, quantity;
    private Button submitbtn;
    private TextView errorEmpty;
    private BookstoreApi bookstoreApi;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("");

        View view = inflater.inflate(R.layout.fragment_request_book, container, false);

        bookName = view.findViewById(R.id.bookName_requestbook_user);
        quantity = view.findViewById(R.id.quantity_requestbook_user);
        submitbtn = view.findViewById(R.id.submitbtn_requestbook_user);
        errorEmpty = view.findViewById(R.id.errorEmpty_requestbook_user);

        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                errorEmpty.setVisibility(View.INVISIBLE);
                RequestBook requestBook = new RequestBook();
                requestBook = getInputValues();
                boolean validate = validateInputs(requestBook);
                if(validate){
                    sendResponse(requestBook);
                }
                else {
                    //do nothing
                    errorEmpty.setVisibility(View.VISIBLE);
                }

            }
        });

        return view;
    }

    private RequestBook getInputValues() {
        RequestBook requestBook = new RequestBook();
        //get User
        SessionManagement sessionManagement = new SessionManagement(getContext());

        requestBook.setBookName(bookName.getText().toString());
        requestBook.setQuantity(Integer.parseInt(quantity.getText().toString()));
        requestBook.setUser(sessionManagement.getSession());

        return requestBook;
    }

    private void sendResponse(RequestBook requestBook) {
        bookstoreApi = ApiClient.getClient().create(BookstoreApi.class);

        Call<loginResponse> call = bookstoreApi.getBookRequestResponse(requestBook);

        call.enqueue(new Callback<loginResponse>() {
            @Override
            public void onResponse(Call<loginResponse> call, Response<loginResponse> response) {
                loginResponse loginRes = response.body();
                if(loginRes.getResponse().matches("Correct")){
                    openDialog();
                }
                else if(loginRes.getResponse().matches("Failed")){
                    //error message
                }
            }

            @Override
            public void onFailure(Call<loginResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Something Went wrong "+t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void openDialog() {
        DialogRequestBook dialogRequestBook = new DialogRequestBook();
        dialogRequestBook.show(getFragmentManager(),"Info");
    }

    private boolean validateInputs(RequestBook requestBook) {
        if (requestBook.getBookName().matches("")) {
            return false;
        } else {
            return true;
        }
    }
}
