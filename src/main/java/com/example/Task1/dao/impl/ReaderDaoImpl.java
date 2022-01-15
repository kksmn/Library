package com.example.Task1.dao.impl;

import com.example.Task1.dao.ReaderDao;
import com.example.Task1.models.Reader;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReaderDaoImpl implements ReaderDao {
    private QueryExecutor executor = QueryExecutor.getInstance();
    private static final String FIND_READER = "SELECT * FROM READER WHERE email=?";

    private static final String COUNT_READER_ROWS = "SELECT COUNT(*) AS rowcount FROM READER";

    public void addReader(Reader reader) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO READER (firstname, lastname,passportnumber,address,email,date,patronymic ) VALUES (?, ?, ?,?,?,?,?)";
        try {
            executor.executeStatement(sql,reader.getFirstName(),reader.getLastName(),reader.getPassportNumber(),reader.getAddress(),reader.getEmail(),reader.getDate(),reader.getPatronymic());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public Reader findReader(String email) throws SQLException, ClassNotFoundException {
        Reader reader=new Reader();
        try{
            ResultSet resultSet=executor.getResultSet(FIND_READER,email);
            while(resultSet.next()){
                reader.setId(resultSet.getLong(1));
                reader.setFirstName(resultSet.getString(2));
                reader.setLastName(resultSet.getString(3));
                reader.setPassportNumber(resultSet.getString(4));
                reader.setAddress(resultSet.getString(5));
                reader.setEmail(resultSet.getString(6));
                reader.setDate(resultSet.getDate(7));
                reader.setPatronymic(resultSet.getString(8));
                reader.setPhotoPath(resultSet.getString(9));
            }

        }catch(SQLException e){
            e.printStackTrace();
        }

        return reader;
    }
    public List<Reader> getReaders(int start, int total) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM Reader limit ? OFFSET ?";
        List<Reader> list=new ArrayList<>();
        try{
            ResultSet resultSet=executor.getResultSet(sql,start,total);
            while (resultSet.next()){
                Reader reader=new Reader();
                reader.setId(resultSet.getLong(1));
                reader.setFirstName(resultSet.getString(2));
                reader.setLastName(resultSet.getString(3));
                reader.setPassportNumber(resultSet.getString(4));
                reader.setAddress(resultSet.getString(5));
                reader.setEmail(resultSet.getString(6));
                reader.setDate(resultSet.getDate(7));
                reader.setPatronymic(resultSet.getString(8));
                reader.setPhotoPath(resultSet.getString(9));

                list.add(reader);
            }

        }catch(SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public int getNoOfRecords() {
        int rowcount = 0;
        try {
            ResultSet resultSet = executor.getResultSet(COUNT_READER_ROWS);
            while (resultSet.next()) {
                rowcount = (int) resultSet.getLong("rowcount");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowcount;
    }
}
