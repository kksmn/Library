package com.example.Task1.dao.impl;

import com.example.Task1.dao.OrderDao;
import com.example.Task1.dao.executor.QueryExecutor;
import com.example.Task1.models.Order;
import com.example.Task1.models.Reader;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {
    private QueryExecutor executor = QueryExecutor.getInstance();
    private static final Logger LOGGER = LogManager.getLogger(OrderDaoImpl.class);
    private static String ADD_ORDERS = "INSERT INTO Orders(readerId, price,copy_id,date) VALUES (?, ?, ?,?)";
    private static String DELETE_ORDER = "DELETE FROM Orders WHERE copy_id=?";
    private static String GET_COPY = "SELECT * FROM Orders copy WHERE readerid=?";
    private static String GET_ORDERS = "SELECT * FROM Orders WHERE readerid=?";
    private static String GET_ROWS = "SELECT row_count FROM Orders WHERE readerid=?";

    public void addNewOrder(Order order) {
        try {
            LocalDate date = new Date(order.getDate().getTime()).toLocalDate();
            executor.executeStatement(ADD_ORDERS, order.getReaderId(), order.getPrice(), order.getCopy_id(), java.sql.Date.valueOf(date));
        } catch (Exception e) {
            LOGGER.error("Error :" + e.getMessage());
        }

    }

    public void deleteOrder(Long id) {
        try {
            executor.executeStatement(DELETE_ORDER, id);
        } catch (Exception e) {
            LOGGER.error("Error :" + e.getMessage());
        }
    }

    public List<Order> getOrdersByReaderId(Long id) {
        List<Order> ordersId = new ArrayList<Order>();
        try {
            ResultSet resultSet = executor.getResultSet(GET_COPY, id);
            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getLong(1));
                order.setReaderId(resultSet.getLong(2));
                order.setFine(resultSet.getDouble(3));
                order.setDate(resultSet.getDate(4));
                order.setPrice(resultSet.getDouble(5));
                order.setCopy_id(resultSet.getLong(6));

                ordersId.add(order);
            }

        } catch (SQLException e) {
            LOGGER.error("Error :" + e.getMessage());
        }
        return ordersId;

    }


    public boolean getReaderDebt(Reader reader) {

        List<Order> orders = new ArrayList<Order>();
        try {
            ResultSet resultSet = executor.getResultSet(GET_ORDERS, reader.getId());
            if (resultSet.next()) {
                return true;
            } else return false;
        } catch (SQLException e) {
            LOGGER.error("Error :" + e.getMessage());
        }
        return false;
    }

    public int countOrders(Long readerId) {

        int count = 0;
        try {
            ResultSet resultSet = executor.getResultSet(GET_ROWS, readerId);
            while (resultSet.next()) {
                count = resultSet.getInt("row_count");
            }

        } catch (SQLException e) {
            LOGGER.error("Error :" + e.getMessage());
        }
        return count;
    }
}
