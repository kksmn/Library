package com.example.Task1.dao.impl;

import com.example.Task1.dao.AuthorDao;
import com.example.Task1.dao.executor.QueryExecutor;
import com.example.Task1.models.Author;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AuthorDaoImpl implements AuthorDao {
    private QueryExecutor executor = QueryExecutor.getInstance();
    private static final Logger LOGGER = LogManager.getLogger(AuthorDaoImpl.class);

    private static final String GET_BOOK_ID_BY_AUTHOR = "SELECT book_id FROM AUTHOR_BOOK WHERE author_id=?";
    private static final String GET_AUTHOR_ID_BY_BOOK = "SELECT author_id FROM AUTHOR_BOOK WHERE book_id=?";
    private static final String ADD_NEW_AUTHOR = "INSERT INTO AUTHORS(authorname,path) values (?,?)";
    private static final String GET_AUTHOR_ID_BY_NAME = "SELECT id FROM AUTHORS WHERE authorname=?";
    private static final String GET_AUTHOR_BY_ID = "SELECT authorname,path FROM Authors WHERE id=?";
    private static final String ADD_AUTHOR_ID = "INSERT INTO author_book (book_id, author_id) VALUES (?, ?)";
    private static final String GET_ALL_AUTHORS = "SELECT * FROM authors";

    public Long getAuthorByName(String authorName) {
        Long id = null;
        try {
            ResultSet resultSet = executor.getResultSet(GET_AUTHOR_ID_BY_NAME, authorName);
            while (resultSet.next()) {
                id = resultSet.getLong("id");
            }
        } catch (SQLException e) {
            LOGGER.error("Error :" + e.getMessage());
        }
        return id;
    }

    public List<Long> findBookIdByAuthor(Long id) {
        List<Long> list = new ArrayList<>();
        try {
            ResultSet resultSet = executor.getResultSet(GET_BOOK_ID_BY_AUTHOR, id);
            while (resultSet.next()) {
                id = resultSet.getLong("book_id");
                list.add(id);
            }

        } catch (SQLException e) {
            LOGGER.error("Error :" + e.getMessage());
        }
        return list;
    }

    public List<Long> findAuthorIdByBook(Long id) {
        List<Long> list = new ArrayList<>();
        try {
            ResultSet resultSet = executor.getResultSet(GET_AUTHOR_ID_BY_BOOK, id);
            while (resultSet.next()) {
                id = resultSet.getLong("author_id");
                list.add(id);
            }

        } catch (SQLException e) {
            LOGGER.error("Error :" + e.getMessage());
        }
        return list;
    }

    public Long addNewAuthor(String name, String path) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        LocalDate date = LocalDate.now();
        int id = 0;
        try {
            con = executor.getConnection();
            ps = con.prepareStatement(ADD_NEW_AUTHOR, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, name);
            if (!path.equals("")) {
                ps.setString(2, path);
            } else {
                ps.setNull(2, Types.VARCHAR);
            }
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            rs.next();
            id = rs.getInt(1);
        } catch (SQLException e) {
            LOGGER.error("Error :" + e.getMessage());
        }
        return Long.valueOf(id);

    }

    public List<Author> getAllAuthors() {
        List<Author> authorList = new ArrayList<>();
        try {
            ResultSet resultSet = executor.getResultSet(GET_ALL_AUTHORS);
            while (resultSet.next()) {
                Author author = new Author();
                author.setId(resultSet.getLong("id"));
                author.setAuthorName(resultSet.getString("authorname"));
                author.setPath(resultSet.getString("path"));
                authorList.add(author);
            }
        } catch (Exception e) {
            LOGGER.error("Error :" + e.getMessage());
        }
        return authorList;
    }


    public Author getAuthorById(Long id) {
        Author author = new Author();
        try {
            ResultSet resultSet = executor.getResultSet(GET_AUTHOR_BY_ID, id);
            while (resultSet.next()) {
                author.setAuthorName(resultSet.getString("authorname"));
                author.setPath(resultSet.getString("path"));
            }

        } catch (SQLException e) {
            LOGGER.error("Error :" + e.getMessage());
        }
        return author;

    }

    public void addAuthorId(Long bookId, Long authorId) {
        try {
            executor.executeStatement(ADD_AUTHOR_ID, bookId, authorId);
        } catch (Exception e) {
            LOGGER.error("Error :" + e.getMessage());
        }
    }

    public List<Author> addAuthorToList(String[] authors, String[] authorImage) {
        List<Author> list = new ArrayList<>();
        try {
            for (int i = 0; i < authors.length; i++) {
                Long authorId = addNewAuthor(authors[i], authorImage[i]);
                list.add(new Author(authorId, authors[i], authorImage[i]));
            }
        } catch (Exception e) {
            LOGGER.error("Error :" + e.getMessage());
        }

        return list;
    }


    public void addAuthorToBook(Long bookId, List<Long> authors) {

        for (Long id : authors) {
            addAuthorId(bookId, id);
        }
    }
}

