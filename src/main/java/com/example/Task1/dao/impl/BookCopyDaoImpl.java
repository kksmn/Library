package com.example.Task1.dao.impl;

import com.example.Task1.dao.BookCopyDao;
import com.example.Task1.models.BookCopy;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookCopyDaoImpl implements BookCopyDao {
    private QueryExecutor executor = QueryExecutor.getInstance();

    private static final String CHECK_IS_BOOK_AVAILABLE =  "SELECT isAvailable FROM BookCopy WHERE id=?";
    private static final String MAKE_BOOK_NOT_AVAILABLE = "Update BookCopy SET isavailable=false WHERE id = ?";
    private static final String GET_COPY_OF_AVAILABLE_BOOK = "SELECT * FROM BOOKCOPY WHERE bookid=? AND isavailable=true ";
    private static final String ADD_NEW_BOOK_COPY = "INSERT INTO BOOKCOPY (bookid,isDamaged,isAvailable,price,priceForDay) VALUES (?,?,?,?,?)";
    private static final String MAKE_BOOK_AVAILABLE = "Update BookCopy SET isavailable=true WHERE id = ?";
    private static final String COUNT_COPY_ROWS = "SELECT COUNT(*) AS rowcount FROM BOOKCOPY WHERE bookid=  ? AND isavailable=true";
    private static final String ADD_NEW_DAMAGED_PHOTO = "INSERT INTO DAMAGEDBOOK_PHOTO (path) VALUES (?)";
    private static final String SET_BOOK_RATING = "Update BookCopy SET rating=? WHERE book_id = ?";
    private static final String SET_DAMAGED_BOOK = "Update BookCopy SET damagedBook_id=? WHERE id = ?";
    private static final String SET_COPY_BY_ID = "SELECT id FROM BookCopy WHERE bookid=?";

    public boolean isBookAvailable(Long id) {
        Boolean isAvailable = false;
        try{
            ResultSet resultSet=executor.getResultSet(CHECK_IS_BOOK_AVAILABLE,id);
            while(resultSet.next()){
                isAvailable=resultSet.getBoolean("isAvailable");
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
        return isAvailable;
    }
    public BookCopy getCopyOfAvailableBook(Long id) {
        BookCopy copy=new BookCopy();
        try{
            ResultSet resultSet=executor.getResultSet(GET_COPY_OF_AVAILABLE_BOOK,id);
            while(resultSet.next()){
                copy.setId(resultSet.getLong("id"));
                copy.setBook_id(resultSet.getLong("bookid"));
                copy.setDamaged(resultSet.getBoolean("isdamaged"));
                copy.setAvailable(resultSet.getBoolean("isavailable"));
                copy.setPrice(resultSet.getDouble("price"));
                copy.setPriceForDay(resultSet.getDouble("priceforday"));
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
        return copy;
    }
    public void makeBookNotAvailable(Long id){
        try {
            executor.executeStatement(MAKE_BOOK_NOT_AVAILABLE,id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addNewCopy(Long bookId,Double price, Double priceForDay){
        try{
            executor.executeStatement(ADD_NEW_BOOK_COPY,bookId,price,priceForDay);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void makeBookAvailable(Long id){
        try{
            executor.executeStatement(MAKE_BOOK_AVAILABLE,id);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void setRating(Long id,Double rating) {

        Integer copyId=null;
        executor.executeStatement(SET_BOOK_RATING,rating,id);

    }
    public Long setDamagedBookPhoto(String path) {
        int copyId=0;
        try{
             copyId=executor.executeStatement(ADD_NEW_DAMAGED_PHOTO,path);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return Long.valueOf(copyId);
    }
    public void addDamagePhoto(Long id,Long bookId) {

        Integer copyId=null;
        try{
            ResultSet resultSet=executor.getResultSet(SET_DAMAGED_BOOK,id,bookId);
            while(resultSet.next()){
                copyId=resultSet.getInt("id");
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public Long getCopyByBookId(Long id) {

        Long copyId=null;
        try{
            ResultSet resultSet=executor.getResultSet(SET_COPY_BY_ID,id);
            while(resultSet.next()){
                copyId=resultSet.getLong("id");
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
        return copyId;
    }
    public int countAvailableCopy(Long id) {
        int amount=0;
        try{
            ResultSet resultSet=executor.getResultSet(COUNT_COPY_ROWS,id);
            while(resultSet.next()){
                amount=resultSet.getInt("rowcount");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return amount;
    }

}
