package com.example.ee1_2_test.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ee1_2_test.Activity.MainActivity;
import com.example.ee1_2_test.Activity.StoreworkerDashboard;
import com.example.ee1_2_test.Fragments.AddToCartFragmentUser;
import com.example.ee1_2_test.Model.CartItem;
import com.example.ee1_2_test.R;
import com.example.ee1_2_test.Sessions.SessionManagement;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.ViewHolder> {

    private ArrayList<CartItem> cartItems = new ArrayList<>();
    private Context context;
    private CartItemAdapter cartItemAdapter;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public CartItemAdapter(ArrayList<CartItem> cartItems, Context context) {
        this.cartItems = cartItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.cartitem_row,parent,false);
        return new ViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.booktitle.setText(cartItems.get(position).getBook().getTitle());
            holder.quantity.setText("Quantity: "+Integer.toString(cartItems.get(position).getQuantity()));
            double subtotal = cartItems.get(position).getBook().getPrice() * cartItems.get(position).getQuantity();
            holder.subtotal.setText("SubTotal: Rs."+Double.toString(subtotal)+"0");
            Picasso.get().load(cartItems.get(position).getBook().getLogoImagepathApi()).resize(300, 400).into(holder.bookImage);

//            holder.removeItem.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    SessionManagement sessionManagement = new SessionManagement(context);
//                    sessionManagement.removeCartItem(cartItems.get(position).getBook().getBookId());
//                    Toast.makeText(context, "Item Removed: "+cartItems.get(position).getBook().getTitle(), Toast.LENGTH_SHORT).show();
//                }
//            });
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView bookImage;
        private TextView booktitle, quantity, subtotal;
        private Button removeItem;
        private ConstraintLayout cartitemLayout;
        public ViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);

            bookImage = itemView.findViewById(R.id.bookimage_cartitem_user);
            booktitle = itemView.findViewById(R.id.booktitle_cartitem_user);
            quantity = itemView.findViewById(R.id.quantity_cartitem_user);
            subtotal = itemView.findViewById(R.id.Subtotal_cartitem_user);
            cartitemLayout = itemView.findViewById(R.id.cartItem_mainLayout);
            removeItem = itemView.findViewById(R.id.Removeitem_cartitem_user);

            removeItem.setOnClickListener(new View.OnClickListener() {
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
