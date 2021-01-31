package com.maad.newsapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {

    private String country;
    private String sentCat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        country = getSharedPreferences("settings", MODE_PRIVATE).getString("country", "us");
        sentCat = getIntent().getStringExtra("cat");


        NewsViewModel viewModel = new ViewModelProvider(this).get(NewsViewModel.class);
        viewModel.getNews(sentCat, country).observe(this, new Observer<NewsModel>() {
            @Override
            public void onChanged(NewsModel newsModel) {
                showList(newsModel.getArticles());
            }
        });
    }

    /* private void loadData() {
         ProgressBar progressBar = findViewById(R.id.pb);
         progressBar.setVisibility(View.VISIBLE);
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
                 progressBar.setVisibility(View.INVISIBLE);
                 NewsModel newsModel = response.body();
                 Log.d("json", "Response: " + newsModel.getArticles().get(0).getUrlToImage());
                 ArrayList<Article> articles = newsModel.getArticles();
                 showList(articles);
             }

             @Override
             public void onFailure(Call<NewsModel> call, Throwable t) {
                 progressBar.setVisibility(View.INVISIBLE);
                 Log.d("json", "Error: " + t.getMessage());
             }
         });
     }
 */
    private void showList(ArrayList<Article> articles) {
        RecyclerView recyclerView = findViewById(R.id.rv);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        CustomAdapter adapter = new CustomAdapter(this, articles);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.refresh, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
