package com.devarsh.vitcoin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

class News{
    String title;
    String desc;
    String source;
    News(String title, String desc, String source){
        this.title = title;
        this.desc = desc;
        this.source = source;
    }
}
public class NewsActivity extends AppCompatActivity {

    private TextView backHome;
    public TextView nextNews;
    public TextView previousViews;
    private TextView newsTitle;
    private TextView newsSource;
    private TextView newsDesc;




    FirebaseDatabase firebaseDatabase;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        nextNews = findViewById(R.id.news_next);
        previousViews = findViewById(R.id.news_previous);

        firebaseDatabase = FirebaseDatabase.getInstance();
        mDatabase = firebaseDatabase.getReference("news");

        backHome = findViewById(R.id.news_back);
        newsTitle = findViewById(R.id.news_title);
        newsDesc = findViewById(R.id.news_desc);
        newsSource = findViewById(R.id.news_source);

//        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
//            private int count;
//            private int i = 0;
//            private int j;
//            private ArrayList<News> news = new ArrayList<>();
//
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                count = (int)dataSnapshot.getChildrenCount();
//                j = count;
//                for( DataSnapshot d:dataSnapshot.getChildren()){
//                    String t = d.child("title").getValue().toString();
//                    String d1 = d.child("desc").getValue().toString();
//                    String s = d.child("source").getValue().toString();
//                    news.add(new News(t,d1,s));
//                }
////                nextNews.setOnClickListener(new View.OnClickListener() {
////                    @Override
////                    public void onClick(View v) {
////                        if(i<count){
////                            newsTitle.setText(news.get(i).title);
////                            newsSource.setText(news.get(i).source);
////                            newsDesc.setText(news.get(i).desc);
////                            i++;
////                        }
////                        else {
////                            nextNews.setEnabled(false);
////                        }
////                    }
////                });
////
////                previousViews.setOnClickListener(new View.OnClickListener() {
////                    @Override
////                    public void onClick(View v) {
////                        if(j>0){
////                            newsTitle.setText(news.get(j-1).title);
////                            newsSource.setText(news.get(j-1).source);
////                            newsDesc.setText(news.get(j-1).desc);
////                            i++;
////                        }
////                        else {
////                            nextNews.setEnabled(false);
////                        }
////
////                    }
////                });
//
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

        backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(NewsActivity.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}
