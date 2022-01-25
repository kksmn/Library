package com.example.Task1.models;

import java.util.List;

public class ConfirmedOrder {
    public List<String> bookNames;
    public double price;
    public String dateFormatString;

    public List<String> getBookNames() {
        return bookNames;
    }

    public void setBookNames(List<String> bookNames) {
        this.bookNames = bookNames;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDateFormatString() {
        return dateFormatString;
    }

    public void setDateFormatString(String dateFormatString) {
        this.dateFormatString = dateFormatString;
    }
}
