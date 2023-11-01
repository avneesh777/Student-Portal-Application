package com.example.studentportal;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

public class Delete extends AppCompatActivity {

    private int generatedOTP;
    private EditText passwordEditText;
    private EditText otpEditText;
    private TextView otpTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        passwordEditText = findViewById(R.id.passwordEditText);
        otpEditText = findViewById(R.id.otpEditText);
        otpTextView = findViewById(R.id.otpTextView);

        Button generateOTPButton = findViewById(R.id.generateOTPButton);
        Button deleteProfileButton = findViewById(R.id.deleteProfileButton);

        generateOTPButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateOTP();
                otpTextView.setText("Generated OTP: " + generatedOTP);
                deleteProfileButton.setEnabled(true);
            }
        });



        // ...
        deleteProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredPassword = passwordEditText.getText().toString().trim();
                String enteredOTPStr = otpEditText.getText().toString().trim();

                if (enteredPassword.isEmpty() || enteredOTPStr.isEmpty()) {
                    // Show error message for empty fields
                    Toast.makeText(Delete.this, "Please enter both password and OTP", Toast.LENGTH_SHORT).show();
                    return;
                }

                int enteredOTP = Integer.parseInt(enteredOTPStr);

                // Replace with your validation logic
                if (!validatePassword(enteredPassword)) {
                    // Show error message for incorrect password
                    Toast.makeText(Delete.this, "Incorrect Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!validateOTP(enteredOTP)) {
                    // Show error message for incorrect OTP
                    Toast.makeText(Delete.this, "Incorrect OTP", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Show warning message
                showDeleteWarningDialog();
            }
        });
// ...

    }

    private void generateOTP() {
        Random rand = new Random();
        generatedOTP = rand.nextInt(9000) + 1000; // Generates a 4-digit random number
    }

    private boolean validatePassword(String enteredPassword) {
        // Replace with your password validation logic
        String correctPassword = getIntent().getStringExtra("userPassword");; // Replace with the correct password
        return enteredPassword.equals(correctPassword);
    }

    private boolean validateOTP(int enteredOTP) {
        return enteredOTP == generatedOTP;
    }
    private void deleteProfileFromDatabase(String userSapid) {
        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child("users");
        DatabaseReference currentUserRef = usersRef.child(userSapid);

        // Remove the user node
        currentUserRef.removeValue();
    }


    private void showDeleteWarningDialog() {
        // Replace this with an actual dialog implementation
        // You can use AlertDialog or any other custom dialog approach
        // Example using AlertDialog:

        new AlertDialog.Builder(this)
                .setTitle("Are you sure?")
                .setMessage("Are you sure you want to delete this account?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Delete the user from the database
                        // Redirect to login activity
                        // Example: deleteProfileFromDatabase();
                        // Then navigate to login activity:
                        deleteProfileFromDatabase(getIntent().getStringExtra("userSapid"));
                        Intent intent = new Intent(Delete.this, login.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                })
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
