package com.example.Task1.сommand.impl.Library;

import com.example.Task1.dao.impl.BookDaoImpl;
import com.example.Task1.dao.impl.ReaderDaoImpl;
import com.example.Task1.models.ConfirmedOrder;
import com.example.Task1.models.Reader;
import com.example.Task1.сommand.ICommand;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

public class CommandGetBook implements ICommand {
    private static final Logger LOGGER = LogManager.getLogger(CommandGetBook.class);
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
            Set<String> set = new HashSet<String>(Arrays.asList(bookNames));
            List<String> list=new ArrayList<String>();
            list.addAll(set);
            confirmedOrder=bookService.getBook(list, reader);
            String json = new ObjectMapper().writeValueAsString(confirmedOrder);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);

        }catch (SQLException | ClassNotFoundException e) {
            LOGGER.error("Error :" + e.getMessage());
        }
    }
}
