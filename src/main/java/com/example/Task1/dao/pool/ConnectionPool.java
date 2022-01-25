package com.example.Task1.dao.pool;

import com.example.Task1.Config;
import com.example.Task1.dao.impl.AuthorDaoImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool extends Config {
    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);

    private ConnectionPool(){
    }

    private static ConnectionPool instance = null;

    public static ConnectionPool getInstance(){
        if (instance==null)
            instance = new ConnectionPool();
        return instance;
    }
    public Connection getConnection(){
        Context ctx;
        Connection c = null;
        try {
            ctx = new InitialContext();
            DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/mydb");
            c = ds.getConnection();
        } catch (SQLException | NamingException e) {
            LOGGER.error("Error :" + e.getMessage());
        }
        return c;
    }
}
