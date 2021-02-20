package com.maad.newsapplication;

import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsViewModel extends ViewModel {

    //this is the data that we will fetch asynchronously
    private MutableLiveData<NewsModel> news;
    private NewsRepository newsRepository;

    public LiveData<NewsModel> getNews(String sentCat, String country) {
        if (news == null) {
            news = new MutableLiveData<>();
            newsRepository = new NewsRepository();
            news = newsRepository.loadNews(sentCat, country);
        }
        return news;
    }
}
