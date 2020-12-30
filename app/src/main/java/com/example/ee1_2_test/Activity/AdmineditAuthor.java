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
import com.example.ee1_2_test.Model.Author;
import com.example.ee1_2_test.Model.loginResponse;
import com.example.ee1_2_test.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdmineditAuthor extends AppCompatActivity {
    private TextView author_id;
    private EditText author_name;
    private Button submitbtn;
    private ProgressBar loadingdata;
    private BookstoreApi bookstoreApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminedit_author);

        author_id = findViewById(R.id.editauthor_authorId_admin);
        author_name = findViewById(R.id.editauthor_authorName_admin);
        submitbtn = findViewById(R.id.editauthor_submitbtn_admin);
        loadingdata = findViewById(R.id.editauthor_progressbar_admin);

        Intent intent = getIntent();
        int id = intent.getExtras().getInt("ID");
        String authorName = intent.getExtras().getString("author_name");

        author_id.setText("Author ID : "+id);
        author_name.setText(authorName);

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
        Author updatedAuthor = new Author();
        updatedAuthor.setAuthorName(author_name.getText().toString());

        bookstoreApi = ApiClient.getClient().create(BookstoreApi.class);

        Call<loginResponse> call = bookstoreApi.updateAuthorDetails(updatedAuthor, id);

        call.enqueue(new Callback<loginResponse>() {
            @Override
            public void onResponse(Call<loginResponse> call, Response<loginResponse> response) {
                loginResponse loginRes = response.body();

                if(loginRes.getResponse().matches("Correct")){
                    Intent intent = new Intent(AdmineditAuthor.this, AdminDashboard.class);
                    startActivity(intent);
                }
                else if(loginRes.getResponse().matches("Failed")){
                    //error message
                }
            }

            @Override
            public void onFailure(Call<loginResponse> call, Throwable t) {
                Toast.makeText(AdmineditAuthor.this, "Failed to receive data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}