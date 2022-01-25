package com.example.Task1.dao.impl;

import com.example.Task1.dao.GenreDao;
import com.example.Task1.dao.executor.QueryExecutor;
import com.example.Task1.dao.pool.ConnectionPool;
import com.example.Task1.models.Genre;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GenreDaoImpl implements GenreDao {
    private QueryExecutor executor = QueryExecutor.getInstance();
    private static final Logger LOGGER = LogManager.getLogger(GenreDaoImpl.class);

    private static final String ADD_NEW_GENRE = "INSERT INTO genre_book (book_id, genre_id) VALUES (?, ?)";
    private static final String GET_GENRE_BY_NAME = "SELECT id FROM Genres WHERE genrename=?";

    public Long getGenreByName(String name) {
        Long genreId = null;
        try {
            ResultSet resultSet = executor.getResultSet(GET_GENRE_BY_NAME,name);
            while (resultSet.next()) {
                genreId = resultSet.getLong("id");
            }

        } catch (SQLException e) {
            LOGGER.error("Error :" + e.getMessage());
        }
        return genreId;

    }
    public void addNewGenre(Long book_id, Long genre_id) {
        try {
            executor.executeStatement(ADD_NEW_GENRE, book_id, genre_id);
        }catch (Exception e) {
            LOGGER.error("Error :" + e.getMessage());
        }
    }

    public List<Genre> addNewGenres(String[] genres) {
        List<Genre> list = new ArrayList<>();
        try {
            for (int i = 0; i < genres.length; i++) {
                Long genre_id = getGenreByName(genres[i]);
                list.add(new Genre(genre_id, genres[i]));
            }
        }catch (Exception e) {
            LOGGER.error("Error :" + e.getMessage());
        }
        return list;
    }

    public void addGenreToBook(Long bookId, List<Genre> genres) {
        try {
            for (Genre genre : genres) {
                addNewGenre(bookId, genre.getId());
            }
        }catch (Exception e) {
            LOGGER.error("Error :" + e.getMessage());
        }
    }
}
