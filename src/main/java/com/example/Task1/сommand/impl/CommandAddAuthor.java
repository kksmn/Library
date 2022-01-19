package com.example.Task1.сommand.impl;

import com.example.Task1.dao.impl.AuthorDaoImpl;
import com.example.Task1.сommand.ICommand;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommandAddAuthor implements ICommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AuthorDaoImpl authorService=new AuthorDaoImpl();
        String path=request.getParameter("path");
        String name=request.getParameter("author");
        authorService.addNewAuthor(name,path);
        response.getWriter().write(name);
        /*return "addBook.jsp";*/
    }
}
