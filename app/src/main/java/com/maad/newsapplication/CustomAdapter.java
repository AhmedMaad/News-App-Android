package com.maad.newsapplication;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private final Activity activity;
    private final ArrayList<Article> articles;

    public CustomAdapter(Activity activity, ArrayList<Article> articles) {
        this.activity = activity;
        this.articles = articles;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View v = inflater.inflate(R.layout.news_list_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String title = articles.get(position).getTitle().substring(0, 25);
        holder.textView.setText(activity.getString(R.string.article_title, title));
        String imageLink = articles.get(position).getUrlToImage();
        Log.d("json", "Link: " + imageLink);
        if (imageLink != null)
            Picasso
                    .get()
                    .load(imageLink)
                    .placeholder(R.drawable.ic_image)
                    .into(holder.imageView);
        else
            holder.imageView.setImageResource(R.drawable.ic_baseline_broken);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri link = Uri.parse(articles.get(position).getUrl());
                Intent i = new Intent(Intent.ACTION_VIEW, link);
                activity.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final CardView cardView;
        private final ImageView imageView;
        private final TextView textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.card_view);
            imageView = itemView.findViewById(R.id.iv);
            textView = itemView.findViewById(R.id.tv);
        }
    }
}
