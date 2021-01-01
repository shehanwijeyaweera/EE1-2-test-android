package com.example.ee1_2_test.Fragments;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.loader.content.CursorLoader;

import com.example.ee1_2_test.API.BookstoreApi;
import com.example.ee1_2_test.ArrayAdapters.AuthorAdapter;
import com.example.ee1_2_test.ArrayAdapters.CategoryAdapter;
import com.example.ee1_2_test.Model.AddbookSpinnerData;
import com.example.ee1_2_test.Model.ApiClient;
import com.example.ee1_2_test.Model.Author;
import com.example.ee1_2_test.Model.Book;
import com.example.ee1_2_test.Model.Category;
import com.example.ee1_2_test.Model.RequestBook;
import com.example.ee1_2_test.Model.loginResponse;
import com.example.ee1_2_test.R;
import com.google.gson.JsonArray;

import java.io.File;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

public class AddBookFragmentAdmin extends Fragment {

    Spinner spinnerCategory, spinnerAuthor;
    private Button selectImage, sendDatabtn;
    private ImageView uploadImagepreview;
    private EditText bookTitle, isbn, price, publisher, pubdate, description;
    ArrayList<Category> CategoryList = new ArrayList<>();
    ArrayList<Author> AuthorList = new ArrayList<>();
    private BookstoreApi bookstoreApi;
    private AddbookSpinnerData addbookSpinnerData;
    private CategoryAdapter categoryAdapter;
    private AuthorAdapter authorAdapter;

    String imagePath;
    int selectedCategory, selectedAuthor;

    private static final int IMAGE_PICK_CODE =1000;
    private static final int PERMISSION_CODE = 1001;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle(" ");

        View view = inflater.inflate(R.layout.admin_fragment_add_book, container, false);

        spinnerCategory = view.findViewById(R.id.addNewBook_spinnerCategory_admin);
        spinnerAuthor = view.findViewById(R.id.addNewBook_spinnerAuthor_admin);

        selectImage = view.findViewById(R.id.addbook_selectImage_admin);
        uploadImagepreview = view.findViewById(R.id.addNewBook_imagepreview_admin);

        bookTitle = view.findViewById(R.id.addNewBook_bookTitle_admin);
        isbn = view.findViewById(R.id.addNewBook_isbn_admin);
        price = view.findViewById(R.id.addNewBook_price_admin);
        publisher = view.findViewById(R.id.addNewBook_publisher_admin);
        pubdate = view.findViewById(R.id.addNewBook_pubdate_admin);
        description = view.findViewById(R.id.addNewBook_description_admin);

        sendDatabtn = view.findViewById(R.id.addNewBook_savebtn_admin);

        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Category clickedItem = (Category) parent.getItemAtPosition(position);
                selectedCategory = clickedItem.getCategoryId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerAuthor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Author clickedItem = (Author) parent.getItemAtPosition(position);
                selectedAuthor = clickedItem.getAuthorId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        selectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 0);
            }
        });

        sendDatabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book newBook = getBookData();

                bookstoreApi = ApiClient.getClient().create(BookstoreApi.class);

                Call<loginResponse> call = bookstoreApi.saveBookData(newBook, selectedCategory,selectedAuthor);

                call.enqueue(new Callback<loginResponse>() {
                    @Override
                    public void onResponse(Call<loginResponse> call, Response<loginResponse> response) {
                        loginResponse loginRes = response.body();

                        int Book_ID = Integer.parseInt(loginRes.getResponse());
                        //save book image
                        sendBookImage(Book_ID);
                    }

                    @Override
                    public void onFailure(Call<loginResponse> call, Throwable t) {

                    }
                });

            }
        });




        getSpinnerData();

        return view;
    }

    private void sendBookImage(int book_id) {
        bookstoreApi = ApiClient.getClient().create(BookstoreApi.class);

        File file = new File(imagePath);
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestBody);

        Call<loginResponse> call = bookstoreApi.saveBookImage(body, book_id);

        call.enqueue(new Callback<loginResponse>() {
            @Override
            public void onResponse(Call<loginResponse> call, Response<loginResponse> response) {
                loginResponse loginRes = response.body();

                if (loginRes.getResponse().matches("Correct")) {
                    Toast.makeText(getContext(), "Book Saved successfully", Toast.LENGTH_SHORT).show();
                    bookTitle.setText("");
                    isbn.setText("");
                    price.setText("");
                    publisher.setText("");
                    pubdate.setText("");
                    pubdate.setText("");
                    description.setText("");
                } else if (loginRes.getResponse().matches("Failed")) {
                    //error message
                }
            }

            @Override
            public void onFailure(Call<loginResponse> call, Throwable t) {

            }
        });
    }

    private Book getBookData() {
        Book book = new Book();
        book.setTitle(bookTitle.getText().toString());
        book.setIsbn(Integer.valueOf(isbn.getText().toString()));
        book.setPrice(Double.valueOf(price.getText().toString()));
        book.setPublisher(publisher.getText().toString());
        book.setPubdate(pubdate.getText().toString());
        book.setDescription(description.getText().toString());

        return book;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            if(data==null){
                Toast.makeText(getContext(), "Unable to choose Image!", Toast.LENGTH_SHORT).show();
                return;
            }

            Uri imageUri = data.getData();
            uploadImagepreview.setImageURI(imageUri);
            imagePath = getRealPathFromUri(imageUri);
        }
    }

    private String getRealPathFromUri(Uri uri){
        String[] projection = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(getContext(),uri,projection,null,null,null);
        Cursor cursor = loader.loadInBackground();
        int column_idx = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_idx);
        cursor.close();
        return result;
    }

    private void getSpinnerData() {
        bookstoreApi = ApiClient.getClient().create(BookstoreApi.class);

        Call<AddbookSpinnerData> call = bookstoreApi.getAddBookSpinnerData();

        call.enqueue(new Callback<AddbookSpinnerData>() {
            @Override
            public void onResponse(Call<AddbookSpinnerData> call, Response<AddbookSpinnerData> response) {
                addbookSpinnerData = response.body();
                CategoryList = new ArrayList<>(addbookSpinnerData.getCategory());
                AuthorList = new ArrayList<>(addbookSpinnerData.getAuthor());
                setSpinnerData(CategoryList, AuthorList);
            }

            @Override
            public void onFailure(Call<AddbookSpinnerData> call, Throwable t) {
                Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setSpinnerData(ArrayList<Category> categoryList, ArrayList<Author> authorList) {

        categoryAdapter = new CategoryAdapter(getContext(), categoryList);
        spinnerCategory.setAdapter(categoryAdapter);


        authorAdapter = new AuthorAdapter(getContext(), authorList);
        spinnerAuthor.setAdapter(authorAdapter);
    }


}
