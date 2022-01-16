package com.example.Task1.сommand.impl;

import com.example.Task1.dao.impl.BookDaoImpl;
import com.example.Task1.dao.impl.ReaderDaoImpl;
import com.example.Task1.models.Reader;
import com.example.Task1.сommand.ICommand;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class CommandReturnBook implements ICommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Double rating = 0.0;
            BookDaoImpl bookDao=new BookDaoImpl();
            if (!request.getParameter("rating").equals("")) {
                rating = Double.valueOf(request.getParameter("rating"));
            }
            ReaderDaoImpl readerDao=new ReaderDaoImpl();
            Reader reader=readerDao.findReader(request.getParameter("email"));
            String[] bookNames = request.getParameterValues("rusname");
            String imagePath ="";
            if (!request.getParameter("path").equals("")) {
                imagePath = request.getParameter("path");
            }
            bookDao.returnBook(bookNames,reader,imagePath,rating);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {

        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("returnBook.jsp");
        requestDispatcher.forward(request, response);
    }
}
