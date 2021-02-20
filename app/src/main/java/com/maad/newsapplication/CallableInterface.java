package com.maad.newsapplication;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CallableInterface {

    @GET("/v2/top-headlines?apiKey=17890bc756f44b72a04846f49aefab5c")
    Call<NewsModel> getNews(@Query("category") String cat, @Query("country") String country);

    @GET("/v2/top-headlines?apiKey=17890bc756f44b72a04846f49aefab5c&q=sports")
    Call<NewsModel> getNewsWithPaging(@Query("page") int pageNo);

}
