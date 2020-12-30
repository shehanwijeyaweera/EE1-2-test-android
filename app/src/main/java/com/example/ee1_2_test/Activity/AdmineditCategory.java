package com.example.ee1_2_test.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ee1_2_test.API.BookstoreApi;
import com.example.ee1_2_test.Model.ApiClient;
import com.example.ee1_2_test.Model.Category;
import com.example.ee1_2_test.Model.loginResponse;
import com.example.ee1_2_test.R;
import com.example.ee1_2_test.Sessions.SessionManagement;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdmineditCategory extends AppCompatActivity {
    private TextView category_id;
    private EditText category_name;
    private Button submitbtn;
    private ProgressBar loadingdata;
    private BookstoreApi bookstoreApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminedit_category);

        category_id = findViewById(R.id.editcategory_categoryId_admin);
        category_name = findViewById(R.id.editcategory_categoryName_admin);
        submitbtn = findViewById(R.id.editCategory_submitbtn_admin);
        loadingdata = findViewById(R.id.editcategory_progressbar_admin);

        Intent intent = getIntent();
        int id = intent.getExtras().getInt("ID");
        String categoryName = intent.getExtras().getString("Category_name");

        category_id.setText("Category ID : "+ id);
        category_name.setText(categoryName);

        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingdata.setVisibility(View.VISIBLE);
                getResponse(id);
                loadingdata.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void getResponse(int id) {
        Category updatedCategory = new Category();
        updatedCategory.setCategoryName(category_name.getText().toString());

        bookstoreApi = ApiClient.getClient().create(BookstoreApi.class);

        Call<loginResponse> call = bookstoreApi.updateCategorydetails(updatedCategory, id);

        call.enqueue(new Callback<loginResponse>() {
            @Override
            public void onResponse(Call<loginResponse> call, Response<loginResponse> response) {
                loginResponse loginRes = response.body();

                if(loginRes.getResponse().matches("Correct")){
                    Intent intent = new Intent(AdmineditCategory.this, AdminDashboard.class);
                    startActivity(intent);
                }
                else if(loginRes.getResponse().matches("Failed")){
                    //error message
                }
            }

            @Override
            public void onFailure(Call<loginResponse> call, Throwable t) {
                Toast.makeText(AdmineditCategory.this, "Failed to receive data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}