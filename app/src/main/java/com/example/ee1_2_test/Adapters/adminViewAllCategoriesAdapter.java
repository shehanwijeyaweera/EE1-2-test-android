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

import com.example.ee1_2_test.Activity.AdmineditCategory;
import com.example.ee1_2_test.Model.Category;
import com.example.ee1_2_test.R;

import java.util.ArrayList;

public class adminViewAllCategoriesAdapter extends RecyclerView.Adapter<adminViewAllCategoriesAdapter.ViewHolder> {

    private ArrayList<Category> categories = new ArrayList<>();
    private Context context;

    public adminViewAllCategoriesAdapter(ArrayList<Category> categories, Context context) {
        this.categories = categories;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(context);
        view = mInflater.inflate(R.layout.category_row, parent, false);
        return new adminViewAllCategoriesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.category_id.setText(categories.get(position).getCategoryId().toString());
            holder.category_name.setText(categories.get(position).getCategoryName());

            holder.editbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, AdmineditCategory.class);

                    intent.putExtra("ID", categories.get(position).getCategoryId());
                    intent.putExtra("Category_name", categories.get(position).getCategoryName());

                    context.startActivity(intent);
                }
            });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView category_id, category_name;
        Button editbtn, deletebtn;
        ConstraintLayout mainlayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            category_id = itemView.findViewById(R.id.category_categoryId_admin);
            category_name = itemView.findViewById(R.id.category_categoryName_admin);

            editbtn = itemView.findViewById(R.id.category_editBtn_admin);
            deletebtn = itemView.findViewById(R.id.category_deleteBtn_admin);

            mainlayout = itemView.findViewById(R.id.mainlayout_category);

        }
    }
}
