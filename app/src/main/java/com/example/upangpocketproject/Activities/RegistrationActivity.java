package com.example.upangpocketproject.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import org.jetbrains.annotations.NotNull;

public class RegistrationActivity extends AppCompatActivity {

    private DatabaseReference studentAccRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        EditText edittxtStudentID = findViewById(R.id.edittxtStudentID);
        EditText edittxtPassword = findViewById(R.id.edittxtPassword);
        EditText edittxtFName = findViewById(R.id.edittxtFName);
        EditText edittxtLName = findViewById(R.id.edittxtLName);
        EditText edittxtMI = findViewById(R.id.edittxtMI);

        Button btnSubmit = findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String studentID, studentPassword, studentFName, studentLName, studentMI;

                studentID = edittxtStudentID.getText().toString().trim();
                studentPassword = edittxtPassword.getText().toString().trim();
                studentFName = edittxtFName.getText().toString().trim();
                studentLName = edittxtLName.getText().toString().trim();
                studentMI = edittxtMI.getText().toString().trim();

                if(studentID.isEmpty()){
                    edittxtStudentID.setError("This field is Required");
                }
                else if(studentPassword.isEmpty()){
                    edittxtPassword.setError("This field is Required");
                }
                else if(studentFName.isEmpty()){
                    edittxtFName.setError("This field is Required");
                }
                else if(studentLName.isEmpty()){
                    edittxtLName.setError("This field is Required");
                }
                else{
                    //make database reference
                    studentAccRef = FirebaseDatabase.getInstance().getReference("tblStudents");

                    studentAccRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                            if(snapshot.hasChild(studentID)){
                                Toast.makeText(getApplicationContext(), "User Already Exists", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                StudentDataClass studentDataClass = new StudentDataClass(studentID, studentPassword, studentFName,
                                        studentLName, studentMI, "00000", "BSIT", 0);

                                studentAccRef.child(studentID).setValue(studentDataClass);

                                Toast.makeText(getApplicationContext(), "Account Created!", Toast.LENGTH_SHORT).show();
                                finish();
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