package com.example.familycollection.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ShowOrder {

    @SerializedName("success")
    String success;
    @SerializedName("transactions")
    @Expose
    List<Order> orderList=null;
    @SerializedName("transaction_detail")
    @Expose
    OrderDetail orderDetail=null;

    public ShowOrder() {
    }

    public ShowOrder(String success, List<Order> orderList, OrderDetail orderDetail) {
        this.success = success;
        this.orderList = orderList;
        this.orderDetail = orderDetail;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    public OrderDetail getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(OrderDetail orderDetail) {
        this.orderDetail = orderDetail;
    }
}
