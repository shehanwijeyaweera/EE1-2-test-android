package com.example.ee1_2_test.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ee1_2_test.Model.CustomerOrderItem;
import com.example.ee1_2_test.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class OrderItemAdapter extends RecyclerView.Adapter<OrderItemAdapter.ViewHolder> {

    private ArrayList<CustomerOrderItem> customerOrderItems = new ArrayList<>();
    private Context context;

    public OrderItemAdapter(ArrayList<CustomerOrderItem> customerOrderItems, Context context) {
        this.customerOrderItems = customerOrderItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.orderitem_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.orderitemId.setText("Order Item ID: "+Integer.toString(customerOrderItems.get(position).getId()));
        holder.orderitemQuantity.setText("Quantity: "+Integer.toString(customerOrderItems.get(position).getQuantity()));
        holder.booknameOrderitem.setText(customerOrderItems.get(position).getBook().getTitle());
        Picasso.get().load(customerOrderItems.get(position).getBook().getLogoImagepathApi()).resize(300,400).into(holder.orderItembookImage);
    }

    @Override
    public int getItemCount() {
        return customerOrderItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView orderitemId, orderitemQuantity, booknameOrderitem;
        ImageView orderItembookImage;
        ConstraintLayout orderItemMainLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            orderitemId = itemView.findViewById(R.id.orderItem_id_admin);
            orderitemQuantity = itemView.findViewById(R.id.Quantity_orderitem_admin);
            orderItembookImage = itemView.findViewById(R.id.orderItem_bookImage_admin);
            orderItemMainLayout = itemView.findViewById(R.id.orderItem_mainLayout);
            booknameOrderitem = itemView.findViewById(R.id.orderitem_bookname_admin);
        }
    }
}
