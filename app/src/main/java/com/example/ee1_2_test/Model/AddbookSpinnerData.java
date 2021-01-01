package com.example.ee1_2_test.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AddbookSpinnerData {
    @SerializedName("Category")
    @Expose
    private List<Category> category = null;
    @SerializedName("Author")
    @Expose
    private List<Author> author = null;

    public AddbookSpinnerData() {
    }

    public AddbookSpinnerData(List<Category> category, List<Author> author) {
        this.category = category;
        this.author = author;
    }

    public List<Category> getCategory() {
        return category;
    }

    public void setCategory(List<Category> category) {
        this.category = category;
    }

    public List<Author> getAuthor() {
        return author;
    }

    public void setAuthor(List<Author> author) {
        this.author = author;
    }
}
