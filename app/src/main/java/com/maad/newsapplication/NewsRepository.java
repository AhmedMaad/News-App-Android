package com.maad.newsapplication;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsRepository {

    private MutableLiveData<NewsModel> newsModelLiveData = new MutableLiveData<>();

    public MutableLiveData<NewsModel> loadNews(String sentCat, String country) {
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl("https://newsapi.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CallableInterface callable = retrofit.create(CallableInterface.class);
        Call<NewsModel> newsModelCall = callable.getNews(sentCat, country);

        newsModelCall.enqueue(new Callback<NewsModel>() {
            @Override
            public void onResponse(Call<NewsModel> call, Response<NewsModel> response) {
                NewsModel newsModel = response.body();
                Log.d("json", "Response: " + newsModel.getArticles().get(0).getUrlToImage());
                newsModelLiveData.setValue(newsModel);
            }

            @Override
            public void onFailure(Call<NewsModel> call, Throwable t) {
                Log.d("json", "Error: " + t.getMessage());
            }
        });
        return newsModelLiveData;
    }

}
