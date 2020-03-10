package com.devarsh.vitcoin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import java.io.File;

public class SplashActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Thread th = new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                    Intent i = new Intent(SplashActivity.this, LandingActivity.class);
                    startActivity(i);
                }catch (Exception e){
                    System.out.println(e);
                }
            }
        };
        th.start();
    }
}
