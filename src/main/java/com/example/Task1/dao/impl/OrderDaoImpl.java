package com.example.Task1.dao.impl;

import com.example.Task1.dao.OrderDao;
import com.example.Task1.dao.impl.QueryExecutor;
import com.example.Task1.dao.pool.ConnectionPool;
import com.example.Task1.models.Order;
import com.example.Task1.models.Reader;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDaoImpl implements OrderDao {
    private QueryExecutor executor = QueryExecutor.getInstance();
    public OrderDaoImpl() {

    }

    public void addNewOrder(Order order) {
        String sql = "INSERT INTO Orders(readerId, price,copyId,date) VALUES (?, ?, ?,?)";
        try {
            DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
            String date=df.format(order.getDate().getTime());
            Date docDate= df.parse(date);
            Date datoe=new Date();
            executor.executeStatement(sql,order.getReaderId(),order.getPrice(),order.getCopy_id(),datoe);
            } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void deleteOrder(Long id) {
        String sql = "DELETE FROM Orders WHERE copy_id=?";
        executor.executeStatement(sql,id);
    }

    public List<Order> getOrdersByReaderId(Long id) {
        String sql = "SELECT * FROM Orders copy WHERE readerid=?";
        List<Order> ordersId = new ArrayList<Order>();
        try {
            ResultSet resultSet = executor.getResultSet(sql,id);
            while (resultSet.next()) {
                Order order=new Order();
                order.setId(resultSet.getLong(1));
                order.setReaderId(resultSet.getLong(2));
                order.setFine(resultSet.getDouble(3));
                order.setDate(resultSet.getDate(4));
                order.setPrice(resultSet.getDouble(5));
                order.setCopy_id(resultSet.getLong(6));

                ordersId.add(order);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return ordersId;

    }


    public boolean getReaderDebt(Reader reader) {
        String sql = "SELECT * FROM Orders WHERE readerid=?";
        List<Order> orders = new ArrayList<Order>();
        try {
            ResultSet resultSet = executor.getResultSet(sql,reader.getId());
            if (resultSet.next()) {
                return true;
            } else return false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public int countOrders(Long readerId) {
        String sql = "SELECT row_count FROM Orders WHERE readerid=?";
        int count = 0;
        try {
            ResultSet resultSet = executor.getResultSet(sql,readerId);
            while (resultSet.next()) {
                count = resultSet.getInt("row_count");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;
    }
}
