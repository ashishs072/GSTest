package com.example.gstest.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CurrentDateResponse implements Serializable {
    private Boolean favouriteFlag;
    @SerializedName("date")
    private String date;
    @SerializedName("title")
    private String title;
    @SerializedName("explanation")
    private String explanation;
    @SerializedName("url")
    private String imageUrl;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Boolean getFavouriteFlag() {
        return favouriteFlag;
    }

    public void setFavouriteFlag(Boolean favouriteFlag) {
        this.favouriteFlag = favouriteFlag;
    }
}
