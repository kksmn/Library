package com.example.Task1.сommand.impl;

import com.example.Task1.dao.impl.ReaderDaoImpl;
import com.example.Task1.models.Reader;
import com.example.Task1.сommand.ICommand;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CommandGetReaderTable implements ICommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ReaderDaoImpl readerService=new ReaderDaoImpl();
            int page = 1;
            int n;
            int recordsPerPage = 20;
            if(request.getParameter("page") != null)
                page = Integer.parseInt(request.getParameter("page"));

            if(request.getParameter("email") != null && request.getParameter("email") != "" ) {
                String email=request.getParameter("email");
                List<Reader> list = readerService.getReadersByEmail(email);
                int noOfRecords = 1;
                int noOfPages = 1;
                request.setAttribute("noOfPages", noOfPages);
                request.setAttribute("currentPage", page);
                request.setAttribute("email", email);
                request.setAttribute("list", list);
            }
            else {
                List<Reader> list = readerService.getReaders(recordsPerPage,
                        (page - 1) * recordsPerPage);
                int noOfRecords = readerService.getNoOfRecords();
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
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("readerTable.jsp");
        requestDispatcher.forward(request, response);
    }
}
