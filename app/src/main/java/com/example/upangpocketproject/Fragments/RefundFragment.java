package com.example.upangpocketproject.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.upangpocketproject.Classes.PaymentDataClass;
import com.example.upangpocketproject.Classes.TuitionDataClass;
import com.example.upangpocketproject.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class RefundFragment extends Fragment {
    private double totalTTfee = 0;
    public RefundFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_refund, container, false);



        Intent intent = getActivity().getIntent();
        String studentID = intent.getStringExtra("studentID");

        TextView txtRefund, txtTotalTuitionFee, txtTotalPaidFee, txtBalanceFee;

        txtRefund = view.findViewById(R.id.txtRefund);
        txtTotalTuitionFee = view.findViewById(R.id.txtTotalTuitionFee);
        txtTotalPaidFee = view.findViewById(R.id.txtTotalPaidFee);
        txtBalanceFee = view.findViewById(R.id.txtBalanceFee);

        //display total tuition and fees
        DatabaseReference feeRef = FirebaseDatabase.getInstance().getReference("Course/BSIT");

        feeRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                TuitionDataClass tuitionDataClass = snapshot.getValue(TuitionDataClass.class);

                totalTTfee = tuitionDataClass.getCourseTFee() + tuitionDataClass.getCourseMFee() +
                        tuitionDataClass.getCourseLabFee() + tuitionDataClass.getCourseOtherFee();

                txtTotalTuitionFee.setText("\u20B1"+String.format("%.2f", totalTTfee));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //get the overall payment
        DatabaseReference paymentRef = FirebaseDatabase.getInstance().getReference("Payments");

        paymentRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                double overallPayment=0.0;
                for(DataSnapshot childSnapshot : snapshot.getChildren()){
                    PaymentDataClass paymentDataClass = childSnapshot.getValue(PaymentDataClass.class);

                    if(paymentDataClass.getStudentID().equals(studentID)){
                        overallPayment += paymentDataClass.getPaymentAmount();
                    }
                }
                txtTotalPaidFee.setText("\u20B1"+String.format("%.2f", overallPayment));

                //display Balance and Refund
                double totalTuitionandFees = totalTTfee;

                if(overallPayment > totalTuitionandFees){
                    //display Refund
                    double refund = overallPayment - totalTuitionandFees;
                    txtRefund.setText("\u20B1"+String.format("%.2f", refund));
                }else{
                    txtRefund.setText("\u20B1"+String.format("%.1f", 0.0));
                }

                if(overallPayment < totalTuitionandFees){
                    //display Balance
                    double balanceFee = totalTuitionandFees - overallPayment;
                    txtBalanceFee.setText("\u20B1"+String.format("%.2f", balanceFee));
                }else{
                    txtBalanceFee.setText("\u20B1"+String.format("%.1f", 0.0));
                }

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        return view;
    }
}