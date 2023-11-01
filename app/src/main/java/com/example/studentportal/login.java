
package com.example.studentportal;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class login extends AppCompatActivity {
    EditText sapid;
    EditText password;
    Button loginbtn;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sapid = findViewById(R.id.sapid);
        password = findViewById(R.id.password);
        loginbtn = findViewById(R.id.login);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("users");

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sap = sapid.getText().toString();
                String pass = password.getText().toString();
                if (TextUtils.isEmpty(sap) || TextUtils.isEmpty(pass)) {
                    Toast.makeText(login.this, "Enter credentials", Toast.LENGTH_SHORT).show();
                } else {
                    checkCredentials(sap, pass);
                }
            }
        });
    }

    private void checkCredentials(final String sap, final String pass) {
        databaseReference.child(sap).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String storedPassword = dataSnapshot.child("password").getValue(String.class);
                    if (pass.equals(storedPassword)) {
                        Toast.makeText(login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(login.this, com.example.studentportal.Dashboard.class);
                        String userName = dataSnapshot.child("name").getValue(String.class);
                        String userEmail = dataSnapshot.child("email").getValue(String.class);
                        String userPhone = dataSnapshot.child("phone").getValue(String.class);
                        String userSapid = dataSnapshot.child("sapid").getValue(String.class);
                        intent.putExtra("userEmail", userEmail);
                        intent.putExtra("userPhone", userPhone);
                        intent.putExtra("userSapid", userSapid);
                        intent.putExtra("userName", userName);
                        intent.putExtra("userPassword", storedPassword);
                        startActivity(intent);
                    } else {
                        Toast.makeText(login.this, "Incorrect Password", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(login.this, "SAP ID not found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(login.this, "Database Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
