package com.example.upangpocketproject.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.upangpocketproject.Classes.ReportDataClass;
import com.example.upangpocketproject.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.core.Repo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ReportIssueActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_issue);

        Intent intent = getIntent();
        String studentID = intent.getStringExtra("studentID");

        EditText edittxtIssue = findViewById(R.id.edittxtIssue);

        Button btnSubmit = findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference reportRef = FirebaseDatabase.getInstance().getReference("Reports");

                Date datetime = new Date();

                String datetimeNow = datetime.toString();

                String message = edittxtIssue.getText().toString().trim();

                ReportDataClass reportDataClass = new ReportDataClass(studentID+datetimeNow, studentID,
                        message, datetimeNow);

                reportRef.child(studentID+datetimeNow).setValue(reportDataClass);

                Toast.makeText(getApplicationContext(), "Message Sent!", Toast.LENGTH_SHORT).show();
                finish();

            }
        });
    }
}