package com.example.upangpocketproject.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.upangpocketproject.Classes.PaymentDataClass;
import com.example.upangpocketproject.Fragments.PaymentFragment;
import com.example.upangpocketproject.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PaymentHistoryTuitionAdapter extends RecyclerView.Adapter<PaymentHistoryTuitionAdapter.MyViewHolder> {
    private List<PaymentDataClass> paymentList;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtpaymentPeriod, txtpaymentAmount, txtpaymentDate;
        public MyViewHolder(View view) {
            super(view);
            txtpaymentPeriod = view.findViewById(R.id.txtpaymentPeriod);
            txtpaymentAmount = view.findViewById(R.id.txtpaymentAmount);
            txtpaymentDate = view.findViewById(R.id.txtpaymentDate);
            context = view.getContext();
        }
    }

    public PaymentHistoryTuitionAdapter(List<PaymentDataClass> paymentList) {
        this.paymentList = paymentList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.payment_history_tuition_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        PaymentFragment paymentFragment = new PaymentFragment();
        PaymentDataClass payment = paymentList.get(position);
        holder.txtpaymentPeriod.setText(payment.getPaymentPeriod());
        holder.txtpaymentAmount.setText("\u20B1"+payment.getPaymentAmount());
        holder.txtpaymentDate.setText(payment.getPaymentDate());
    }

    @Override
    public int getItemCount() {
        return paymentList.size();
    }
}
