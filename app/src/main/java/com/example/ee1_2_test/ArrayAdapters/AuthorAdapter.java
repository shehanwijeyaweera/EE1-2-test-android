package com.example.ee1_2_test.ArrayAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ee1_2_test.Model.Author;
import com.example.ee1_2_test.R;

import java.util.ArrayList;

public class AuthorAdapter extends ArrayAdapter<Author> {

    public AuthorAdapter(Context context, ArrayList<Author> authorList){
        super(context, 0, authorList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView, ViewGroup parent){
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.author_spinner_row, parent, false);
        }

        TextView authorID = convertView.findViewById(R.id.authorspinner_authorId);
        TextView authorName = convertView.findViewById(R.id.authorspinner_authroName);

        Author currentAuthor = getItem(position);

        if(currentAuthor !=null) {
            authorID.setText(currentAuthor.getAuthorId().toString());
            authorName.setText(currentAuthor.getAuthorName());
        }

        return convertView;
    }
}
