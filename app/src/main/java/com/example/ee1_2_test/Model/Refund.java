package com.example.ee1_2_test.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Refund {
    @SerializedName("refund_Id")
    @Expose
    private Integer refundId;
    @SerializedName("reason")
    @Expose
    private String reason;
    @SerializedName("reqDate")
    @Expose
    private String reqDate;
    @SerializedName("customer_orders")
    @Expose
    private Customer_orders customerOrders;
    @SerializedName("respond")
    @Expose
    private String respond;

    public Integer getRefundId() {
        return refundId;
    }

    public void setRefundId(Integer refundId) {
        this.refundId = refundId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getReqDate() {
        return reqDate;
    }

    public void setReqDate(String reqDate) {
        this.reqDate = reqDate;
    }

    public Customer_orders getCustomerOrders() {
        return customerOrders;
    }

    public void setCustomerOrders(Customer_orders customerOrders) {
        this.customerOrders = customerOrders;
    }

    public String getRespond() {
        return respond;
    }

    public void setRespond(String respond) {
        this.respond = respond;
    }

}
