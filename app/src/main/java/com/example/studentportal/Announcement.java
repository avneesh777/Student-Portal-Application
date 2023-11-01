package com.example.studentportal;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.*;

public class Announcement extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private FirebaseUser currentUser;
    TextView announcementTextView;
    EditText announcementEditText;

    Button addbutton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("announcements");
        currentUser = mAuth.getCurrentUser();
        announcementTextView = findViewById(R.id.announcementTextView);
        announcementEditText = findViewById(R.id.announcementEditText);
        addbutton = findViewById(R.id.addbutton);

        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = announcementEditText.getText().toString().trim();
                String userName = getIntent().getStringExtra("userName");
                String userSapid = getIntent().getStringExtra("userSapid");

                DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child("Announcements");
                DatabaseReference currentUserRef = usersRef.child(userSapid);
                currentUserRef.child("name").setValue(userName);
                currentUserRef.child("message").setValue(message);
                currentUserRef.child("sapid").setValue(userSapid);
                announcementTextView.append(userName+": "+message+"\n");
            }
        });

//        addbutton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String message = announcementEditText.getText().toString().trim();
//                String userName = getIntent().getStringExtra("userName");
//                String userSapid = getIntent().getStringExtra("userSapid");
//
//                DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child("Announcements");
//                DatabaseReference currentUserRef = usersRef.child(userSapid);
//                currentUserRef.child("name").setValue(userName);
//                currentUserRef.child("message").setValue(message);
//                currentUserRef.child("sapid").setValue(userSapid);
//
////                displayAnnouncements();
//            }
//        });

    }

//    private void displayAnnouncements() {
//        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child("Announcements");
//
//        usersRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                StringBuilder announcementsBuilder = new StringBuilder();
//                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
//                    String name = userSnapshot.child("name").getValue(String.class);
//                    String message = userSnapshot.child("message").getValue(String.class);
//                    String sapid = userSnapshot.child("sapid").getValue(String.class);
//                    if (name != null && message != null && sapid != null) {
//                        String announcementInfo = "Name: " + name + "\n" +
//                                "Message: " + message + "\n" +
//                                "SAP ID: " + sapid + "\n\n";
//                        announcementsBuilder.append(announcementInfo);
//                    }
//                }
//                String allAnnouncements = announcementsBuilder.toString();
//                announcementTextView.append(allAnnouncements);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                // Handle the error
//                Log.w("TAG", "Failed to read value.", databaseError.toException());
//            }
//        });
//    }


}



        // Read announcements from the Firebase database
//        mDatabase.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                StringBuilder announcementsBuilder = new StringBuilder();
//                for (DataSnapshot announcementSnapshot : dataSnapshot.getChildren()) {
//                    String userId = announcementSnapshot.child("sapid").getValue(String.class);
//                    String userName = announcementSnapshot.child("name").getValue(String.class);
//                    String content = announcementSnapshot.child("content").getValue(String.class);
//                    if (userId != null && userName != null && content != null) {
//                        String announcementInfo = "User ID: " + userId + "\n" +
//                                "User Name: " + userName + "\n" +
//                                "Announcement: " + content + "\n\n";
//                        announcementsBuilder.append(announcementInfo);
//                    }
//                }
//                String allAnnouncements = announcementsBuilder.toString();
//                // Update the UI to display the announcements
//                announcementTextView.setText(allAnnouncements);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                // Handle any errors
//            }
//        });

        // Create a new announcement
//        String announcementContent = "Sample announcement content";
//        String userId = currentUser.getUid();
//        String userName = currentUser.getDisplayName();
//        String key = mDatabase.push().getKey();
//        if (key != null) {
//            Announcementdata newAnnouncement = new Announcementdata(userId, userName, announcementContent);
//            mDatabase.child(key).setValue(newAnnouncement);
//        }
//    }

//    public static class Announcementdata {
//        public String userId;
//        public String userName;
//        public String content;
//
//        public Announcementdata(String userId, String userName, String content) {
//            this.userId = userId;
//            this.userName = userName;
//            this.content = content;
//        }
//    }
//    }
//}

//
//import android.os.Bundle;
//import android.widget.TextView;
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.database.*;
//
//public class Announcement extends AppCompatActivity {
//
//    private FirebaseAuth mAuth;
//    private DatabaseReference mDatabase;
//    private FirebaseUser currentUser;
//    private TextView announcementTextView;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_announcement);
//
//        mAuth = FirebaseAuth.getInstance();
//        mDatabase = FirebaseDatabase.getInstance().getReference("announcements");
//        currentUser = mAuth.getCurrentUser();
//        announcementTextView = findViewById(R.id.announcementTextView);
//
//        String Name = getIntent().getStringExtra("userName");
//        String sapid = getIntent().getStringExtra("sapid");
//
//
//
//
//        // Read announcements from the Firebase database
////        mDatabase.addValueEventListener(new ValueEventListener() {
////            @Override
////            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
////                // Handle the fetched announcements data
////                // Update the UI to display the announcements
////                // Read announcements from the Firebase database
////                mDatabase.addValueEventListener(new ValueEventListener() {
////                    @Override
////                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
////                        StringBuilder announcementsBuilder = new StringBuilder();
////                        for (DataSnapshot announcementSnapshot : dataSnapshot.getChildren()) {
////                            Announcement announcement = announcementSnapshot.getValue(Announcement.class);
////                            if (announcement != null) {
////                                String announcementInfo = "User SapID: " + announcement.sapid + "\n" +
////                                        "User Name: " + announcement.userName + "\n" +
////                                        "Announcement: " + announcement.content + "\n\n";
////                                announcementsBuilder.append(announcementInfo);
////                            }
////                        }
////                        String allAnnouncements = announcementsBuilder.toString();
////                        // Update the UI to display the announcements
////                        announcementTextView.setText(allAnnouncements);
////                    }
////
////                    @Override
////                    public void onCancelled(@NonNull DatabaseError databaseError) {
////                        // Handle any errors
////                    }
////                });
////
////            }
////
////            @Override
////            public void onCancelled(@NonNull DatabaseError databaseError) {
////                // Handle any errors
////            }
////        });
//
//        // Create a new announcement
//        String announcementContent = "Sample announcement content";
//        Announcement newAnnouncement = new Announcement(currentUser.getUid(), currentUser.getDisplayName(), announcementContent);
//        mDatabase.push().setValue(newAnnouncement).addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//                if (task.isSuccessful()) {
//                    // Announcement added successfully
//                } else {
//                    // Handle the error
//                }
//            }
//        });
//
//        // Delete an announcement
//        String announcementIdToDelete = "announcement_id"; // Replace with the announcement ID to delete
//        mDatabase.child(announcementIdToDelete).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//                if (task.isSuccessful()) {
//                    // Announcement deleted successfully
//                } else {
//                    // Handle the error
//                }
//            }
//        });
//    }
//
//    // Define the Announcement class
//    public static class Announcement {
//        public String userId;
//        public String userName;
//        public String content;
//
//        public Announcement() {
//            // Default constructor required for calls to DataSnapshot.getValue(Announcement.class)
//        }
//
//        public Announcement(String userId, String userName, String content) {
//            this.userId = userId;
//            this.userName = userName;
//            this.content = content;
//        }
//    }
//}
//
//
////import androidx.appcompat.app.AppCompatActivity;
////
////import android.os.Bundle;
////
////public class Announcement extends AppCompatActivity {
////
////    @Override
////    protected void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_announcement);
////    }
////}