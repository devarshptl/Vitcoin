package com.devarsh.vitcoin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HeaderActivity extends AppCompatActivity {

    private TextView name;
    private TextView username;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference mDatabase;
    SharedPreferences myPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_header);

        firebaseDatabase = FirebaseDatabase.getInstance();
        mDatabase = firebaseDatabase.getReference();
        myPreferences = getSharedPreferences("vcpref", Context.MODE_PRIVATE);
        final String usernameString = myPreferences.getString("username", "");

        name = findViewById(R.id.head_name);
        username = findViewById(R.id.head_username);
        Toast.makeText(this, usernameString, Toast.LENGTH_SHORT).show();
        username.setText("#"+usernameString);

        mDatabase.child("user").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                name.setText(dataSnapshot.child(usernameString).child("fullname").getValue().toString());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(HeaderActivity.this, "Database Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
