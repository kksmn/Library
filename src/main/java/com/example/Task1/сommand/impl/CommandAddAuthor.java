package com.example.Task1.сommand.impl;

import com.example.Task1.dao.impl.AuthorDaoImpl;
import com.example.Task1.сommand.ICommand;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CommandAddAuthor implements ICommand {
    public static final String SAVE_DIRECTORY_BOOKS = "D:\\Task1\\src\\main\\webapp\\templates\\picture\\authors";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AuthorDaoImpl authorService=new AuthorDaoImpl();
        String path=getFile(request, "path", SAVE_DIRECTORY_BOOKS);
        String name=request.getParameter("author");
        authorService.addNewAuthor(name,path);
        response.getWriter().write(name);
        /*return "addBook.jsp";*/
    }
    private String getFile(HttpServletRequest request, String partName, String directory) throws IOException, ServletException {
        String image = null;
        for (Part part : request.getParts()) {
            if (part.getName().equals(partName)) {
                if (!part.getSubmittedFileName().isEmpty()) {
                    String name = UUID.randomUUID().toString() + "." + part.getSubmittedFileName().trim().replace(" ", "");
                    image=name;
                    part.write(directory + name);
                } else {
                   image=part.getSubmittedFileName();
                }
            }
        }
        return image;
    }
}
