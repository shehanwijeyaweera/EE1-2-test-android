package com.example.ee1_2_test.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestBook2 {

    @SerializedName("request_id")
    @Expose
    private Integer requestId;
    @SerializedName("bookName")
    @Expose
    private String bookName;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    @SerializedName("reqDate")
    @Expose
    private String reqDate;
    @SerializedName("user")
    @Expose
    private User user;

    public RequestBook2(Integer requestId, String bookName, Integer quantity, String reqDate, User user) {
        this.requestId = requestId;
        this.bookName = bookName;
        this.quantity = quantity;
        this.reqDate = reqDate;
        this.user = user;
    }

    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getReqDate() {
        return reqDate;
    }

    public void setReqDate(String reqDate) {
        this.reqDate = reqDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
