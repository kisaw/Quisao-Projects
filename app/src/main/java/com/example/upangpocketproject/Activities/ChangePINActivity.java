package com.example.upangpocketproject.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.upangpocketproject.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class ChangePINActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pinactivity);

        Intent intent = getIntent();
        String studentID = intent.getStringExtra("studentID");

        EditText edittxtCurrentPIN = findViewById(R.id.edittxtCurrentPIN);
        EditText edittxtNewPIN = findViewById(R.id.edittxtNewPIN);
        EditText edittxtConfirmPIN = findViewById(R.id.edittxtConfirmPIN);

        Button btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference studentPocketPINRef = FirebaseDatabase.getInstance().getReference("tblStudents/"
                        +studentID+"/studentPocketPassword");

                String currentPIN = edittxtCurrentPIN.getText().toString();
                String newPIN = edittxtNewPIN.getText().toString();
                String confirmPIN = edittxtConfirmPIN.getText().toString();

                if(currentPIN.isEmpty() || newPIN.isEmpty() || confirmPIN.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please fill all the fields", Toast.LENGTH_SHORT).show();
                }
                else{

                    studentPocketPINRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                            if(!currentPIN.equals(snapshot.getValue().toString())){
                                Toast.makeText(getApplicationContext(), "Wrong Current PIN", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                if(newPIN.length() != 5 || confirmPIN.length() != 5){
                                    Toast.makeText(getApplicationContext(), "PIN must be 5 numbers", Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    if(!newPIN.equals(confirmPIN)){
                                        Toast.makeText(getApplicationContext(), "PIN doesn't match", Toast.LENGTH_SHORT).show();
                                    }
                                    else{
                                        studentPocketPINRef.setValue(newPIN);
                                        Toast.makeText(getApplicationContext(), "PIN Changed!", Toast.LENGTH_SHORT).show();
                                        finish();
                                    }
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull @NotNull DatabaseError error) {

                        }
                    });

                }
            }
        });

    }
}