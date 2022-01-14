package com.example.Task1.сommand.impl;

import com.example.Task1.dao.OrderDao;
import com.example.Task1.dao.impl.OrderDaoImpl;
import com.example.Task1.dao.impl.ReaderDaoImpl;
import com.example.Task1.models.Reader;
import com.example.Task1.сommand.ICommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class CommandCheckUser implements ICommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderDaoImpl orderService=new OrderDaoImpl();
        ReaderDaoImpl readerService=new ReaderDaoImpl();
        try {
            Reader reader=readerService.findReader(request.getParameter("email"));
           if (!orderService.getReaderDebt(reader)){
              /* return null;*/
           }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
       /* return null;*/
    }
}
