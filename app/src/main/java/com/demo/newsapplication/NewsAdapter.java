package com.demo.newsapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {


    private ArrayList<Articles> articlesArrayList;

    public NewsAdapter(ArrayList<Articles> articlesArrayList, Context context) {
        this.articlesArrayList = articlesArrayList;
        this.context = context;
    }

    private Context context;


    @NonNull
    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_rv, parent, false);
        return new NewsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.ViewHolder holder, int position) {
        Articles articles = articlesArrayList.get(position);
        holder.txtsub.setText(articles.getDescription());
        holder.txtnew.setText(articles.getTitle());
        Picasso.get().load(articles.getUrlToIMage()).into(holder.imgnew);
holder.itemView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent  intent = new Intent(context,NewsDetailActivity.class);
        intent.putExtra("title",articles.getTitle());
        intent.putExtra("content",articles.getContent());
        intent.putExtra("desc",articles.getDescription());
        intent.putExtra("image",articles.getUrlToIMage());
        intent.putExtra("url",articles.getUrl());
        context.startActivity(intent);
    }
});

    }

    @Override
    public int getItemCount() {
        return articlesArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtnew, txtsub;
        private ImageView imgnew;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtnew = itemView.findViewById(R.id.txtnew);
            txtsub = itemView.findViewById(R.id.txtsub);
            imgnew = itemView.findViewById(R.id.imgnew);

        }
    }
}
