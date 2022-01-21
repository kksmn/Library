package com.example.Task1.сommand.impl;

import com.example.Task1.controller.MainController;
import com.example.Task1.dao.impl.AuthorDaoImpl;
import com.example.Task1.dao.impl.BookCopyDaoImpl;
import com.example.Task1.dao.impl.BookDaoImpl;
import com.example.Task1.dao.impl.GenreDaoImpl;
import com.example.Task1.models.Author;
import com.example.Task1.models.Book;
import com.example.Task1.models.Genre;
import com.example.Task1.сommand.ICommand;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

public class CommandAddBook implements ICommand {
    private static final Logger log = Logger.getLogger(String.valueOf(CommandAddBook.class));

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        List<Genre> genreList;
        List<Long> authorList = new ArrayList<>();
        GenreDaoImpl genreService=new GenreDaoImpl();
        AuthorDaoImpl authorService=new AuthorDaoImpl();
        BookDaoImpl bookService=new BookDaoImpl();
        BookCopyDaoImpl bookCopyService=new BookCopyDaoImpl();
        Book book = new Book();

        if (!request.getParameter("originalName").equals("")) {
            book.setOriginalName(request.getParameter("originalName"));
        }
        if (!request.getParameter("countPages").equals("")) {
            book.setCountPages(Integer.valueOf(request.getParameter("countPages")));
        }
        if (!request.getParameter("year").equals("")) {
            book.setRegDate(LocalDate.parse(request.getParameter("year") + "-12-12"));
        }
        String[] images = request.getParameterValues("image");
        String[] genres = request.getParameterValues("genre");
        String[] authors = request.getParameterValues("authorName");
        Double price = Double.valueOf(request.getParameter("price"));
        Double priceForDay = Double.valueOf(request.getParameter("priceForDay"));

        book.setName(request.getParameter("russianName"));
        book.setCount(Integer.valueOf(request.getParameter("count")));
        book.setImages(Arrays.asList(images));

        genreList=genreService.addNewGenres(genres);
        book.setGenres(genreList);
        Long bookId = bookService.addNewBook(book);
        genreService.addGenreToBook(bookId,genreList);
        for (int i=0;i<authors.length;i++) {
            authorList.add(authorService.getAuthorByName(authors[i]));
        }
        authorService.addAuthorToBook(bookId,authorList);
        for(int i=0;i<book.getCount();i++) {
            bookCopyService.addNewCopy(bookId, price, priceForDay);
        }
        for (int i=0;i<images.length;i++) {
            bookService.addNewPicture(bookId,images[i]);
        }
        log.info("New book was added");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("mainPage.jsp");
        requestDispatcher.forward(request, response);
    }
    private List<String> getImage(HttpServletRequest request, String partName, String saveDirectory) throws IOException, ServletException {
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
