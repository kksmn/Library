package com.example.Task1.controller;

import com.example.Task1.сommand.ICommand;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@MultipartConfig
public class MainController extends HttpServlet {
    private static final long serialVersionUID = 1L;/*
private static final Logger LOGGER = Logger.getLogger(Controller.class);*/

    private void process(final HttpServletRequest request, final HttpServletResponse response)
            throws IOException, ServletException {
        ICommand command = MainControllerHelper.getInstance().getCommand(request);
        command.execute(request, response);
    }

    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
/*LOGGER.info("===================GET works");*/
        request.setCharacterEncoding("UTF-8");
        process(request, response);
    }
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        process(request, response);
    }
}
