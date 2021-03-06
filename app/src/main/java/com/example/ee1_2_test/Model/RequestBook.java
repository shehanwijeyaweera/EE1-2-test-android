package com.example.ee1_2_test.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.sql.Date;

public class RequestBook {

    @SerializedName("request_id")
    @Expose
    private Integer request_id;
    @SerializedName("bookName")
    @Expose
    private String bookName;
    @SerializedName("quantity")
    @Expose
    private int quantity;
    @SerializedName("reqDate")
    @Expose
    private Date reqDate;

    @SerializedName("user")
    @Expose
    private User user;

    public RequestBook() {
    }

    public RequestBook(String bookName, int quantity, User user) {
        this.bookName = bookName;
        this.quantity = quantity;
        this.user = user;
    }

    public RequestBook(Integer request_id, String bookName, int quantity, Date reqDate, User user) {
        this.request_id = request_id;
        this.bookName = bookName;
        this.quantity = quantity;
        this.reqDate = reqDate;
        this.user = user;
    }

    public void setRequest_id(Integer request_id) {
        this.request_id = request_id;
    }

    public int getRequest_id() {
        return request_id;
    }

    public void setRequest_id(int request_id) {
        this.request_id = request_id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getReqDate() {
        return reqDate;
    }

    public void setReqDate(Date reqDate) {
        this.reqDate = reqDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
