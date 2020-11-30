package com.example.ee1_2_test.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AdminDashboard {

    @SerializedName("allcustomers")
    @Expose
    private Integer allcustomers;
    @SerializedName("bookRequest")
    @Expose
    private Integer bookRequest;
    @SerializedName("totalrefunds")
    @Expose
    private Integer totalrefunds;
    @SerializedName("listOrders")
    @Expose
    private Integer listOrders;
    @SerializedName("totalsales")
    @Expose
    private Double totalsales;
    @SerializedName("allbooks")
    @Expose
    private Integer allbooks;

    public Integer getAllcustomers() {
        return allcustomers;
    }

    public void setAllcustomers(Integer allcustomers) {
        this.allcustomers = allcustomers;
    }

    public Integer getBookRequest() {
        return bookRequest;
    }

    public void setBookRequest(Integer bookRequest) {
        this.bookRequest = bookRequest;
    }

    public Integer getTotalrefunds() {
        return totalrefunds;
    }

    public void setTotalrefunds(Integer totalrefunds) {
        this.totalrefunds = totalrefunds;
    }

    public Integer getListOrders() {
        return listOrders;
    }

    public void setListOrders(Integer listOrders) {
        this.listOrders = listOrders;
    }

    public Double getTotalsales() {
        return totalsales;
    }

    public void setTotalsales(Double totalsales) {
        this.totalsales = totalsales;
    }

    public Integer getAllbooks() {
        return allbooks;
    }

    public void setAllbooks(Integer allbooks) {
        this.allbooks = allbooks;
    }
}
