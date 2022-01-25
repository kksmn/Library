package com.example.Task1.dao.impl;

import com.example.Task1.dao.BookCopyDao;
import com.example.Task1.dao.executor.QueryExecutor;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookCopyDaoImpl implements BookCopyDao {
    private QueryExecutor executor = QueryExecutor.getInstance();
    private static final Logger LOGGER = LogManager.getLogger(BookCopyDaoImpl.class);

    private static final String CHECK_IS_BOOK_AVAILABLE = "SELECT isAvailable FROM BookCopy WHERE id=?";
    private static final String ADD_NEW_BOOK_COPY = "INSERT INTO BOOKCOPY (bookid,price,priceForDay,isavailable,isdamaged) VALUES (?,?,?,?,?)";
    private static final String MAKE_BOOK_AVAILABLE = "Update BookCopy SET isavailable=true WHERE id = ?";
    private static final String ADD_NEW_DAMAGED_PHOTO = "INSERT INTO DAMAGEDBOOK_PHOTO (path) VALUES (?)";
    private static final String SET_BOOK_RATING = "Update BookCopy SET rating=? WHERE book_id = ?";
    private static final String SET_DAMAGED_BOOK = "INSERT INTO DAMAGED_BOOK (book_id, damaged_id) VALUES (?,?)";
    private static final String SET_DAMAGED_PRICE = "Update BookCopy SET price=price*0.6 WHERE id = ?";
    private static final String MAKE_BOOK_DAMAGED = "Update BookCopy SET isdamaged=true WHERE id = ?";
    private static final String SET_COPY_BY_ID = "SELECT id FROM BookCopy WHERE bookid=?";

    public void setDamagedPrice(Long id) {
        try {
            executor.executeStatement(SET_DAMAGED_PRICE, id);
        } catch (Exception e) {
            LOGGER.error("Error :" + e.getMessage());
        }
    }

    public void makeCopyDamaged(Long id) {
        try {
            executor.executeStatement(MAKE_BOOK_DAMAGED, id);
        } catch (Exception e) {
            LOGGER.error("Error :" + e.getMessage());
        }
    }

    public boolean isBookAvailable(Long id) {
        Boolean isAvailable = false;
        try {
            ResultSet resultSet = executor.getResultSet(CHECK_IS_BOOK_AVAILABLE, id);
            while (resultSet.next()) {
                isAvailable = resultSet.getBoolean("isAvailable");
            }

        } catch (SQLException e) {
            LOGGER.error("Error :" + e.getMessage());
        }
        return isAvailable;
    }


    public void addNewCopy(Long bookId, Double price, Double priceForDay) {
        boolean isDamaged = false;
        boolean isAvailable = true;
        try {
            executor.executeStatement(ADD_NEW_BOOK_COPY, bookId, price, priceForDay, isAvailable, isDamaged);
        } catch (Exception e) {
            LOGGER.error("Error :" + e.getMessage());
        }
    }

    public void makeBookAvailable(Long id) {
        try {
            executor.executeStatement(MAKE_BOOK_AVAILABLE, id);
        } catch (Exception e) {
            LOGGER.error("Error :" + e.getMessage());
        }

    }

    public void setRating(Long id, Double rating) {
        try {
            Integer copyId = null;
            executor.executeStatement(SET_BOOK_RATING, rating, id);
        } catch (Exception e) {
            LOGGER.error("Error :" + e.getMessage());
        }

    }

    public Long setDamagedBookPhoto(String path) {
        int copyId = 0;
        try {
            copyId = executor.executeStatement(ADD_NEW_DAMAGED_PHOTO, path);
        } catch (Exception e) {
            LOGGER.error("Error :" + e.getMessage());
        }
        return Long.valueOf(copyId);
    }

    public void addDamagePhoto(Long copyId, Long id) {
        try {
            executor.getResultSet(SET_DAMAGED_BOOK, copyId, id);
        } catch (SQLException e) {
            LOGGER.error("Error :" + e.getMessage());
        }
    }

    public Long getCopyByBookId(Long id) {
        Long copyId = null;
        try {
            ResultSet resultSet = executor.getResultSet(SET_COPY_BY_ID, id);
            while (resultSet.next()) {
                copyId = resultSet.getLong("id");
            }

        } catch (SQLException e) {
            LOGGER.error("Error :" + e.getMessage());
        }
        return copyId;
    }


}
