package com.example.Task1.dao;

import com.example.Task1.models.Book;

import java.sql.SQLException;
import java.util.Map;

public interface BookDao {
    Map<Long, Book> getBookMap(String bookName);
    String getBookById(Long id);
    Long getBookByName(String bookName);
    Long getCopyId(String name);
    Long addNewBook(Book book, String path);
    Map<Long,Book> getBooks(int start,int total) throws SQLException, ClassNotFoundException;
    Integer getBookByCopyId(Integer copyId);





}
