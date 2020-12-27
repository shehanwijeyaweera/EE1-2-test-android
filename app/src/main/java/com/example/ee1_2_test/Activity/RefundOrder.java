package com.example.ee1_2_test.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ee1_2_test.API.BookstoreApi;
import com.example.ee1_2_test.Dialog.DialogRequestBook;
import com.example.ee1_2_test.Model.ApiClient;
import com.example.ee1_2_test.Model.Refund;
import com.example.ee1_2_test.Model.User;
import com.example.ee1_2_test.Model.loginResponse;
import com.example.ee1_2_test.R;
import com.example.ee1_2_test.Sessions.SessionManagement;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RefundOrder extends AppCompatActivity {

    private TextView orderId;
    private EditText reasonEditText;
    private Button submitBtn;
    private BookstoreApi bookstoreApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refund_order);

        orderId = findViewById(R.id.refundOrder_orderId_user);
        reasonEditText = findViewById(R.id.refundOrder_reason_user);
        submitBtn = findViewById(R.id.refundOrder_submitbtn_user);

        Intent intent = getIntent();

        String id = intent.getStringExtra("orderId");

        orderId.setText("Refund Order : " + id);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SessionManagement sessionManagement = new SessionManagement(RefundOrder.this);
                User user = sessionManagement.getSession();

                Refund refund = new Refund();
                refund.setReason(reasonEditText.getText().toString());
                refund.setUser(user);

                boolean validate = validateInput(refund);

                if(validate){
                    getResponse(refund, id, user.getId());
                }else {
                    // error messages
                }

            }
        });
    }

    private boolean validateInput(Refund refund) {
        if(refund.getReason().matches("")){
            return false;
        }else {
            return true;
        }
    }

    private void getResponse(Refund refund, String id, int user_id) {

        bookstoreApi = ApiClient.getClient().create(BookstoreApi.class);

        Call<loginResponse> call = bookstoreApi.postRefundReq(refund, id, user_id);

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
                Toast.makeText(RefundOrder.this, "Failed to receive data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void openDialog() {
        DialogRequestBook dialogRequestBook = new DialogRequestBook();
        dialogRequestBook.show(getSupportFragmentManager(),"Info");
    }
}