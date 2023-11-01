package com.example.studentportal;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        TextView welcomeMessage = findViewById(R.id.welcomeMessage);
        Button logoutButton = findViewById(R.id.logoutButton);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button announcementButton = findViewById(R.id.announcementButton);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button updateButton = findViewById(R.id.updateProfileButton);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button deleteButton = findViewById(R.id.deleteProfileButton);

        // Get the user's name from the Intent
        String userName = getIntent().getStringExtra("userName");

        String userEmail = getIntent().getStringExtra("userEmail");

        String userPhone = getIntent().getStringExtra("userPhone");

        String userSapid = getIntent().getStringExtra("userSapid");

        String userPassword = getIntent().getStringExtra("userPassword");

        // Display welcome message
        welcomeMessage.setText("Welcome " + userName);

        // Set OnClickListener for logout button
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to login activity
                finish(); // Finish the current activity
            }
        });

        announcementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to announcement activity
                Intent intent= new Intent(Dashboard.this, Announcement.class);
                intent.putExtra("userName", userName);
                intent.putExtra("userSapid", userSapid);
                startActivity(intent);
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to update activity
                Intent intent= new Intent(Dashboard.this, Update.class);
                intent.putExtra("userName", userName);
                intent.putExtra("userEmail", userEmail);
                intent.putExtra("userPhone", userPhone);
                intent.putExtra("userSapid", userSapid);
                intent.putExtra("userPassword", userPassword);
                startActivity(intent);
            }
        });



        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to delete activity
                Intent intent= new Intent(Dashboard.this, Delete.class);
                intent.putExtra("userPassword", userPassword);
                intent.putExtra("userSapid", userSapid);
                startActivity(intent);
            }
        });


    }
}
