package com.example.ee1_2_test.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ee1_2_test.Model.RequestBook;
import com.example.ee1_2_test.Model.RequestBook2;
import com.example.ee1_2_test.R;

import java.util.ArrayList;

public class adminViewAllRequestAdapter extends RecyclerView.Adapter<adminViewAllRequestAdapter.ViewHolder> {
    private ArrayList<RequestBook2> requestBooks = new ArrayList<>();
    private Context context;

    public adminViewAllRequestAdapter(ArrayList<RequestBook2> requestBooks, Context context) {
        this.requestBooks = requestBooks;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(context);
        view = mInflater.inflate(R.layout.request_row_admin, parent, false);
        return new adminViewAllRequestAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.requestID.setText("ID:"+requestBooks.get(position).getRequestId().toString());
        holder.requestDate.setText("Date:"+requestBooks.get(position).getReqDate().toString());
        holder.bookName.setText("Book Name: "+requestBooks.get(position).getBookName());
        holder.Quantity.setText("Quantity: "+requestBooks.get(position).getQuantity().toString());
    }

    @Override
    public int getItemCount() {
        return requestBooks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView requestID, requestDate, userFname, userLName, bookName, Quantity;
        private ConstraintLayout mainLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            requestID = itemView.findViewById(R.id.Requestbook_requestID_admin);
            requestDate = itemView.findViewById(R.id.requestBook_requestDate_admin);
            bookName = itemView.findViewById(R.id.requestBook_bookName_admin);
            Quantity = itemView.findViewById(R.id.requestBook_quantity_admin);

            mainLayout = itemView.findViewById(R.id.requestBook_mainLayout);
        }
    }
}
