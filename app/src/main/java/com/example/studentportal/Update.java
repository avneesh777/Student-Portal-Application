package com.example.studentportal;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Update extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        // Retrieve old data from Intent
        String oldName = getIntent().getStringExtra("userName");
        String oldEmail = getIntent().getStringExtra("userEmail");
        String oldPhone = getIntent().getStringExtra("userPhone");
        String oldSapid = getIntent().getStringExtra("userSapid");

        TextView oldNameTextView = findViewById(R.id.oldNameTextView);
        TextView oldEmailTextView = findViewById(R.id.oldEmailTextView);
        TextView oldPhoneTextView = findViewById(R.id.oldPhoneTextView);
        TextView oldSapidTextView = findViewById(R.id.oldSapidTextView);

        oldNameTextView.setText("Name: " + oldName);
        oldEmailTextView.setText("Email: " + oldEmail);
        oldPhoneTextView.setText("Phone: " + oldPhone);
        oldSapidTextView.setText("SAP ID: " + oldSapid);

        Button updateButton = findViewById(R.id.updateButton);

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText newNameEditText = findViewById(R.id.newNameEditText);
                EditText newEmailEditText = findViewById(R.id.newEmailEditText);
                EditText newPhoneEditText = findViewById(R.id.newPhoneEditText);
                EditText newPasswordEditText = findViewById(R.id.newPasswordEditText);
                EditText confirmOldPasswordEditText = findViewById(R.id.confirmOldPasswordEditText);

                String newName = newNameEditText.getText().toString().trim();
                String newEmail = newEmailEditText.getText().toString().trim();
                String newPhone = newPhoneEditText.getText().toString().trim();
                String newPassword = newPasswordEditText.getText().toString().trim();
                String confirmOldPassword = confirmOldPasswordEditText.getText().toString().trim();

                if (TextUtils.isEmpty(newName) || TextUtils.isEmpty(newEmail) || TextUtils.isEmpty(newPhone) || TextUtils.isEmpty(newPassword) || TextUtils.isEmpty(confirmOldPassword)) {
                    Toast.makeText(Update.this, "All fields are required", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!TextUtils.isEmpty(newName) && !newName.matches(".*\\d+.*")) {
                    // Name is valid (does not contain numbers)
                } else {
                    Toast.makeText(Update.this, "Name should only contain alphabets", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!TextUtils.isEmpty(newEmail) && Patterns.EMAIL_ADDRESS.matcher(newEmail).matches() && newEmail.endsWith("@nmims.edu.in")) {
                    // Email is valid
                } else {
                    Toast.makeText(Update.this, "Enter a valid NMIMS email address", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!TextUtils.isEmpty(newPhone) && newPhone.length() == 10 && TextUtils.isDigitsOnly(newPhone)) {
                    // Phone number is valid
                } else {
                    Toast.makeText(Update.this, "Phone should be 10 digits long and contain only numbers", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!TextUtils.isEmpty(newPassword) && newPassword.length() >= 8 && newPassword.length() <= 20
                        && newPassword.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,20}$")) {
                    // Password is valid
                } else {
                    Toast.makeText(Update.this, "Password should be 8-20 characters long and contain at least one number, one uppercase, one lowercase, and one special character", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!confirmOldPassword.equals(getIntent().getStringExtra("userPassword"))) {
                    Toast.makeText(Update.this, "Incorrect Old Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (newPassword.equals(getIntent().getStringExtra("userPassword"))) {
                    Toast.makeText(Update.this, "New password must be different from old password", Toast.LENGTH_SHORT).show();
                    return;
                }

                DatabaseReference currentUserRef = FirebaseDatabase.getInstance().getReference().child("users").child(getIntent().getStringExtra("userSapid"));



                currentUserRef.child("name").setValue(newName);
                currentUserRef.child("email").setValue(newEmail);
                currentUserRef.child("phone").setValue(newPhone);
                currentUserRef.child("password").setValue(newPassword);


                Toast.makeText(Update.this, "Update Successful", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(Update.this, login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

    }

}


