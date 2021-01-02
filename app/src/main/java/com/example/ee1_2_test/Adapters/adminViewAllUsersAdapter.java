package com.example.ee1_2_test.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ee1_2_test.Activity.AdmineditAuthor;
import com.example.ee1_2_test.Model.AdminUserView;
import com.example.ee1_2_test.Model.UserRole;
import com.example.ee1_2_test.R;

import java.util.ArrayList;
import java.util.List;

public class adminViewAllUsersAdapter extends RecyclerView.Adapter<adminViewAllUsersAdapter.ViewHolder> {

    private ArrayList<AdminUserView> userViews = new ArrayList<>();
    private ArrayList<UserRole> userRoles = new ArrayList<>();
    private Context context;
    private OnItemClickListener mListener;

    public adminViewAllUsersAdapter(ArrayList<AdminUserView> userViews, Context context) {
        this.userViews = userViews;
        this.context = context;
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(context);
        view = mInflater.inflate(R.layout.user_row_admin, parent, false);
        return new ViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.fistName.setText(userViews.get(position).getUserFName());
            holder.lastName.setText(userViews.get(position).getUserLName());
            holder.userEmail.setText(userViews.get(position).getUserEmail());
            holder.Username.setText(userViews.get(position).getUsername());
            holder.PhoneNo.setText(userViews.get(position).getUserPhoneNo().toString());
            List<UserRole> userRoles = userViews.get(position).getUserRole();
            holder.role.setText(userRoles.get(userRoles.size()-1).getName());

            holder.editbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  //  Intent intent = new Intent(context, AdminEditUser.class);
                }
            });
    }

    @Override
    public int getItemCount() {
        return userViews.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView fistName, lastName, userEmail, Username, PhoneNo, role;
        private Button editbtn, deletebtn;
        ConstraintLayout mainLayout_admin;
        public ViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            fistName = itemView.findViewById(R.id.user_firstName_admin);
            lastName = itemView.findViewById(R.id.user_LastName_admin);
            userEmail = itemView.findViewById(R.id.user_email_admin);
            Username = itemView.findViewById(R.id.user_username_admin);
            PhoneNo = itemView.findViewById(R.id.user_phoneNo_admin);
            role = itemView.findViewById(R.id.user_role_admin);

            deletebtn=itemView.findViewById(R.id.user_deleteBtn_admin);
            editbtn=itemView.findViewById(R.id.user_editbtn_admin);

            deletebtn.setOnClickListener(new View.OnClickListener() {
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
