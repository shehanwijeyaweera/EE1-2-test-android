package com.example.ee1_2_test.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ee1_2_test.Activity.orderItems_admin;
import com.example.ee1_2_test.Model.CustomerOrderItem;
import com.example.ee1_2_test.Model.Customer_orders;
import com.example.ee1_2_test.R;

import java.io.Serializable;
import java.util.ArrayList;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.ViewHolder> {

    private ArrayList<Customer_orders> customer_orders = new ArrayList<>();
    private ArrayList<CustomerOrderItem> customerOrderItems = new ArrayList<>();
    private Context context;

    public OrdersAdapter(Context context, ArrayList<Customer_orders> customer_orders){
        this.customer_orders = customer_orders;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
              String stringInteger = Integer.toString(customer_orders.get(position).getId());
            holder.tvOrderId.setText(stringInteger);
            holder.tvDatePurchased.setText(customer_orders.get(position).getDatecreation());
            holder.tvStatus.setText(customer_orders.get(position).getStatus());
            holder.tvShippingStatus.setText(customer_orders.get(position).getName());
            holder.tvFirstName.setText(customer_orders.get(position).getUser().getUserFName());
            holder.tvLastName.setText(customer_orders.get(position).getUser().getUserLName());


            holder.viewOrderbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    customerOrderItems = new ArrayList<>(customer_orders.get(position).getCustomerOrderItems());
                    String testDebugpoint = "Test";
                    Intent intent = new Intent(context, orderItems_admin.class);
                    intent.putExtra("custorderitems", customerOrderItems);
                    context.startActivity(intent);
                }
            });
    }

    @Override
    public int getItemCount() {
        return customer_orders.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvOrderId;
        private TextView tvDatePurchased;
        private TextView tvStatus;
        private TextView tvShippingStatus;
        private TextView tvFirstName;
        private TextView tvLastName;
        private Button viewOrderbtn;
        private ConstraintLayout mainLayout_admin;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvOrderId = itemView.findViewById(R.id.Orders_id_admin);
            tvDatePurchased = itemView.findViewById(R.id.orders_datepurchased_admin);
            tvStatus = itemView.findViewById(R.id.orders_status_admin);
            tvShippingStatus = itemView.findViewById(R.id.orders_shippingstatus_admin);
            tvFirstName = itemView.findViewById(R.id.orders_custFName_admin);
            tvLastName = itemView.findViewById(R.id.orders_custLName_admin);
            viewOrderbtn = itemView.findViewById(R.id.orders_viewOrder_adminbtn);

            mainLayout_admin = itemView.findViewById(R.id.orders_mainLayout);

        }
    }
}
