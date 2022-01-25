package com.example.Task1.сommand.impl.Library;

import com.example.Task1.dao.impl.BookDaoImpl;
import com.example.Task1.dao.impl.ReaderDaoImpl;
import com.example.Task1.models.Order;
import com.example.Task1.models.Reader;
import com.example.Task1.сommand.ICommand;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommandReturnBook implements ICommand {
    private static final Logger LOGGER = LogManager.getLogger(CommandReturnBook.class);
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Double rating = 0.0;
            BookDaoImpl bookDao=new BookDaoImpl();
            if (!request.getParameter("rating").equals("")) {
                rating = Double.valueOf(request.getParameter("rating"));
            }
            ReaderDaoImpl readerDao=new ReaderDaoImpl();
            Reader reader=readerDao.findReader(request.getParameter("email"));
            String bookName = request.getParameter("rusName");
            List<String> imagePath = new ArrayList<>();
            imagePath = getFile(request, "path");

            Date date = new Date();
            Order order=bookDao.returnBook(bookName,reader,imagePath,rating,date);
            String json=new ObjectMapper().writeValueAsString(order);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        }catch (SQLException | ClassNotFoundException |ParseException e) {
            LOGGER.error("Error :" + e.getMessage());
        }

    }
    private List<String> getFile(HttpServletRequest request, String partName) throws IOException, ServletException {
        List<String> picture = new ArrayList<>();
        for (Part part : request.getParts()) {
            if (part.getName().equals(partName)) {
                if (!part.getSubmittedFileName().isEmpty()) {
                    String name =  part.getSubmittedFileName().trim().replace(" ", "");
                    picture.add(name);
                } else {
                    picture.add(part.getSubmittedFileName());
                }
            }
        }
        return picture;
    }
}
