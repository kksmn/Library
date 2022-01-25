package com.example.Task1.dao.impl;

import com.example.Task1.dao.ReaderDao;
import com.example.Task1.dao.executor.QueryExecutor;
import com.example.Task1.models.Reader;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReaderDaoImpl implements ReaderDao {

    private QueryExecutor executor = QueryExecutor.getInstance();
    private static final Logger LOGGER = LogManager.getLogger(OrderDaoImpl.class);
    private static final String FIND_READER = "SELECT * FROM READER WHERE email=?";
    private static final String COUNT_READER_ROWS = "SELECT COUNT(*) AS rowcount FROM READER";
    private static final String ADD_NEW_READER = "INSERT INTO READER (firstname, lastname,passportnumber,address,email,date,patronymic,photopath ) VALUES (?, ?, ?,?,?,?,?,?)";
    private static final String GET_BY_EMAIL = "SELECT * FROM Reader where email=?";
    private static final String GET_READERS = "SELECT * FROM Reader ORDER BY lastname limit ? OFFSET ? ";

    public void addReader(Reader reader) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id = 0;
        try {
            LocalDate date = new Date(reader.getDate().getTime()).toLocalDate();
            con = executor.getConnection();
            ps = con.prepareStatement(ADD_NEW_READER, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, reader.getFirstName());
            ps.setString(2, reader.getLastName());
            ps.setString(3, reader.getPassportNumber());
            ps.setString(4, reader.getAddress());
            ps.setString(5, reader.getEmail());
            ps.setDate(6, java.sql.Date.valueOf(date));
            ps.setString(7, reader.getPatronymic());
            ps.setString(8, reader.getPhotoPath());

            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            rs.next();
            id = rs.getInt(1);
        } catch (Exception e) {
            LOGGER.error("Error :" + e.getMessage());
        }
    }

    public Reader findReader(String email) throws SQLException, ClassNotFoundException {
        Reader reader = new Reader();
        try {
            ResultSet resultSet = executor.getResultSet(FIND_READER, email);
            while (resultSet.next()) {
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

        } catch (SQLException e) {
            LOGGER.error("Error :" + e.getMessage());
        }

        return reader;
    }

    public List<Reader> getReadersByEmail(String email) throws SQLException, ClassNotFoundException {

        List<Reader> list = new ArrayList<>();
        try {
            ResultSet resultSet = executor.getResultSet(GET_BY_EMAIL, email);
            while (resultSet.next()) {
                Reader reader = new Reader();
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

        } catch (SQLException e) {
            LOGGER.error("Error :" + e.getMessage());
        }
        return list;
    }

    public List<Reader> getReaders(int start, int total) throws SQLException, ClassNotFoundException {

        List<Reader> list = new ArrayList<>();
        try {
            ResultSet resultSet = executor.getResultSet(GET_READERS, start, total);
            while (resultSet.next()) {
                Reader reader = new Reader();
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

        } catch (Exception e) {
            LOGGER.error("Error :" + e.getMessage());
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

        } catch (Exception e) {
            LOGGER.error("Error :" + e.getMessage());
        }
        return rowcount;
    }
}
