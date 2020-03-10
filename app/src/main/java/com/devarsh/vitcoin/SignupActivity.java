package com.devarsh.vitcoin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class SignupActivity extends AppCompatActivity {

    private Button signupButton;
    private Button loginButton;

    private EditText name;
    private EditText username;
    private EditText email;
    private EditText password;
    private EditText confirmPassword;

    SharedPreferences myPreferences;
    SharedPreferences.Editor myEditor;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        firebaseDatabase = FirebaseDatabase.getInstance();
        mDatabase = firebaseDatabase.getReference("user");

        myPreferences = getSharedPreferences("vcpref", Context.MODE_PRIVATE);
        myEditor = myPreferences.edit();

        loginButton = findViewById(R.id.signup_login_button);
        signupButton = findViewById(R.id.signup_signup_button);

        name = findViewById(R.id.signup_name);
        username = findViewById(R.id.signup_username);
        email = findViewById(R.id.signup_email);
        password = findViewById(R.id.signup_password);
        confirmPassword = findViewById(R.id.signup_confirm);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameString = name.getText().toString();
                String usernameString = username.getText().toString() ;
                String emailString = email.getText().toString() ;
                String passwordString = password.getText().toString() ;
                String confirmPasswordString = confirmPassword.getText().toString();

                ArrayList<String> transactionData = new ArrayList<>();

                myEditor.putString("username", usernameString);
                myEditor.putString("email", emailString);
                myEditor.putString("vcid", "1232swe"+usernameString + "112321");
                myEditor.commit();

                mDatabase.child(usernameString).child("username").setValue(usernameString);
                mDatabase.child(usernameString).child("fullname").setValue(nameString);
                mDatabase.child(usernameString).child("email").setValue(emailString);
                mDatabase.child(usernameString).child("password").setValue(passwordString);
                mDatabase.child(usernameString).child("vcid").setValue("1232swe"+usernameString + "112321");
                mDatabase.child(usernameString).child("coins").setValue(0);
                mDatabase.child(usernameString).child("transactions").setValue(transactionData);


                if(confirmPasswordString.equals(passwordString)) {
                    Intent i = new Intent(SignupActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }
                else{
                    Toast.makeText(SignupActivity.this, "Password doesn't match", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
