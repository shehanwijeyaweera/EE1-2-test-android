package com.example.ee1_2_test.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ee1_2_test.Activity.Single_book_view;
import com.example.ee1_2_test.Model.Book;
import com.example.ee1_2_test.R;
import com.example.ee1_2_test.Sessions.SessionManagement;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.ViewHolder> {

    private ArrayList<Book> books = new ArrayList<>();
    private Context context;
    private Book bookdetails;

    public BooksAdapter(Context context, ArrayList<Book> books){
            this.books = books;
            this.context = context;
    }

    @NonNull
    @Override
    public BooksAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_row,parent,false);
        return new BooksAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BooksAdapter.ViewHolder holder, int position) {
        holder.book_title.setText(books.get(position).getTitle());
        holder.book_desc.setText(books.get(position).getDescription());

        Picasso.get().load(books.get(position).getLogoImagepathApi()).resize(130,170).into(holder.book_image);

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Single_book_view.class);
                intent.putExtra("title", books.get(position).getTitle());
                intent.putExtra("price", books.get(position).getPrice());
                intent.putExtra("isbn", books.get(position).getIsbn());
                intent.putExtra("desc", books.get(position).getDescription());
                intent.putExtra("imageLocation", books.get(position).getLogoImagepathApi());
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

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView book_image;
        private TextView book_title;
        private TextView book_desc;
        private ConstraintLayout mainLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            book_image=(ImageView)itemView.findViewById(R.id.book_image);
            book_title=(TextView) itemView.findViewById(R.id.book_title);
            book_desc=(TextView) itemView.findViewById(R.id.book_desc);
            mainLayout= (ConstraintLayout) itemView.findViewById(R.id.mainLayout);
        }
    }

}
