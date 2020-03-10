package com.devarsh.vitcoin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LandingActivity extends AppCompatActivity {

    private TextView termUse;
    private TextView privacyPolicy;

    private Button loginButton;
    private Button signupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        termUse = findViewById(R.id.term_use);
        privacyPolicy = findViewById(R.id.privacy_policy);
        loginButton = findViewById(R.id.land_login);
        signupButton = findViewById(R.id.land_signup);

        termUse.setPaintFlags(termUse.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        termUse.setText("Terms of Use");

        privacyPolicy.setPaintFlags(privacyPolicy.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        privacyPolicy.setText("Privacy Policy");

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LandingActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LandingActivity.this, SignupActivity.class);
                startActivity(i);
            }
        });

    }
}
