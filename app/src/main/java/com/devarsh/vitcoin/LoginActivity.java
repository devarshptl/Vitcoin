package com.devarsh.vitcoin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {

    private Button signupButton;
    private Button loginButton;

    private EditText username;
    private EditText password;

    SharedPreferences myPreferences ;
    SharedPreferences.Editor mEditor;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("user");

        myPreferences = getSharedPreferences("vcpref", Context.MODE_PRIVATE);
        mEditor = myPreferences.edit();

        signupButton = findViewById(R.id.login_signup);
        loginButton = findViewById(R.id.login_login);
        username = findViewById(R.id.login_username);
        password = findViewById(R.id.login_password);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(i);
            }
        });

        if(!myPreferences.getString("username", "").equals("")){
            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        }

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String usernameString = String.valueOf(username.getText());
                final String passwordString = String.valueOf(password.getText());
                final String vcidString = "1232swe"+usernameString + "112321";
                if(usernameString.equals("")){
                    Toast.makeText(LoginActivity.this, "Enter username", Toast.LENGTH_SHORT).show();
                }
                else if (passwordString.equals("")){
                    Toast.makeText(LoginActivity.this, "Enter Password", Toast.LENGTH_SHORT).show();
                }
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild(usernameString)){
                            String tempPass = dataSnapshot.child(usernameString).child("password").getValue().toString();
                            if(passwordString.equals(tempPass)){
                                String emailString = dataSnapshot.child(usernameString).child("email").getValue().toString();
                                mEditor.putString("username", usernameString);
                                mEditor.putString("email", emailString);
                                mEditor.putString("vcid", vcidString);
                                mEditor.commit();
                                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(i);
                                finish();
                            }
                            else{
                                Toast.makeText(LoginActivity.this, "Wrong password", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(LoginActivity.this, "Database error", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
