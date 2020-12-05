package com.example.ee1_2_test.Sessions;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.ee1_2_test.Model.Book;
import com.example.ee1_2_test.Model.CartItem;
import com.example.ee1_2_test.Model.Role;
import com.example.ee1_2_test.Model.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SessionManagement {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String SHARED_PREF_NAME = "session";
    String SESSION_KEY = "session_user";
    String SESSION_ROLE = "session_role";
    String SESSION_CARTITEM = "session_cart";
    private ArrayList<CartItem> cartItems = new ArrayList<>();

    public SessionManagement(Context context){
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

    }

    public void saveSession(User user, List<Role> role){
        editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json_user = gson.toJson(user);
        String json_role = role.get(0).getName();
        editor.putString(SESSION_KEY, json_user);
        editor.putString(SESSION_ROLE, json_role);
        editor.commit();
    }

    public User getSession(){

        Gson gson = new Gson();
        String json = sharedPreferences.getString(SESSION_KEY, "");
        User user = gson.fromJson(json, User.class);

        return user;
    }

    public void removeSession(){

        editor.putString(SESSION_KEY, null).commit();
        editor.putString(SESSION_ROLE, null).commit();
    }

    public String getRole(){
        String role = sharedPreferences.getString(SESSION_ROLE, "");

        return role;
    }

    public void addToCart(Book book, int quantity){
        String cartJson = sharedPreferences.getString(SESSION_CARTITEM, "");
        if(cartJson ==""){
            cartItems.add(new CartItem(book, quantity));
            editor = sharedPreferences.edit();
            Gson gson = new Gson();

            String Cartjson = gson.toJson(cartItems);

            editor.putString(SESSION_CARTITEM, Cartjson);
        }else {
            Gson gson = new Gson();
            Type type = new TypeToken<List<CartItem>>(){}.getType();
            List<CartItem> cartItemList = gson.fromJson(cartJson, type);

            int index = isExists(book.getBookId(), cartItemList);
            if(index == -1){
                cartItemList.add(new CartItem(book, quantity));
            }
            else {
                int quantityNew = cartItemList.get(index).getQuantity() + quantity;
                cartItemList.get(index).setQuantity(quantityNew);
            }

            editor = sharedPreferences.edit();
            String Cartjson = gson.toJson(cartItemList);
            editor.putString(SESSION_CARTITEM, Cartjson);

        } editor.commit();

    }

    public ArrayList<CartItem> getCartItems(){

        Gson gson = new Gson();
        String cartJson = sharedPreferences.getString(SESSION_CARTITEM, "");
        Type type = new TypeToken<List<CartItem>>(){}.getType();
        List<CartItem> cartItemList = gson.fromJson(cartJson, type);

        return (ArrayList<CartItem>) cartItemList;
    }

    public void clearCart(){
        editor.putString(SESSION_CARTITEM, null).commit();
    }

    private int isExists(int bookId, List<CartItem> cartItems){
        for(int i = 0; i< cartItems.size(); i++){
            if(cartItems.get(i).getBook().getBookId() == bookId){
                return i;
            }
        }
        return -1;
    }

    public void removeCartItem(int bookid){
        List<CartItem> cartItems = getCartItems();
        int index = isExists(bookid, cartItems);
        cartItems.remove(index);

        Gson gson = new Gson();

        editor = sharedPreferences.edit();
        String Cartjson = gson.toJson(cartItems);
        editor.putString(SESSION_CARTITEM, Cartjson);
        editor.commit();
    }
}
