package com.maad.newsapplication;

import java.util.ArrayList;

public class NewsModel {

    private ArrayList<Article> articles;

    private int totalResults;

    public ArrayList<Article> getArticles() {
        return articles;
    }

    public int getTotalResults() {
        return totalResults;
    }
}


class Article {

    private String title;
    private String url;
    private String urlToImage;

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }
}