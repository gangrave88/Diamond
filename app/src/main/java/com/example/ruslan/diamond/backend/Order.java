package com.example.ruslan.diamond.backend;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Order {

    @SerializedName("DataTime")
    @Expose
    private Long dataTime;
    @SerializedName("Weight")
    @Expose
    private Integer weight;
    @SerializedName("Sum")
    @Expose
    private Double sum;
    @SerializedName("Phone")
    @Expose
    private String phone;
    @SerializedName("Amount")
    @Expose
    private Integer amount;
    @SerializedName("Clarity")
    @Expose
    private String clarity;
    @SerializedName("Carat")
    @Expose
    private String carat;

    public Order(Long dataTime, Integer weight, Double sum, String phone, Integer amount, String clarity, String carat) {
        this.dataTime = dataTime;
        this.weight = weight;
        this.sum = sum;
        this.phone = phone;
        this.amount = amount;
        this.clarity = clarity;
        this.carat = carat;
    }

    public Long getDataTime() {
        return dataTime;
    }

    public void setDataTime(Long dataTime) {
        this.dataTime = dataTime;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getClarity() {
        return clarity;
    }

    public void setClarity(String clarity) {
        this.clarity = clarity;
    }

    public String getCarat() {
        return carat;
    }

    public void setCarat(String carat) {
        this.carat = carat;
    }

    @Override
    public String toString() {
        return "{" +
                "\"dataTime\":" + dataTime +
                ", \"weight\":" + weight +
                ", \"sum\":" + sum +
                ", \"phone\":\"" + phone + '\"' +
                ", \"amount\":" + amount +
                ", \"clarity\":\"" + clarity + '\"' +
                ", \"carat\":\"" + carat + '\"' +
                "}";
    }
}