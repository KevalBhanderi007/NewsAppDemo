package com.demo.newsapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class NewsDetailActivity extends AppCompatActivity {

    String title,desc,content,url,imageURl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        title = getIntent().getStringExtra("title");
        desc = getIntent().getStringExtra("desc");
        content = getIntent().getStringExtra("content");
        url = getIntent().getStringExtra("url");
        imageURl = getIntent().getStringExtra("image");
    }
}