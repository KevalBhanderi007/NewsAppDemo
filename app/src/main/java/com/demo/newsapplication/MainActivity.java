package com.demo.newsapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements CategoryAdapter.CategoryClickInterface {


    private RecyclerView catagoryrv, newsrv;
    private ProgressBar progbar;

    private ArrayList<Articles> articlesArrayList;
    private ArrayList<CategoryRVModel> categoryRVModelsArraylist;
    private CategoryAdapter categoryAdapter;
    private NewsAdapter newsAdapter;

    //58f795b7dc90431c842a4919d0f94fe7
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progbar = findViewById(R.id.progbar);
        catagoryrv = findViewById(R.id.catagoryrv);
        newsrv = findViewById(R.id.newsrv);
        articlesArrayList = new ArrayList<>();
        categoryRVModelsArraylist = new ArrayList<>();
        newsAdapter = new NewsAdapter(articlesArrayList, this);
        categoryAdapter = new CategoryAdapter(categoryRVModelsArraylist, this, this::onCategoryClick);

        newsrv.setLayoutManager(new LinearLayoutManager(this));
        newsrv.setAdapter(newsAdapter);
        catagoryrv.setAdapter(categoryAdapter);

        getCategories();
        getNews("All");
        newsAdapter.notifyDataSetChanged();

    }

    private void getCategories() {


        categoryRVModelsArraylist.add(new CategoryRVModel("All", "https://unsplash.com/photos/Q7wDdmgCBFg"));
        categoryRVModelsArraylist.add(new CategoryRVModel("Technology", "https://unsplash.com/photos/XJXWbfSo2f0"));
        categoryRVModelsArraylist.add(new CategoryRVModel("Science", "https://unsplash.com/photos/OgvqXGL7XO4"));
        categoryRVModelsArraylist.add(new CategoryRVModel("Sports", "https://unsplash.com/photos/WUehAgqO5hE"));
        categoryRVModelsArraylist.add(new CategoryRVModel("General", "https://unsplash.com/photos/2G8mnFvH8xk"));
        categoryRVModelsArraylist.add(new CategoryRVModel("Bussiness", "https://unsplash.com/photos/MMUpT2C5Pnk"));
        categoryRVModelsArraylist.add(new CategoryRVModel("Entertainment", "https://unsplash.com/photos/Qnlp3FCO2vc"));
        categoryRVModelsArraylist.add(new CategoryRVModel("Health", "https://unsplash.com/photos/NTyBbu66_SI"));
        categoryRVModelsArraylist.add(new CategoryRVModel("Space", "https://unsplash.com/photos/qtRF_RxCAo0"));
        categoryAdapter.notifyDataSetChanged();

    }

    private void getNews(String category) {
        progbar.setVisibility(View.VISIBLE);
        articlesArrayList.clear();

        String categoryURL = "http://newsapi.org/v2/top-headlines/sources?country=us&category=" + category + "&apiKey=58f795b7dc90431c842a4919d0f94fe7";
        String url = "http://newsapi.org/v2/top-headlines/sources?country=us&excludeDomains=stackoverflow.com&sortBy=publishedAt&langauge=en&apiKey=58f795b7dc90431c842a4919d0f94fe7";
        String BASE_URL = "http://newsapi.org/";


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitApi retrofitApi = retrofit.create(RetrofitApi.class);
        Call<NewsModel> call;

        if (category.equals("All")) {
            call = retrofitApi.getAllNews(url);

        } else {
            call = retrofitApi.getNewsByCategory(categoryURL);
        }
        call.enqueue(new Callback<NewsModel>() {
            @Override
            public void onResponse(Call<NewsModel> call, Response<NewsModel> response) {
                NewsModel newsModel = response.body();
                progbar.setVisibility(View.GONE);


                ArrayList<Articles> articles = newsModel.getArticles();


                for (int i=0; i<articles.size(); i++) {
                    articlesArrayList.add(new Articles(articles.get(i).getTitle(),articles.get(i).getDescription(),articles.get(i).getUrlToIMage(),articles.get(i).getUrl(),articles.get(i).getContent()));

                }



                newsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<NewsModel> call, Throwable t) {
                Log.e("onFailure","jgxvhbyofdzcxougvy");

            }
        });
    }

    @Override
    public void onCategoryClick(int positon) {
        String category = categoryRVModelsArraylist.get(positon).getCategory();
        getNews(category);


    }
}