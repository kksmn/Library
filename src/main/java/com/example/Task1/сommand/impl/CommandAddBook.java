package com.example.Task1.сommand.impl;

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
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandAddBook implements ICommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        GenreDaoImpl genreService=new GenreDaoImpl();
        AuthorDaoImpl authorService=new AuthorDaoImpl();
        BookDaoImpl bookService=new BookDaoImpl();
        BookCopyDaoImpl bookCopyService=new BookCopyDaoImpl();
        request.setCharacterEncoding("UTF-8");
        Book book = new Book();
        List<Genre> genreList;
        List<Long> authorList = new ArrayList<>();
        String path = request.getParameter("image");

        String[] genres = request.getParameterValues("genre");
        String[] authors = request.getParameterValues("authorName");
      //  String[] authorImage = request.getParameterValues("authorImage");

        book.setName(request.getParameter("russianName"));
        book.setOriginalName(request.getParameter("originalName"));
        book.setCount(Integer.valueOf(request.getParameter("count")));
        book.setYear(Integer.valueOf(request.getParameter("year")));
        book.setCountPages(Integer.valueOf(request.getParameter("countPages")));
        Double price = Double.valueOf(request.getParameter("price"));
        Double priceForDay = Double.valueOf(request.getParameter("priceForDay"));
        genreList=genreService.addNewGenres( genres);
        book.setGenres(genreList);
        //authorList=authorService.addAuthorToList( authors, authorImage);
        Long bookId = bookService.addNewBook(book, path);
        genreService.addGenreToBook(bookId,genreList);
        for (int i=0;i<authors.length;i++) {
            authorList.add(authorService.getAuthorByName(authors[i]));
        }
        authorService.addAuthorToBook(bookId,authorList);
        for(int i=0;i<book.getCount();i++) {
            bookCopyService.addNewCopy(bookId, price, priceForDay);
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("mainPage.jsp");
        requestDispatcher.forward(request, response);

    }
}
