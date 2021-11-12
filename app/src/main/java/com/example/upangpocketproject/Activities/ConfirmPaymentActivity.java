package com.example.upangpocketproject.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import com.example.upangpocketproject.Classes.PaymentDataClass;
import com.example.upangpocketproject.R;
import com.example.upangpocketproject.Classes.StudentDataClass;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ConfirmPaymentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_payment);

        Intent intent = getIntent();
        String studentID = intent.getStringExtra("studentID");
        String paymentPeriod = intent.getStringExtra("paymentPeriod");
        double paymentAmount = Double.parseDouble(intent.getStringExtra("paymentAmount").split("\u20B1")[1]);

        EditText edittxtNum1, edittxtNum2, edittxtNum3, edittxtNum4, edittxtNum5;

        edittxtNum1 = findViewById(R.id.edittxtNum1);
        edittxtNum2 = findViewById(R.id.edittxtNum2);
        edittxtNum3 = findViewById(R.id.edittxtNum3);
        edittxtNum4 = findViewById(R.id.edittxtNum4);
        edittxtNum5 = findViewById(R.id.edittxtNum5);

        edittxtNum1.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start,int before, int count)
            {
                // TODO Auto-generated method stub
                if(edittxtNum1.getText().toString().length()==1)     //size as per your requirement
                {
                    edittxtNum2.requestFocus();
                }
            }
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
                // TODO Auto-generated method stub

            }

            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }

        });

        edittxtNum2.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start,int before, int count)
            {
                // TODO Auto-generated method stub
                if(edittxtNum2.getText().toString().length()==1)     //size as per your requirement
                {
                    edittxtNum3.requestFocus();
                }
                else{
                    edittxtNum1.requestFocus();
                }
            }
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
                // TODO Auto-generated method stub

            }

            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }

        });

        edittxtNum3.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start,int before, int count)
            {
                // TODO Auto-generated method stub
                if(edittxtNum3.getText().toString().length()==1)     //size as per your requirement
                {
                    edittxtNum4.requestFocus();
                }
                else{
                    edittxtNum2.requestFocus();
                }
            }
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
                // TODO Auto-generated method stub

            }

            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }

        });

        edittxtNum4.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start,int before, int count)
            {
                // TODO Auto-generated method stub
                if(edittxtNum4.getText().toString().length()==1)     //size as per your requirement
                {
                    edittxtNum5.requestFocus();
                }
                else{
                    edittxtNum3.requestFocus();
                }
            }
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
                // TODO Auto-generated method stub

            }

            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }

        });

        edittxtNum5.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start,int before, int count)
            {
                // TODO Auto-generated method stub
                if(edittxtNum5.getText().toString().length()==1)     //size as per your requirement
                {
                    DatabaseReference paymentRef = FirebaseDatabase.getInstance().getReference("Payments");
                    DatabaseReference studentRef = FirebaseDatabase.getInstance().getReference("tblStudents/"+studentID);

                    studentRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

                            StudentDataClass studentDataClass = snapshot.getValue(StudentDataClass.class);

                            String inputPin = edittxtNum1.getText().toString() + edittxtNum2.getText().toString() +
                                    edittxtNum3.getText().toString() + edittxtNum4.getText().toString() +
                                    edittxtNum5.getText().toString();

                            if(studentDataClass.getStudentPocketPassword().equals(inputPin)){
                                double currentBalance = studentDataClass.getPocketBalance();

                                if(currentBalance < paymentAmount){
                                    Toast.makeText(getApplicationContext(), "Insufficient Balance", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                                else{
                                    Date c = Calendar.getInstance().getTime();

                                    SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
                                    String dateToday = df.format(c);

                                    PaymentDataClass paymentDataClass = new PaymentDataClass(studentID+paymentPeriod,
                                            studentID, paymentPeriod, dateToday, paymentAmount);

                                    paymentRef.child(studentID+paymentPeriod).setValue(paymentDataClass);

                                    studentRef.child("pocketBalance").setValue(currentBalance - paymentAmount);

                                    Toast.makeText(getApplicationContext(), "Transaction Completed!", Toast.LENGTH_SHORT).show();
                                    finish();

                                }

                            }else{
                                Toast.makeText(getApplicationContext(), "Invalid PIN", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull @NotNull DatabaseError error) {

                        }
                    });

                }
            }
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
                // TODO Auto-generated method stub

            }

            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }

        });

    }
}