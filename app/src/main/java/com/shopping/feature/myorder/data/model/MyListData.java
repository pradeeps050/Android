package com.shopping.feature.myorder.data.model;

import java.util.ArrayList;

public class MyListData {
    private  String orderNum;
    private String orderStatus;
    private String motherChoice;
    private String deliveryCharge;
    private String vitTax;
    private String firstAmount;
    private String secondAmount;
    private String myOrderStatus;
    private String percentage;

    public MyListData(String orderNum, String orderStatus, String motherChoice, String deliveryCharge ,
                      String vitTax, String firstAmount , String secondAmount, String myOrderStatus ,String percentage)
    {
        this.orderNum = orderNum;
        this.orderStatus = orderStatus;
        this.motherChoice = motherChoice;
        this.deliveryCharge = deliveryCharge;
        this.vitTax = vitTax;
        this.firstAmount = firstAmount;
        this.secondAmount = secondAmount;
        this.myOrderStatus = myOrderStatus;
        this.percentage = percentage;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;

    }
    public String getOrderNum() {
        return orderNum;

    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;

    }
    public String getOrderStatus() {
        return orderStatus;

    }


    public void setMotherChoice(String motherCoice)
    {
        this.motherChoice= motherCoice;
    }

    public String getMotherChoice()
    {
      return motherChoice;
    }
    public  void setDeliveryCharge(String deliveryCharge)
    {
        this.deliveryCharge = deliveryCharge;
    }

    public String getDeliveryCharge()

    {
        return deliveryCharge;
    }

    public void setVitTax(String vitTax)
    {
        this.vitTax = vitTax;
    }

    public String getVitTax()
    {
        return vitTax;
    }

    public void setFirstAmount(String firstAmount) {
        this.firstAmount = firstAmount;
    }

    public String getFirstAmount(){
        return firstAmount;
    }

    private void setSecondAmount(String secondAmount){
        this.secondAmount = secondAmount;
    }

    public String getSecondAmount()
    {
     return secondAmount;
    }

  public void setMyOrderStatus(String myOrderStatus) {
      this.myOrderStatus = myOrderStatus;

  }
     public  String getMyOrderStatus()

     {
         return myOrderStatus;
     }

     public void setPercentage(String percentage)
     {
         this.percentage = percentage;
     }

     public  String getPercentage()
     {
         return percentage;
     }

    public static ArrayList<MyListData> getData() {
        ArrayList<MyListData> mydata = new ArrayList<>();
        mydata.add(new MyListData("VUXTY46656", "Cancelled Odrer","Mothers choic" +
                "e kacchi ghani mustard oil","Delivery Charge","VIT TAX","₹34","₹75",
                "Free","5%"));
        mydata.add(new MyListData("PASSWORD345", "Delivered","Mothers choice kacchi ghani mustard oil",
                "Delivery Charge","VIT TAX","₹34","₹75","Free","5%"));
        mydata.add(new MyListData("QWERTY123", "Pending","Mothers choice kacchi ghani mustard oil","Delivery Charge",
                "VIT TAX","₹34","₹75","Free","6%"));
        mydata.add(new MyListData("PASSWORD345", "confirmed Order","Mothers choice kacchi ghani mustard oil",
                "Delivery Charge","VIT TAX","₹34","₹75","Free","7%"));
        mydata.add(new MyListData("QWERTY123", "cancelled Order","Mothers choice skacchi ghani mustard oil","Delivery Charge",
                "VIT TAX","₹34","₹75","No discount","8%"));

        return mydata;
    }
}

