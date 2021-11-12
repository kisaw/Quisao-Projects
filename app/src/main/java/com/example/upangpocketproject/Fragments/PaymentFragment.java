package com.example.upangpocketproject.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.upangpocketproject.Activities.ConfirmPaymentActivity;
import com.example.upangpocketproject.Adapters.PaymentHistoryTuitionAdapter;
import com.example.upangpocketproject.Classes.PaymentDataClass;
import com.example.upangpocketproject.R;
import com.example.upangpocketproject.Classes.TuitionDataClass;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class PaymentFragment extends Fragment {

    DatabaseReference feeRef, paymentRef;

    public PaymentFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_payment, container, false);

        RecyclerView historyTuitionRecycler = view.findViewById(R.id.historyTuitionRecycler);

        Intent intent = getActivity().getIntent();
        String studentID = intent.getStringExtra("studentID");

        TextView txtTotalTFee, txtMFee, txtLabFee, txtOtherFee, txtTotalTuitionFee,
        txtP1Payment, txtP2Payment, txtFinalPayment;

        txtTotalTFee = view.findViewById(R.id.txtTotalTFee);
        txtMFee = view.findViewById(R.id.txtMFee);
        txtLabFee = view.findViewById(R.id.txtLabFee);
        txtOtherFee = view.findViewById(R.id.txtOtherFee);
        txtTotalTuitionFee = view.findViewById(R.id.txtTotalTuitionFee);
        txtP1Payment = view.findViewById(R.id.txtP1Payment);
        txtP2Payment = view.findViewById(R.id.txtP2Payment);
        txtFinalPayment = view.findViewById(R.id.txtFinalPayment);

        Button btnP1Pay, btnP2Pay, btnFinalPay;

        btnP1Pay = view.findViewById(R.id.btnP1Pay);
        btnP2Pay = view.findViewById(R.id.btnP2Pay);
        btnFinalPay = view.findViewById(R.id.btnFinalPay);

        //display fees
        feeRef = FirebaseDatabase.getInstance().getReference("Course/BSIT");

        feeRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                TuitionDataClass tuitionDataClass = snapshot.getValue(TuitionDataClass.class);

                txtTotalTFee.setText("\u20B1"+tuitionDataClass.getCourseTFee());
                txtMFee.setText("\u20B1"+tuitionDataClass.getCourseMFee());
                txtLabFee.setText("\u20B1"+tuitionDataClass.getCourseLabFee());
                txtOtherFee.setText("\u20B1"+tuitionDataClass.getCourseOtherFee());

                double totalTTfee = tuitionDataClass.getCourseTFee() + tuitionDataClass.getCourseMFee() +
                        tuitionDataClass.getCourseLabFee() + tuitionDataClass.getCourseOtherFee();

                txtTotalTuitionFee.setText("\u20B1"+totalTTfee);

                double P1Payment = (35.0 / 100.0) * totalTTfee;
                double P2Payment = (35.0 / 100.0) * totalTTfee;
                double FinalPayment = (30.0 / 100.0) * totalTTfee;

                txtP1Payment.setText("\u20B1"+String.format("%.2f", P1Payment));
                txtP2Payment.setText("\u20B1"+String.format("%.2f", P2Payment));
                txtFinalPayment.setText("\u20B1"+String.format("%.2f", FinalPayment));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //Check if paid or not
        paymentRef = FirebaseDatabase.getInstance().getReference("Payments");

        paymentRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                if(snapshot.hasChild(studentID+"P1")){
                    btnP1Pay.setEnabled(false);
                    btnP1Pay.setText("PAID");
                }
                else{
                    btnP1Pay.setEnabled(true);
                    btnP1Pay.setText("PAY NOW");
                }
                if(snapshot.hasChild(studentID+"P2")){
                    btnP2Pay.setEnabled(false);
                    btnP2Pay.setText("PAID");
                }
                else{
                    btnP2Pay.setEnabled(true);
                    btnP2Pay.setText("PAY NOW");
                }
                if(snapshot.hasChild(studentID+"Final")){
                    btnFinalPay.setEnabled(false);
                    btnFinalPay.setText("PAID");
                }
                else{
                    btnFinalPay.setEnabled(true);
                    btnFinalPay.setText("PAY NOW");
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        //buttons
        btnP1Pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getContext(), ConfirmPaymentActivity.class);
                intent.putExtra("studentID", studentID);
                intent.putExtra("paymentPeriod", "P1");
                intent.putExtra("paymentAmount", txtP1Payment.getText().toString());
                startActivity(intent);
            }
        });

        btnP2Pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getContext(), ConfirmPaymentActivity.class);
                intent.putExtra("studentID", studentID);
                intent.putExtra("paymentPeriod", "P2");
                intent.putExtra("paymentAmount", txtP2Payment.getText().toString());
                startActivity(intent);
            }
        });

        btnFinalPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getContext(), ConfirmPaymentActivity.class);
                intent.putExtra("studentID", studentID);
                intent.putExtra("paymentPeriod", "Final");
                intent.putExtra("paymentAmount", txtFinalPayment.getText().toString());
                startActivity(intent);
            }
        });

        //load payment history (tuition)
        List<PaymentDataClass> paymentList = new ArrayList<>();
        paymentRef = FirebaseDatabase.getInstance().getReference("Payments");

        paymentRef.orderByChild("paymentDate").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                paymentList.clear();
                for(DataSnapshot childSnapshot : snapshot.getChildren()){
                    PaymentDataClass paymentDataClass = childSnapshot.getValue(PaymentDataClass.class);

                    paymentList.add(new PaymentDataClass(paymentDataClass.getPaymentID(), paymentDataClass.getStudentID(),
                            paymentDataClass.getPaymentPeriod(), paymentDataClass.getPaymentDate(), paymentDataClass.getPaymentAmount()));

                }
                PaymentHistoryTuitionAdapter mAdapter = new PaymentHistoryTuitionAdapter(paymentList);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
                historyTuitionRecycler.setLayoutManager(mLayoutManager);
                historyTuitionRecycler.setItemAnimator(new DefaultItemAnimator());
                historyTuitionRecycler.setAdapter(mAdapter);

                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        return view;
    }
}