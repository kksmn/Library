package com.example.Task1.сommand.impl.Library;

import com.example.Task1.dao.impl.AuthorDaoImpl;
import com.example.Task1.сommand.ICommand;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

public class CommandAddAuthor implements ICommand {
    private static final Logger LOGGER = LogManager.getLogger(CommandAddAuthor.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            AuthorDaoImpl authorService = new AuthorDaoImpl();
            String path = getFile(request, "path");
            String name = request.getParameter("author");
            authorService.addNewAuthor(name, path);
            response.getWriter().write(name);
        } catch (Exception e) {
            LOGGER.error("Error :" + e.getMessage());
        }
    }

    private String getFile(HttpServletRequest request, String partName) throws IOException, ServletException {
        String image = null;
        try {
            for (Part part : request.getParts()) {
                if (part.getName().equals(partName)) {
                    if (!part.getSubmittedFileName().isEmpty()) {
                        String name = part.getSubmittedFileName().trim().replace(" ", "");
                        image = name;
                    } else {
                        image = part.getSubmittedFileName();
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error("Error :" + e.getMessage());
        }
        return image;
    }
}
