package com.example.ee1_2_test.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.LinearLayout;

import com.example.ee1_2_test.Adapters.OrderItemAdapter;
import com.example.ee1_2_test.Model.CustomerOrderItem;
import com.example.ee1_2_test.Model.Customer_orders;
import com.example.ee1_2_test.R;

import java.util.ArrayList;

public class orderItems_admin extends AppCompatActivity {

    private RecyclerView orderitems_recyclerview_admin;
    ArrayList<CustomerOrderItem> customerOrderItems = new ArrayList<>();
    private OrderItemAdapter orderItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_items_admin);

        orderitems_recyclerview_admin = findViewById(R.id.orderitems_recyclerview_admin);
        orderitems_recyclerview_admin.setLayoutManager(new LinearLayoutManager(orderItems_admin.this));

        displayData();

    }

    private void displayData() {
        customerOrderItems = (ArrayList<CustomerOrderItem>) getIntent().getSerializableExtra("custorderitems");
        orderItemAdapter = new OrderItemAdapter(customerOrderItems, orderItems_admin.this);
        orderitems_recyclerview_admin.setAdapter(orderItemAdapter);
    }
}