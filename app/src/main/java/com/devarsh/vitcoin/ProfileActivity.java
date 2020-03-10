package com.devarsh.vitcoin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {

    private TextView backButton;
    private TextView profilePageName;
    private TextView profilePageMail;
    private TextView transactions;
    private TextView balance;
    private TextView formFullname;
    private TextView formEmail;
    private TextView formUsername;
    private TextView formVcid;

    private Button donation;

    SharedPreferences myPreferences;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        backButton = findViewById(R.id.profile_page_back);
        profilePageName = findViewById(R.id.profile_page_name);
        profilePageMail = findViewById(R.id.profile_page_mail);
        transactions = findViewById(R.id.transactions);
        balance = findViewById(R.id.balance);
        formFullname = findViewById(R.id.form_full_name);
        formEmail = findViewById(R.id.form_email);
        formUsername = findViewById(R.id.form_username);
        formVcid = findViewById(R.id.form_vcid);
        donation = findViewById(R.id.donation);

        firebaseDatabase = FirebaseDatabase.getInstance();
        mDatabase = firebaseDatabase.getReference();
        myPreferences = getSharedPreferences("vcpref", Context.MODE_PRIVATE);

        profilePageMail.setText(myPreferences.getString("email", ""));
        formEmail.setText(myPreferences.getString("email", ""));
        formVcid.setText(myPreferences.getString("vcid", ""));

        final String usernameString = myPreferences.getString("username", "devarsh0205");
        formUsername.setText(usernameString);

        mDatabase.child("user").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                profilePageName.setText(dataSnapshot.child(usernameString).child("fullname").getValue().toString());
                balance.setText(dataSnapshot.child(usernameString).child("coins").getValue().toString());
                formFullname.setText(dataSnapshot.child(usernameString).child("fullname").getValue().toString());
                long noTransactions = dataSnapshot.child(usernameString).child("transactions").getChildrenCount();
                transactions.setText(String.valueOf(noTransactions));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ProfileActivity.this, "Database Error", Toast.LENGTH_SHORT).show();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProfileActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        donation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProfileActivity.this, PaymentActivity.class);
                startActivity(i);
            }
        });


    }
}
