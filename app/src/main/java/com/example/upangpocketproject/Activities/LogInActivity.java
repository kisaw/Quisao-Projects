package com.example.upangpocketproject.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.upangpocketproject.R;
import com.example.upangpocketproject.Classes.StudentDataClass;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LogInActivity extends AppCompatActivity {

    private DatabaseReference studentAccRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        Button btnLogin = findViewById(R.id.btnLogin);
        Button btnRegister = findViewById(R.id.btnRegister);

        EditText edittxtStudentID = findViewById(R.id.edittxtStudentID);
        EditText edittxtPassword = findViewById(R.id.edittxtPassword);

//        TextView tv = findViewById(R.id.txtForgotPassword);
//        SpannableString content = new SpannableString("Forgot Password");
//        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
//        tv.setText(content);
//
//        tv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(), "TAE", Toast.LENGTH_SHORT).show();
//            }
//        });

        checkUser();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String studentID = edittxtStudentID.getText().toString().trim();
                String password = edittxtPassword.getText().toString().trim();

                studentAccRef = FirebaseDatabase.getInstance().getReference("tblStudents");

                studentAccRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot childSnapshot : snapshot.getChildren()){
                            StudentDataClass studentDataClass = childSnapshot.getValue(StudentDataClass.class);

                            if (studentID.equals(studentDataClass.getStudentID()) &&
                                    password.equals(studentDataClass.getStudentPassword())) {
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                intent.putExtra("studentID", studentID);

                                SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);

                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putInt("activeuser", 1);
                                editor.putString("studentID", studentID);
                                editor.commit();

                                startActivity(intent);
                                finish();
                            }
                            else{
                                Toast.makeText(getApplicationContext(), "Invalid Username or Password", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegistrationActivity.class);
                startActivity(intent);
            }
        });

    }

    public void checkUser(){
        SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);

        if(sharedPreferences.getInt("activeuser", 0) == 1){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            String studentID = sharedPreferences.getString("studentID", "");
            intent.putExtra("studentID", studentID);
            startActivity(intent);
            finish();
        }
    }
}