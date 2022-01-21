package com.example.Task1.сommand.impl;

import com.example.Task1.dao.impl.BookCopyDaoImpl;
import com.example.Task1.dao.impl.BookDaoImpl;
import com.example.Task1.models.Book;
import com.example.Task1.сommand.ICommand;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

public class CommandGetSearchPage implements ICommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            BookDaoImpl bookService=new BookDaoImpl();
            BookCopyDaoImpl copyService=new BookCopyDaoImpl();
            int page = 1;
            int recordsPerPage = 20;
            if(request.getParameter("page") != null)
                page = Integer.parseInt(request.getParameter("page"));
            if (request.getParameter("name")!= null && request.getParameter("name")!= "")
            {
                String name=request.getParameter("name");
                Map<Long, Book> list = bookService.getBookMap(name,recordsPerPage,
                        (page-1)*recordsPerPage);
                for (Map.Entry<Long, Book> item : list.entrySet()) {
                    item.getValue().setCountAvailableCopies(copyService.countAvailableCopy(item.getValue().getId()));
                }
                int noOfRecords = bookService.getNoOfRecords();
                int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
                request.setAttribute("noOfPages", noOfPages);
                request.setAttribute("name", name);
                request.setAttribute("currentPage", page);
                request.setAttribute("list", list);
            }
            else {
                Map<Long, Book> list = bookService.getBooks(recordsPerPage,
                        (page - 1) * recordsPerPage);
                for (Map.Entry<Long, Book> item : list.entrySet()) {
                    item.getValue().setCountAvailableCopies(copyService.countAvailableCopy(item.getValue().getId()));
                }
                int noOfRecords = bookService.getNoOfRecords();
                int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
                request.setAttribute("noOfPages", noOfPages);
                request.setAttribute("currentPage", page);
                request.setAttribute("list", list);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("searchPage.jsp");
        requestDispatcher.forward(request, response);
    }
}
