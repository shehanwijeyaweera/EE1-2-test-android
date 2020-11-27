package com.example.ee1_2_test.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Book {

    @SerializedName("bookId")
    @Expose
    private Integer bookId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("price")
    @Expose
    private Double price;
    @SerializedName("publisher")
    @Expose
    private String publisher;
    @SerializedName("logo")
    @Expose
    private String logo;
    @SerializedName("pubdate")
    @Expose
    private String pubdate;
    @SerializedName("isbn")
    @Expose
    private Integer isbn;
    @SerializedName("logoImagepathApi")
    @Expose
    private String logoImagepathApi;
    @SerializedName("logoImagePath")
    @Expose
    private String logoImagePath;
    @SerializedName("authors")
    @Expose
    private List<Author> authors = null;
    @SerializedName("categories")
    @Expose
    private List<Category> categories = null;

    public Book(Integer bookId, String title, String description, Double price, String publisher, String logo, String pubdate, Integer isbn, String logoImagepathApi, String logoImagePath, List<Author> authors, List<Category> categories) {
        this.bookId = bookId;
        this.title = title;
        this.description = description;
        this.price = price;
        this.publisher = publisher;
        this.logo = logo;
        this.pubdate = pubdate;
        this.isbn = isbn;
        this.logoImagepathApi = logoImagepathApi;
        this.logoImagePath = logoImagePath;
        this.authors = authors;
        this.categories = categories;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }


    public Integer getIsbn() {
        return isbn;
    }

    public void setIsbn(Integer isbn) {
        this.isbn = isbn;
    }

    public String getLogoImagepathApi() {
        return logoImagepathApi;
    }

    public void setLogoImagepathApi(String logoImagepathApi) {
        this.logoImagepathApi = logoImagepathApi;
    }

    public String getLogoImagePath() {
        return logoImagePath;
    }

    public void setLogoImagePath(String logoImagePath) {
        this.logoImagePath = logoImagePath;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }
}
