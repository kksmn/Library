package com.example.Task1.models;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class Book {
    Long id;
    String russianName;
    String originalName;
    Integer count;
    Integer year;//type
    Date registrationDate;
    Integer countPages;
    private List<Author> authors;
    private List<Genre> genres;
    private List<String> images;
    int countAvailableCopies;
    LocalDate regDate;
    LocalDate bookYear;

    public LocalDate getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDate regDate) {
        this.regDate = regDate;
    }

    public LocalDate getBookYear() {
        return bookYear;
    }

    public void setBookYear(LocalDate bookYear) {
        this.bookYear = bookYear;
    }

    public String getRussianName() {
        return russianName;
    }

    public void setRussianName(String russianName) {
        this.russianName = russianName;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public int getCountAvailableCopies() {
        return countAvailableCopies;
    }

    public void setCountAvailableCopies(int countAvailableCopies) {
        this.countAvailableCopies = countAvailableCopies;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return russianName;
    }

    public void setName(String russianName) {
        this.russianName = russianName;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }



    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Integer getCountPages() {
        return countPages;
    }

    public void setCountPages(Integer countPages) {
        this.countPages = countPages;
    }

}
