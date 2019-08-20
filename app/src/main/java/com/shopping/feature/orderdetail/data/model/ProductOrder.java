package com.shopping.feature.orderdetail.data.model;

import com.shopping.framework.model.Product;

import java.util.ArrayList;

public class ProductOrder {
    private String placesDay;
    private String orderStatus;
    private String cashOnDeliver;
    private String payAmount;

    public ProductOrder(String placesDay, String orderStatus, String cashOnDeliver, String payAmount) {
        this.placesDay = placesDay;
        this.orderStatus = orderStatus;
        this.cashOnDeliver = cashOnDeliver;
        this.payAmount = payAmount;

    }

    public void setPlacesDay(String placesDay) {
        this.placesDay = placesDay;
    }

    public String getPlacesDay() {
        return placesDay;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setCashOnDeliver(String cashOnDeliver) {
        this.cashOnDeliver = cashOnDeliver;
    }

    public String getCashOnDeliver() {
        return cashOnDeliver;
    }

    public void setPayAmount(String payAmount) {
        this.payAmount = payAmount;
    }

    public String getPayAmount() {
        return payAmount;
    }


    public static ArrayList<ProductOrder> getOrderData() {
        ArrayList<ProductOrder> order = new ArrayList<>();
        order.add(new ProductOrder("Places Today", "Cancelled Order", "Cash On Deliver",
                "you have to pay 75 to the deliver executive"));
        return order;
    }
}


