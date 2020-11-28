package com.example.ee1_2_test.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ee1_2_test.R;
import com.squareup.picasso.Picasso;

public class userSingleBookView extends AppCompatActivity {

    private TextView tvTitle, tvDescription, tvCategory, tvpublisher, tvpubdate, tvprice, tvauthor;
    private ImageView bookImage;

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


        Intent intent = getIntent();
        String Title = intent.getExtras().getString("Title");
        String Description = intent.getExtras().getString("Description");
        String imagepath = intent.getExtras().getString("Thumbnail");
        String category = intent.getExtras().getString("category");
        String pubdate = intent.getExtras().getString("pubdate");
        String publisher = intent.getExtras().getString("publisher");
        double price = intent.getExtras().getDouble("price");
        String author = intent.getExtras().getString("author");

        String stringdouble = Double.toString(price);

        tvTitle.setText(Title);
        tvDescription.setText(Description);
        tvCategory.setText(category);
        tvpubdate.setText(pubdate);
        tvpublisher.setText(publisher);
        tvprice.setText(stringdouble);
        tvauthor.setText(author);
        Picasso.get().load(imagepath).into(bookImage);
    }
}