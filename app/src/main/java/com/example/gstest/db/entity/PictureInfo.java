package com.example.gstest.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

@Entity(primaryKeys = {"id","Title"})
public class PictureInfo implements Serializable {

    @NotNull
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "Date")
    private String date;
    @NotNull
    @ColumnInfo(name = "Title")
    private String title;
    @ColumnInfo(name = "Explanation")
    private String explanation;
    @ColumnInfo(name = "Url")
    private String url;

    @ColumnInfo(name = "FavouriteFlag")
    private Boolean flag;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }
}
