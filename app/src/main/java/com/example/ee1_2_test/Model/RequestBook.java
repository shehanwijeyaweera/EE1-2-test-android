package com.example.ee1_2_test.Model;

import java.sql.Date;

public class RequestBook {
    private Integer request_id;
    private String bookName;
    private int quantity;
    private Date reqDate;

    private User user;

    public RequestBook() {
    }

    public RequestBook(String bookName, int quantity, User user) {
        this.bookName = bookName;
        this.quantity = quantity;
        this.user = user;
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
