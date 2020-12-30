package com.example.ee1_2_test.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ee1_2_test.Activity.AdmineditAuthor;
import com.example.ee1_2_test.Model.Author;
import com.example.ee1_2_test.R;

import java.util.ArrayList;

public class adminViewAllAuthorsAdapter extends RecyclerView.Adapter<adminViewAllAuthorsAdapter.ViewHolder> {

    private ArrayList<Author> authors = new ArrayList<>();
    private Context context;
    private OnItemClickListener mListener;

    public adminViewAllAuthorsAdapter(ArrayList<Author> authors, Context context) {
        this.authors = authors;
        this.context = context;
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(context);
        view = mInflater.inflate(R.layout.author_row, parent, false);
        return new adminViewAllAuthorsAdapter.ViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.author_id.setText(authors.get(position).getAuthorId().toString());
        holder.author_name.setText(authors.get(position).getAuthorName());

        holder.editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AdmineditAuthor.class);

                intent.putExtra("ID", authors.get(position).getAuthorId());
                intent.putExtra("author_name", authors.get(position).getAuthorName());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return authors.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView author_id, author_name;
        Button editbtn, deletebtn;
        ConstraintLayout mainlayout;

        public ViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            author_id = itemView.findViewById(R.id.author_authorId_admin);
            author_name = itemView.findViewById(R.id.author_authorName_admin);

            editbtn = itemView.findViewById(R.id.author_editbtn_admin);
            deletebtn = itemView.findViewById(R.id.author_deleteBtn_admin);

            mainlayout = itemView.findViewById(R.id.mainlayout_author);

            deletebtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
