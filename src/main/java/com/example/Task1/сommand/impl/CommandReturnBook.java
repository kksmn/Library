package com.example.Task1.сommand.impl;

import com.example.Task1.dao.impl.BookDaoImpl;
import com.example.Task1.dao.impl.ReaderDaoImpl;
import com.example.Task1.models.Order;
import com.example.Task1.models.Reader;
import com.example.Task1.сommand.ICommand;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

public class CommandReturnBook implements ICommand {
    private static final Logger log = Logger.getLogger(String.valueOf(CommandReturnBook.class));
    public static final String SAVE_DIRECTORY_BOOKS = "D:\\Task1\\src\\main\\webapp\\templates\\picture\\";
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
            imagePath = getFile(request, "path", SAVE_DIRECTORY_BOOKS);

            Date date = new Date();
            Order order=bookDao.returnBook(bookName,reader,imagePath,rating,date);
            String json=new ObjectMapper().writeValueAsString(order);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {

        }
        log.info("New book was returned");

    }
    private List<String> getFile(HttpServletRequest request, String partName, String saveDirectory) throws IOException, ServletException {
        List<String> picture = new ArrayList<>();
        for (Part part : request.getParts()) {
            if (part.getName().equals(partName)) {
                if (!part.getSubmittedFileName().isEmpty()) {
                    String name = UUID.randomUUID().toString() + "." + part.getSubmittedFileName().trim().replace(" ", "");
                    picture.add(name);
                    part.write(saveDirectory + name);
                } else {
                    picture.add(part.getSubmittedFileName());
                }
            }
        }
        return picture;
    }
}
