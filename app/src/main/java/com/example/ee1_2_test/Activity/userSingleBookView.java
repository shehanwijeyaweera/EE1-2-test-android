package com.example.ee1_2_test.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ee1_2_test.Dialog.AddToCartDialog;
import com.example.ee1_2_test.Model.Book;
import com.example.ee1_2_test.R;
import com.example.ee1_2_test.Sessions.SessionManagement;
import com.squareup.picasso.Picasso;

public class userSingleBookView extends AppCompatActivity implements AddToCartDialog.AddToCartListener {

    private TextView tvTitle, tvDescription, tvCategory, tvpublisher, tvpubdate, tvprice, tvauthor;
    private ImageView bookImage;
    private Button addToCart, editbook;
    private Book bookDetails;
    int quantityValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_single_book_view);

        tvTitle = findViewById(R.id.book_title_user_view_single_book);
        tvDescription = findViewById(R.id.book_desc_user_view_single_book);
        tvCategory = findViewById(R.id.book_category_user_view_single_book);
        bookImage = findViewById(R.id.bookthumbnail_user_single_book_view);
        tvpubdate = findViewById(R.id.pubdate_user_view_single_book);
        tvpublisher = findViewById(R.id.publisher_user_view_single_book);
        tvprice = findViewById(R.id.price_user_view_single_book);
        tvauthor = findViewById(R.id.author_user_view_single_book);
        addToCart = findViewById(R.id.add_to_cart);
        editbook = findViewById(R.id.edit_book_admin);

        Intent intent = getIntent();
        String Title = intent.getExtras().getString("Title");
        String Description = intent.getExtras().getString("Description");
        String imagepath = intent.getExtras().getString("Thumbnail");
        String category = intent.getExtras().getString("category");
        String pubdate = intent.getExtras().getString("pubdate");
        String publisher = intent.getExtras().getString("publisher");
        double price = intent.getExtras().getDouble("price");
        String author = intent.getExtras().getString("author");

        String stringdouble = "Price: Rs." + Double.toString(price) +"0";

        tvTitle.setText(Title);
        tvDescription.setText(Description);
        tvCategory.setText(category);
        tvpubdate.setText("Published date: "+pubdate);
        tvpublisher.setText("Publisher: "+publisher);
        tvprice.setText(stringdouble);
        tvauthor.setText("Author: "+author);
        Picasso.get().load(imagepath).into(bookImage);

        SessionManagement sessionManagement = new SessionManagement(userSingleBookView.this);
        final String role = sessionManagement.getRole();

        if(role.equalsIgnoreCase("Admin")){
            addToCart.setVisibility(View.INVISIBLE);
        }
        if(role.equalsIgnoreCase("User")){
            editbook.setVisibility(View.INVISIBLE);
        }



        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

    }

    private void openDialog() {
        AddToCartDialog addToCartDialog = new AddToCartDialog();
        addToCartDialog.show(getSupportFragmentManager(), "Quantity");
    }

    @Override
    public void applyQuantity(String quantity) {
        SessionManagement sessionManagement = new SessionManagement(userSingleBookView.this);
        quantityValue  = Integer.parseInt(quantity);
        bookDetails = (Book) getIntent().getSerializableExtra("bookdetails");
        sessionManagement.addToCart(bookDetails,quantityValue);
    }
}