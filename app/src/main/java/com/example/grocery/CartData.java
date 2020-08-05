package com.example.grocery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CartData{
        private String name;
        private String unit;
        private Double qnt;
        private Double rate;
        private int number_of_items;

    public CartData() {
    }

    public CartData(String name, String unit, Double qnt, Double rate) {
        this.name = name;
        this.unit = unit;
        this.qnt = qnt;
        this.rate = rate;
    }

    public CartData(String name, String unit, Double qnt, Double rate, int number_of_items) {
        this.name = name;
        this.unit = unit;
        this.qnt = qnt;
        this.rate = rate;
        this.number_of_items = number_of_items;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getQnt() {
        return qnt;
    }

    public void setQnt(Double qnt) {
        this.qnt = qnt;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public int getNumber_of_items() {
        return number_of_items;
    }

    public void setNumber_of_items(int number_of_items) {
        this.number_of_items = number_of_items;
    }
}
