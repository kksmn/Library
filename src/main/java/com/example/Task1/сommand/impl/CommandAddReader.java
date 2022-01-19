package com.example.Task1.сommand.impl;

import com.example.Task1.dao.impl.ReaderDaoImpl;
import com.example.Task1.models.Reader;
import com.example.Task1.сommand.ICommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Logger;

public class CommandAddReader implements ICommand {
    private static final Logger log = Logger.getLogger(String.valueOf(CommandAddReader.class));

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ReaderDaoImpl readerService = new ReaderDaoImpl();
            Reader reader = new Reader();
            reader.setFirstName(request.getParameter("firstName"));
            reader.setLastName(request.getParameter("lastName"));
            reader.setEmail(request.getParameter("email"));
            if (!request.getParameter("patronymic").equals("")) {
                reader.setPatronymic(request.getParameter("patronymic"));
            }
            reader.setDate(Date.valueOf(request.getParameter("birthDate")));
            reader.setEmail(request.getParameter("email"));
            if (!request.getParameter("address").equals("")) {
                reader.setAddress(request.getParameter("address"));
            }
            reader.setPassportNumber(request.getParameter("passportNumber"));
            if (!request.getParameter("image").equals("")) {
                reader.setPhotoPath(request.getParameter("image"));
            }
            readerService.addReader(reader);
            log.info("New book was added");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
      /*  return "mainPage.jsp";*/
    }
}

