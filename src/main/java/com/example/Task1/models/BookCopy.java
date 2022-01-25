package com.example.Task1.models;

public class BookCopy {
    public Long id;
    public Long book_id;
    public Boolean isDamaged;
    public Boolean isAvailable;
    public Double rating;
    public Double priceForDay;
    public Double price;
    public Long damagedBook_id;

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getPriceForDay() {
        return priceForDay;
    }

    public Long getDamagedBook_id() {
        return damagedBook_id;
    }

    public void setDamagedBook_id(Long damagedBook_id) {
        this.damagedBook_id = damagedBook_id;
    }

    public Boolean getDamaged() {
        return isDamaged;
    }

    public void setDamaged(Boolean damaged) {
        isDamaged = damaged;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    public void setPriceForDay(Double priceForDay) {
        this.priceForDay = priceForDay;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBook_id() {
        return book_id;
    }

    public void setBook_id(Long book_id) {
        this.book_id = book_id;
    }

    public boolean isDamaged() {
        return isDamaged;
    }

    public void setDamaged(boolean damaged) {
        isDamaged = damaged;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}

