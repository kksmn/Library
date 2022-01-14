package com.example.Task1.сommand.impl;

import com.example.Task1.dao.impl.BookDaoImpl;
import com.example.Task1.models.Book;
import com.example.Task1.сommand.ICommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class CommandFindBook implements ICommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookDaoImpl bookService=new BookDaoImpl();
        request.setCharacterEncoding("UTF-8");
        String name=request.getParameter("name");
        Map<Long, Book> bookMap=bookService.getBookMap(name);
        request.setAttribute("bookMap", bookMap);
        /*return "searchPage.jsp";*/
    }
}
