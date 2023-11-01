package com.example.studentportal;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    EditText name;
    EditText email;
    EditText phone;
    EditText sapid;
    EditText password;
    Button signup;
    Button altlogin;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        sapid = findViewById(R.id.sapid);
        password = findViewById(R.id.password);
        signup = findViewById(R.id.signup);
        altlogin = findViewById(R.id.altlogin);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inputValidation()) {
                    String userName = name.getText().toString().trim();
                    String userEmail = email.getText().toString().trim();
                    String userPhone = phone.getText().toString().trim();
                    String userSapid = sapid.getText().toString().trim();
                    String userPassword = password.getText().toString().trim();

                    DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child("users");
                    DatabaseReference currentUserRef = usersRef.child(userSapid);

                    currentUserRef.child("name").setValue(userName);
                    currentUserRef.child("email").setValue(userEmail);
                    currentUserRef.child("phone").setValue(userPhone);
                    currentUserRef.child("sapid").setValue(userSapid);
                    currentUserRef.child("password").setValue(userPassword);

                    Toast.makeText(MainActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, com.example.studentportal.login.class);
                    startActivity(intent);
                    name.getText().clear();
                    email.getText().clear();
                    phone.getText().clear();
                    sapid.getText().clear();
                    password.getText().clear();
                }
            }
        });

        altlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, login.class);
                startActivity(intent);
            }
        });
    }

    public boolean inputValidation() {
        String userName = name.getText().toString().trim();
        String userEmail = email.getText().toString().trim();
        String userPhone = phone.getText().toString().trim();
        String userSapid = sapid.getText().toString().trim();
        String userPassword = password.getText().toString().trim();

        if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(userEmail) || TextUtils.isEmpty(userPhone) || TextUtils.isEmpty(userSapid) || TextUtils.isEmpty(userPassword)) {
            Toast.makeText(MainActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Validation for Name
        if (!userName.matches("[a-zA-Z]+")) {
            Toast.makeText(MainActivity.this, "Name should only contain alphabets", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Validation for Email
        if (!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches() || !userEmail.endsWith("@nmims.edu.in")) {
            Toast.makeText(MainActivity.this, "Enter a valid NMIMS email address", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Validation for Phone
        if (userPhone.length() != 10 || !TextUtils.isDigitsOnly(userPhone)) {
            Toast.makeText(MainActivity.this, "Phone should be 10 digits long and contain only numbers", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Validation for SAP ID
        if (userSapid.length() != 11 || !TextUtils.isDigitsOnly(userSapid)) {
            Toast.makeText(MainActivity.this, "SAP ID should be 11 digits long and contain only numbers", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Validation for Password
        if (userPassword.length() < 8 || userPassword.length() > 20
                || !userPassword.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,20}$")) {
            Toast.makeText(MainActivity.this, "Password should be 8-20 characters long and contain at least one number, one uppercase, one lowercase, and one special character", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}

