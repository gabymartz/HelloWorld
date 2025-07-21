package com.example.helloworld;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.time.LocalDate;

public class MainActivity extends AppCompatActivity {

    public static final String USERNAME = "loginUsername";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView welcomeTextView = findViewById(R.id.welcome);
        String welcomeMessage = welcomeTextView.getText().toString();
        Bundle extras = getIntent().getExtras();

        String username = extras.getString(USERNAME);
        welcomeMessage = welcomeMessage + " , " + username;
        welcomeTextView.setText(welcomeMessage);

        EditText myEdit = findViewById(R.id.myEdit);
        EditText myName = findViewById(R.id.myName);
        EditText mySemester = findViewById(R.id.mySemester);
        EditText myLastname = findViewById(R.id.myLastname);
        EditText myAge = findViewById(R.id.myAge);
        Button myButton = findViewById(R.id.myButton);
        Button clearButton = findViewById(R.id.clearButton);
        TextView myTextView = findViewById(R.id.text);

        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String degree = myEdit.getText().toString();
                String name = myName.getText().toString();
                String lastname = myLastname.getText().toString();
                String semester = mySemester.getText().toString();
                String ageString = myAge.getText().toString();

                Integer semesterInteger = Integer.parseInt(semester);
                Integer ageInteger = Integer.parseInt(ageString);
                Integer agePlusOne = ageInteger + 1;

                myTextView.setText(buildStudentData(name, lastname, ageString, degree, semester));
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myEdit.setText("");
                myName.setText("");
                mySemester.setText("");
                myLastname.setText("");
                myAge.setText("");
                myTextView.setText("Student Data UPS");
            }
        });
    }

    public String buildStudentData(String name, String lastname, String ageString, String degree, String semesterString) {
        return "Student Data UPS\n\n" +
                "Name: " + name + "\n" +
                "LastName: " + lastname + "\n" +
                "Age: " + ageString + "\n" +
                "Degree: " + degree + "\n" +
                "BirthYear: " + calculateBirthYear(ageString) + "\n" +
                "Year Enrollment: " + calculateYearEnrollment(semesterString);
    }

    public String calculateBirthYear(String ageString) {
        LocalDate currentDate = LocalDate.now();
        int year = currentDate.getYear();
        int age = Integer.parseInt(ageString);
        int birthYear = year - age;
        return String.valueOf(birthYear);
    }

    public String calculateYearEnrollment(String semesterString) {
        LocalDate currentDate = LocalDate.now();
        int year = currentDate.getYear();
        int semester = Integer.parseInt(semesterString);
        int enrollmentYear = year - (semester / 2);
        return String.valueOf(enrollmentYear);
    }
}
