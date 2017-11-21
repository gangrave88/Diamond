package com.example.ruslan.diamond.db;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class Price extends RealmObject{

    @SerializedName("Amount")
    @Expose
    private Integer amount;
    @SerializedName("Carat")
    @Expose
    private String carat;
    @SerializedName("Weight")
    @Expose
    private Integer weight;
    @SerializedName("Price")
    @Expose
    private Double price;
    @SerializedName("Clarity")
    @Expose
    private String clarity;

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getCarat() {
        return carat;
    }

    public void setCarat(String carat) {
        this.carat = carat;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getClarity() {
        return clarity;
    }

    public void setClarity(String clarity) {
        this.clarity = clarity;
    }

}
