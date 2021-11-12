package com.example.upangpocketproject.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.upangpocketproject.Activities.CardPaymentActivity;
import com.example.upangpocketproject.Activities.ChangePINActivity;
import com.example.upangpocketproject.Activities.LogInActivity;
import com.example.upangpocketproject.R;
import com.example.upangpocketproject.Classes.StudentDataClass;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AccountFragment extends Fragment {

    DatabaseReference studentRef;

    public AccountFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        Intent intent = getActivity().getIntent();
        String studentID = intent.getStringExtra("studentID");

        TextView txtWalletBalance, txtStudentName, txtStudentID, txtStudentCourse;

        txtWalletBalance = view.findViewById(R.id.txtWalletBalance);
        txtStudentName = view.findViewById(R.id.txtStudentName);
        txtStudentID = view.findViewById(R.id.txtStudentID);
        txtStudentCourse = view.findViewById(R.id.txtStudentCourse);

        Button btnTopup, btnChangePin;

        btnTopup = view.findViewById(R.id.btnTopup);
        btnChangePin = view.findViewById(R.id.btnChangePin);

        studentRef = FirebaseDatabase.getInstance().getReference("tblStudents/"+studentID);

        studentRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                StudentDataClass studentDataClass = snapshot.getValue(StudentDataClass.class);

                txtWalletBalance.setText("\u20B1"+String.format("%.2f", studentDataClass.getPocketBalance()));
                txtStudentName.setText(studentDataClass.getStudentLName() + ", " +
                        studentDataClass.getStudentFName() + " " + studentDataClass.getStudentMI());
                txtStudentID.setText(studentDataClass.getStudentID());
                txtStudentCourse.setText(studentDataClass.getStudentCourse());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btnTopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), CardPaymentActivity.class);
                intent.putExtra("studentID", studentID);
                intent.putExtra("pocketBalance", txtWalletBalance.getText().toString().split("\u20B1")[1]);
                startActivity(intent);
            }
        });

        btnChangePin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ChangePINActivity.class);
                intent.putExtra("studentID", studentID);
                startActivity(intent);
            }
        });

        return view;
    }
}