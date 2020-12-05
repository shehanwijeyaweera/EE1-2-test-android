package com.example.ee1_2_test.Dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.ee1_2_test.R;

public class AddToCartDialog extends AppCompatDialogFragment {

    private EditText quantity;
    private AddToCartListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.layout_addtocart, null);

        builder.setView(view).setTitle("Quantity").setNegativeButton("Claer", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        })
                .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String quantitystrig = quantity.getText().toString();

                        listener.applyQuantity(quantitystrig);
                    }
                });

        quantity = view.findViewById(R.id.quantity_addtocart_user);

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (AddToCartListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()+"must implement AddToCartDialogListener");
        }
    }

    public interface AddToCartListener{
        void applyQuantity(String quantity);
    }
}
