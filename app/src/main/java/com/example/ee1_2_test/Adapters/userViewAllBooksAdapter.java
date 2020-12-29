package com.example.ee1_2_test.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ee1_2_test.Activity.userSingleBookView;
import com.example.ee1_2_test.Model.Book;
import com.example.ee1_2_test.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class userViewAllBooksAdapter extends RecyclerView.Adapter<userViewAllBooksAdapter.ViewHolder> {

    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<Book> filteredBooks = new ArrayList<>();
    private Context context;
    private Book bookdetails;

    public userViewAllBooksAdapter(Context context, ArrayList<Book> books) {
        this.books = books;
        this.context = context;

    }

    @NonNull
    @Override
    public userViewAllBooksAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(context);
        view = mInflater.inflate(R.layout.cardview_item_book, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull userViewAllBooksAdapter.ViewHolder holder, int position) {
        holder.tv_book_title.setText(books.get(position).getTitle());
        Picasso.get().load(books.get(position).getLogoImagepathApi()).into(holder.Image_book_thumbnail);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, userSingleBookView.class);

                intent.putExtra("ID", books.get(position).getBookId());
                intent.putExtra("Title", books.get(position).getTitle());
                intent.putExtra("Description", books.get(position).getDescription());
                intent.putExtra("Thumbnail", books.get(position).getLogoImagepathApi());
                intent.putExtra("pubdate", books.get(position).getPubdate());
                intent.putExtra("publisher", books.get(position).getPublisher());
                intent.putExtra("price", books.get(position).getPrice());
                try {
                    intent.putExtra("author", books.get(position).getAuthors().get(0).getAuthorName());
                }catch (Exception e){
                    intent.putExtra("author", "Not available");
                }

                try {
                    intent.putExtra("category", books.get(position).getCategories().get(0).getCategoryName());
                }catch (Exception e){
                    intent.putExtra("category", "Not available");
                }
                bookdetails = books.get(position);
                String testDebug = "Test";
                intent.putExtra("bookdetails", bookdetails);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_book_title;
        ImageView Image_book_thumbnail;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_book_title = (TextView) itemView.findViewById(R.id.book_title_user_view_all_books);
            Image_book_thumbnail = (ImageView) itemView.findViewById(R.id.image_view_user_view_all);
            cardView = (CardView) itemView.findViewById(R.id.cardView_id);
        }
    }

    public void filterList(ArrayList<Book> filteredBooks){
        books = filteredBooks;
        notifyDataSetChanged();
    }
}
