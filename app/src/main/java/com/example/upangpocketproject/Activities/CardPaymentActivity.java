package com.example.upangpocketproject.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.upangpocketproject.CreditCardField.CreditCardNumberTextWatcher;
import com.example.upangpocketproject.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CardPaymentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_payment);

        Intent intent = getIntent();
        String studentID = intent.getStringExtra("studentID");
        String pocketBalance = intent.getStringExtra("pocketBalance");

        EditText edittxtCreditCardNumber = findViewById(R.id.edittxtCreditCardNumber);
        EditText edittxtExpiry = findViewById(R.id.edittxtExpiry);
        EditText edittxtCVV = findViewById(R.id.edittxtCVV);
        EditText edittxtAmount = findViewById(R.id.edittxtAmount);

        Button btnSubmit = findViewById(R.id.btnSubmit);

        TextWatcher textWatcher = new CreditCardNumberTextWatcher(edittxtCreditCardNumber);
        edittxtCreditCardNumber.addTextChangedListener(textWatcher);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edittxtCreditCardNumber.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Card Number is Required", Toast.LENGTH_SHORT).show();
                }
                else if(edittxtExpiry.getText().toString().isEmpty()){
                    edittxtExpiry.setError("This field is required");
                }
                else if(edittxtCVV.getText().toString().isEmpty()){
                    edittxtCVV.setError("This field is required");
                }
                else if(edittxtAmount.getText().toString().isEmpty()){
                    edittxtAmount.setError("This field is required");
                }
                else{

                    DatabaseReference studentPocketRef = FirebaseDatabase.getInstance().getReference("tblStudents/"+
                            studentID+"/pocketBalance");

                    if(Double.parseDouble(pocketBalance) > 0){
                        double oldBalance = Double.parseDouble(pocketBalance);
                        double newBalance = Double.parseDouble(edittxtAmount.getText().toString()) + oldBalance;
                        studentPocketRef.setValue(newBalance);
                        Toast.makeText(getApplicationContext(), "Transaction Complete", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    else{
                        double newBalance = Double.parseDouble(edittxtAmount.getText().toString());
                        studentPocketRef.setValue(newBalance);
                        Toast.makeText(getApplicationContext(), "Transaction Complete", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                }
            }
        });
    }
}