package com.example.ee1_2_test.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ee1_2_test.API.BookstoreApi;
import com.example.ee1_2_test.Adapters.CartItemAdapter;
import com.example.ee1_2_test.JSON.Checkout;
import com.example.ee1_2_test.Model.ApiClient;
import com.example.ee1_2_test.Model.CartItem;
import com.example.ee1_2_test.Model.Category;
import com.example.ee1_2_test.Model.User;
import com.example.ee1_2_test.Model.loginResponse;
import com.example.ee1_2_test.R;
import com.example.ee1_2_test.Sessions.SessionManagement;

import java.util.ArrayList;
import java.util.stream.DoubleStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddToCartFragmentUser extends Fragment {

    private RecyclerView cartitem_recyclerview_user;
    private CartItemAdapter cartItemAdapter;
    private TextView totalprice;
    private Button checkoutbtn;
    BookstoreApi bookstoreApi;

    SessionManagement sessionManagement;
    ArrayList<CartItem> cartItems = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Cart");

        View view = inflater.inflate(R.layout.fragment_add_to_cart,container,false);

        setRecyclerView(view);

        setData();

        return view;
    }

    private void setRecyclerView(View view) {
        checkoutbtn = view.findViewById(R.id.purchaseOrder_cartitem_user);
        cartitem_recyclerview_user = view.findViewById(R.id.cartItem_recyclerview_user);
        cartitem_recyclerview_user.setLayoutManager(new LinearLayoutManager(getContext()));
        totalprice = view.findViewById(R.id.cart_finalprice_user);
    }

    private void setData() {
        SessionManagement sessionManagement = new SessionManagement(getContext());
        cartItems = sessionManagement.getCartItems();


        double s = 0;
        if(cartItems != null) {
            for (CartItem cartItem : cartItems) {
                s += cartItem.getQuantity() * cartItem.getBook().getPrice().doubleValue();
            }

            totalprice.setText(Double.toString(s));

            cartItemAdapter = new CartItemAdapter(cartItems, getContext());
            cartitem_recyclerview_user.setAdapter(cartItemAdapter);

            cartItemAdapter.setOnItemClickListener(new CartItemAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    SessionManagement sessionManagement = new SessionManagement(getContext());
                    sessionManagement.removeCartItem(cartItems.get(position).getBook().getBookId());
                    removeItem(position);
                }
            });

            checkoutbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SessionManagement sessionManagement = new SessionManagement(getContext());
                    cartItems = sessionManagement.getCartItems();
                    User user = sessionManagement.getSession();

                    double total = 0;
                    for (CartItem cartItem : cartItems) {
                        total += cartItem.getQuantity() * cartItem.getBook().getPrice().doubleValue();
                    }


                    bookstoreApi = ApiClient.getClient().create(BookstoreApi.class);

                    Call<loginResponse> call = bookstoreApi.checkoutCart(user.getUsername(), total, cartItems);

                    call.enqueue(new Callback<loginResponse>() {
                        @Override
                        public void onResponse(Call<loginResponse> call, Response<loginResponse> response) {
                            loginResponse checkoutResponse = response.body();
                            if(checkoutResponse.getResponse().equalsIgnoreCase("Correct")){
                                Toast.makeText(getContext(), "Checkout Successful!", Toast.LENGTH_SHORT).show();
                                sessionManagement.clearCart();
                                removeAllCartItems();
                            }
                        }

                        @Override
                        public void onFailure(Call<loginResponse> call, Throwable t) {
                            Toast.makeText(getContext(), "Checkout Failed", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        }
    }

    public void removeItem(int position){
        cartItems.remove(position);
        cartItemAdapter.notifyItemRemoved(position);
        onResume();
    }

    public void clear() {

    }

    private void removeAllCartItems() {
        cartItems.clear();
        cartItemAdapter.notifyDataSetChanged();
        onResume();
    }

    @Override
    public void onResume() {
        super.onResume();
        double s = 0;
        if(cartItems != null){
            for (CartItem cartItem : cartItems) {
                s += cartItem.getQuantity() * cartItem.getBook().getPrice().doubleValue();
            }
            totalprice.setText(Double.toString(s));
        }
    }

    public void CheckoutFunction(){


    }
}
