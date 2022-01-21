package com.example.Task1.сommand.impl;

import com.example.Task1.Configs;
import com.example.Task1.dao.impl.BookDaoImpl;
import com.example.Task1.dao.impl.ReaderDaoImpl;
import com.example.Task1.models.ConfirmedOrder;
import com.example.Task1.models.Order;
import com.example.Task1.models.Reader;
import com.example.Task1.сommand.ICommand;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommandGetBook implements ICommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        BookDaoImpl bookService=new BookDaoImpl();
        Double price=null;
        ConfirmedOrder confirmedOrder=new ConfirmedOrder();
        try {
            ReaderDaoImpl readerDao=new ReaderDaoImpl();
            Reader reader=readerDao.findReader(request.getParameter("email"));
            String[] bookNames = request.getParameterValues("bookName");
            confirmedOrder=bookService.getBook(bookNames, reader);
            String json = new ObjectMapper().writeValueAsString(confirmedOrder);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
      /*  return "mainPage.jsp";*/
    }
}
