package com.example.Task1.сommand.impl.Library;

import com.example.Task1.dao.impl.AuthorDaoImpl;
import com.example.Task1.dao.impl.BookCopyDaoImpl;
import com.example.Task1.dao.impl.BookDaoImpl;
import com.example.Task1.dao.impl.GenreDaoImpl;
import com.example.Task1.models.Book;
import com.example.Task1.models.Genre;
import com.example.Task1.сommand.ICommand;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandAddBook implements ICommand {
    private static final Logger LOGGER = LogManager.getLogger(CommandAddBook.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");

            List<Genre> genreList;
            List<Long> authorList = new ArrayList<>();
            GenreDaoImpl genreService = new GenreDaoImpl();
            AuthorDaoImpl authorService = new AuthorDaoImpl();
            BookDaoImpl bookService = new BookDaoImpl();
            BookCopyDaoImpl bookCopyService = new BookCopyDaoImpl();
            Book book = new Book();

            if (!request.getParameter("originalName").equals("")) {
                book.setOriginalName(request.getParameter("originalName"));
            }
            if (!request.getParameter("countPages").equals("")) {
                book.setCountPages(Integer.valueOf(request.getParameter("countPages")));
            }
            if (!request.getParameter("year").equals("")) {
                book.setBookYear(LocalDate.parse(request.getParameter("year") + "-12-12"));
            }
            String[] images = request.getParameterValues("image");
            String[] genres = request.getParameterValues("genre");
            String[] authors = request.getParameterValues("authorName");
            Double price = Double.valueOf(request.getParameter("price"));
            Double priceForDay = Double.valueOf(request.getParameter("priceForDay"));

            book.setName(request.getParameter("russianName"));
            book.setCount(Integer.valueOf(request.getParameter("count")));
            book.setImages(Arrays.asList(images));

            genreList = genreService.addNewGenres(genres);
            book.setGenres(genreList);
            Long bookId = bookService.addNewBook(book);
            genreService.addGenreToBook(bookId, genreList);
            for (int i = 0; i < authors.length; i++) {
                authorList.add(authorService.getAuthorByName(authors[i]));
            }
            authorService.addAuthorToBook(bookId, authorList);
            for (int i = 0; i < book.getCount(); i++) {
                bookCopyService.addNewCopy(bookId, price, priceForDay);
            }
            for (int i = 0; i < images.length; i++) {
                bookService.addNewPicture(bookId, images[i]);
            }
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("main?command=getBookTable");
            requestDispatcher.forward(request, response);
        } catch (Exception e) {
            LOGGER.error("Error :" + e.getMessage());
        }
    }

}
