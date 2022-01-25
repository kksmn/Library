package com.example.Task1.dao;

import com.example.Task1.models.Author;

import java.util.List;

public interface AuthorDao {
    Long addNewAuthor(String name, String path);

    Long getAuthorByName(String name);

    void addAuthorId(Long bookId, Long authorId);

    void addAuthorToBook(Long bookId, List<Long> authors);

}
