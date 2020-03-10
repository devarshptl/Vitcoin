package com.devarsh.vitcoin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private ImageView profileImage;
    private ImageView trendingImage;
    private ImageView paymentImage;
    private GraphView graphView;
    private ImageView navIcon;
    private NavigationView navigationView;
    private DrawerLayout mDrawerLayout;
    public TextView profileName;
    public LinearLayout profileLink;
    public LinearLayout makePayment;

    public LinearLayout mainFeed;

    SharedPreferences myPreferences;
    SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myPreferences = getSharedPreferences("vcpref", Context.MODE_PRIVATE);
        mEditor = myPreferences.edit();
        String userName = myPreferences.getString("username", "default");

        profileImage = findViewById(R.id.profile_image);
        trendingImage = findViewById(R.id.trending_image);
        paymentImage = findViewById(R.id.payment_image);
        graphView = findViewById(R.id.graph_view);
        profileName = findViewById(R.id.profile_name);
        navIcon = findViewById(R.id.nav_icon);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        profileLink = findViewById(R.id.profile_link);
        mainFeed = findViewById(R.id.main_feed);
        makePayment= findViewById(R.id.make_payment);


        profileName.setText(userName);

        profileImage.setClipToOutline(true);
        trendingImage.setClipToOutline(true);
        paymentImage.setClipToOutline(true);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                if(id == R.id.nav_qrscanner){
                    Intent i = new Intent(MainActivity.this, ScannerActivity.class);
                    startActivity(i);
                }
                if(id == R.id.nav_messages){
                    Intent i = new Intent(MainActivity.this,NewsActivity.class);
                    startActivity(i);
                }
                if(id == R.id.nav_friends){
                    Intent i = new Intent(MainActivity.this,MainActivity.class);
                    startActivity(i);
                }
                if(id == R.id.nav_discussion){
                    Intent i = new Intent(MainActivity.this,MainActivity.class);
                    startActivity(i);
                }
                if(id == R.id.nav_logout){
                    mEditor.clear().commit();
                    File f = new File("/data/data/com/devarsh/vitcoin/shared_prefs/");
                    File[] listFiles = f.listFiles();
                    for(File file : listFiles) {
                        file.delete();
                    }
                    Intent i = new Intent(MainActivity.this,LandingActivity.class);
                    startActivity(i);
                    finish();
                }
                return true;        }
        });
        navIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
                mDrawerLayout.openDrawer(navigationView);
            }
        });

        profileLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(i);
                finish();
            }
        });

        mainFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, NewsActivity.class);
                startActivity(i);
            }
        });

        makePayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ScannerActivity.class);
                startActivity(i);
                finish();
            }
        });

        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6)
        });
        graphView.addSeries(series);

    }
}
