package com.example.Task1.dao;

import com.example.Task1.models.Book;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface BookDao {
    Map<Long, Book> getBookMap(String bookName,int start,int total);
    String getBookById(Long id);
    List<Long> getBookByName(String bookName);
    Long getCopyId(String name);
    Long addNewBook(Book book);
    Map<Long,Book> getBooks(int start,int total) throws SQLException, ClassNotFoundException;
    Integer getBookByCopyId(Integer copyId);





}
