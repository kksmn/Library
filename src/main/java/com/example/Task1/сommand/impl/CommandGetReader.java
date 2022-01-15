package com.example.Task1.сommand.impl;

import com.example.Task1.dao.impl.ReaderDaoImpl;
import com.example.Task1.models.Reader;
import com.example.Task1.сommand.ICommand;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommandGetReader implements ICommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ReaderDaoImpl readerService=new ReaderDaoImpl();
            String email=(request.getParameter("email"));
            Reader reader=readerService.findReader(email);
            request.setAttribute("reader", reader);
            String json=new ObjectMapper().writeValueAsString(reader);
            System.out.println(json);
            List<String>list=new ArrayList<String>() ;
            list.add(reader.getFirstName());
            list.add(reader.getLastName());
            response.setContentType("application/json");  // Set content type of the response so that jQuery knows what it can expect.
            response.setCharacterEncoding("UTF-8"); // You want world domination, huh?
            response.getWriter().write(json);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
/*        RequestDispatcher requestDispatcher = request.getRequestDispatcher("getBook.jsp");
        requestDispatcher.forward(request, response);*/
     }
}
