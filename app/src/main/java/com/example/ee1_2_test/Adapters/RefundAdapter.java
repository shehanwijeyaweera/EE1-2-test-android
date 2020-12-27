package com.example.ee1_2_test.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ee1_2_test.Model.Refund;
import com.example.ee1_2_test.R;

import java.util.ArrayList;

public class RefundAdapter extends RecyclerView.Adapter<RefundAdapter.ViewHolder> {
    private ArrayList<Refund> refunds = new ArrayList<>();
    private Context context;

    public RefundAdapter(ArrayList<Refund> refunds, Context context) {
        this.refunds = refunds;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.refund_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String stringInteger = Integer.toString(refunds.get(position).getRefundId());
        holder.refundReq_id.setText(stringInteger);
        holder.refundReq_date.setText(refunds.get(position).getReqDate());
        holder.refundReq_response.setText(refunds.get(position).getRespond());
        holder.refundReq_reason.setText(refunds.get(position).getReason());
    }

    @Override
    public int getItemCount() {
        return refunds.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView refundReq_id;
        private TextView refundReq_date;
        private TextView refundReq_response;
        private TextView refundReq_reason;
        private Button refundReq_viewbtn;
        private ConstraintLayout mainLayout_user;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            refundReq_id = itemView.findViewById(R.id.refund_id_refundReq_user);
            refundReq_date = itemView.findViewById(R.id.refund_Date_refundReq_user);
            refundReq_response = itemView.findViewById(R.id.refund_response_refundReq_user);
            refundReq_reason = itemView.findViewById(R.id.refund_reason_refundReq_user);
            refundReq_viewbtn = itemView.findViewById(R.id.viewbtn_refundReq_user);

            mainLayout_user = itemView.findViewById(R.id.refund_mainLayout);
        }
    }
}
