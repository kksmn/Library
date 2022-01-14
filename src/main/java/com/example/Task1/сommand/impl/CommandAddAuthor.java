package com.example.Task1.сommand.impl;

import com.example.Task1.dao.impl.AuthorDaoImpl;
import com.example.Task1.models.Author;
import com.example.Task1.сommand.ICommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CommandAddAuthor implements ICommand {
    //directory
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AuthorDaoImpl authorService=new AuthorDaoImpl();
        authorService.addNewAuthor(request.getParameter("authorName"),request.getParameter("path"));
        /*return "addBook.jsp";*/
    }
}
