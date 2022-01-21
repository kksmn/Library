package com.example.Task1.сommand.impl;

import com.example.Task1.dao.OrderDao;
import com.example.Task1.dao.impl.OrderDaoImpl;
import com.example.Task1.dao.impl.ReaderDaoImpl;
import com.example.Task1.models.Reader;
import com.example.Task1.сommand.ICommand;
import com.fasterxml.jackson.databind.ObjectMapper;

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
               String json = new ObjectMapper().writeValueAsString(reader);
               response.setContentType("application/json");
               response.setCharacterEncoding("UTF-8");
               response.getWriter().write(json);
           }
           else {
               Reader returnReader =new Reader();
               String json = new ObjectMapper().writeValueAsString(returnReader);
               response.setContentType("application/json");
               response.setCharacterEncoding("UTF-8");
               response.getWriter().write(json);
           }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
       /* return null;*/
    }
}
