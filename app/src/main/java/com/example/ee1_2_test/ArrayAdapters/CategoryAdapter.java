package com.example.ee1_2_test.ArrayAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ee1_2_test.Model.Category;
import com.example.ee1_2_test.R;

import java.util.ArrayList;

public class CategoryAdapter extends ArrayAdapter<Category> {

    public CategoryAdapter(Context context, ArrayList<Category> categoryList){
        super(context, 0, categoryList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return  initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return  initView(position, convertView, parent);
    }

    private View initView(int position, View convertView, ViewGroup parent){
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.category_spinner_row, parent, false);
        }

        TextView categoryID = convertView.findViewById(R.id.categoryspinner_categoryId);
        TextView categoryName = convertView.findViewById(R.id.categoryspinner_categoryName);

        Category currentcategory = getItem(position);

        if(currentcategory !=null) {
            categoryID.setText(currentcategory.getCategoryId().toString());
            categoryName.setText(currentcategory.getCategoryName());
        }

        return convertView;
    }
}
