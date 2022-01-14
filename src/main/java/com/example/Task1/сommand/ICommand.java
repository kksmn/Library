package com.example.Task1.сommand;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ICommand {

    void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
