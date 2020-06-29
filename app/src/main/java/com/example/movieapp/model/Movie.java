package com.example.movieapp.model;

import java.io.Serializable;

public class Movie implements Serializable {

    public Movie() {}

    private String imgUrl;
    private String title;
    private String overView;
    private String popularity;
    private String voteAverage;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String url) {
        this.imgUrl = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String t) {
        this.title = t;
    }

    public String getOverView() {
        return overView;
    }

    public void setOverView(String t) {
        this.overView = t;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String t) {
        this.popularity = t;
    }

    public String getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(String t) {
        this.voteAverage = t;
    }

}
