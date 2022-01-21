package com.example.Task1.dao.impl;

import com.example.Task1.dao.BookDao;
import com.example.Task1.dao.pool.ConnectionPool;
import com.example.Task1.models.*;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.*;
import java.util.Date;
import java.util.concurrent.TimeUnit;


public class BookDaoImpl implements BookDao {

    private QueryExecutor executor = QueryExecutor.getInstance();

    private static final String ADD_NEW_BOOK = "INSERT INTO BOOK (russianname, originalname,count,registrationdate,countpages,year) VALUES (?, ?,?,?,?,?)";
    private static final String SELECT_FROM_BOOK = "SELECT * FROM BOOK WHERE russianname=?";
    private static final String COUNT_BOOK_ROWS = "SELECT COUNT(*) AS rowcount FROM BOOK";
    private static final String GET_BOOK_BY_ID = "SELECT russianname FROM BOOK WHERE id=?";
    private static final String UPDATE_USER = "UPDATE users SET password = ?, email = ? WHERE id = ?";
    private static final String GET_BOOK_BY_NAME = "SELECT id FROM BOOK WHERE russianname=? limit 1";
    private static final String GET_BOOKS = "SELECT * FROM Book limit ? OFFSET ?";
    private static final String GET_COPY_BY_BOOK = "SELECT bookid FROM BookCopy WHERE book_id=?";
    private static final String ADD_NEW_PHOTO = "INSERT INTO BOOKPICTURE(book_id,bookpicturepath) values (?,?)";
    GenreDaoImpl genreService;
    AuthorDaoImpl authorService;
    ReaderDaoImpl readerService;
    BookCopyDaoImpl bookCopyService;
    OrderDaoImpl orderService;

    public BookDaoImpl() {

        genreService = new GenreDaoImpl();
        authorService = new AuthorDaoImpl();
        readerService = new ReaderDaoImpl();
        bookCopyService = new BookCopyDaoImpl();
        orderService = new OrderDaoImpl();
    }

    public void addNewPicture(Long bookId, String path) {
        try {
            executor.executeStatement(ADD_NEW_PHOTO, bookId, path);

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public Map<Long, Book> getBookMap(String bookName) {
        Map<Long, Book> bookMap = new HashMap<>();
        try {
            ResultSet resultSet = executor.getResultSet(SELECT_FROM_BOOK, bookName);
            while (resultSet.next()) {
                Book book = new Book();
                List<Author> authors = new ArrayList<>();
                List<Genre> genres = new ArrayList<>();
                book.setId(resultSet.getLong("id"));
                book.setName(resultSet.getString("russianname"));
                book.setYear(resultSet.getInt("year"));
                book.setCount(resultSet.getInt("count"));
                for (Long id : authorService.findAuthorIdByBook(book.getId())) {
                    authors.add(authorService.getAuthorById(id));
                }
                for (Long id : genreService.getGenreIdByBookId(book.getId())) {
                    genres.add(genreService.getGenreById(id));
                }
                bookMap.put(book.getId(), book);
                book.setAuthors(authors);
                book.setGenres(genres);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookMap;
    }

    public List<Book> getBookList(String bookName) {
        List<Book> bookMap = new ArrayList<>();
        try {
            ResultSet resultSet = executor.getResultSet(SELECT_FROM_BOOK, bookName);
            while (resultSet.next()) {
                Book book = new Book();
                List<Author> authors = new ArrayList<>();
                List<Genre> genres = new ArrayList<>();
                book.setId(resultSet.getLong("id"));
                book.setName(resultSet.getString("russianname"));
                book.setYear(resultSet.getInt("year"));
                book.setCount(resultSet.getInt("count"));
                for (Long id : authorService.findAuthorIdByBook(book.getId())) {
                    authors.add(authorService.getAuthorById(id));
                }
                /*for (Long id : genreService.getGenreIdByBookId(book.getId())) {
                    genres.add(genreService.getGenreById(id));
                }
                bookMap.put(book.getId(), book);*/
                book.setAuthors(authors);
                book.setGenres(genres);
                bookMap.add(book);


            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookMap;
    }


    public String getBookById(Long id) {
        String bookName = null;
        try {
            ResultSet resultSet = executor.getResultSet(GET_BOOK_BY_ID, id);
            while (resultSet.next()) {
                bookName = resultSet.getString("russianname");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookName;
    }

    public Integer findAuthorIdByName(String authorName) {//MANYAUTHORS
        String sql = "SELECT id FROM AUTHORS WHERE authorname=?";
        Integer id = null;

        try (PreparedStatement stmt = ConnectionPool.getInstance().getConnection().prepareStatement(sql)) {
            stmt.setString(1, authorName);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getInt("id");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public List<Long> getBookByName(String bookName) {

        List<Long> list = new ArrayList<>();
        try {
            ResultSet resultSet = executor.getResultSet(GET_BOOK_BY_NAME, bookName);
            while (resultSet.next()) {
                list.add(resultSet.getLong("id"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }


    public Long getCopyId(String name) {
        String sql = "SELECT id FROM BookCopy WHERE book_id=?";
        Long bookId = null;
        Long copyId = null;
        try (PreparedStatement stmt = ConnectionPool.getInstance().getConnection().prepareStatement(sql)) {
           /* bookId=findBookByName(name);
             stmt.setInt(1, bookId);
            ResultSet resultSet=stmt.executeQuery();
            while(resultSet.next()){
                copyId=resultSet.getInt("id");
            }
*/
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return copyId;

    }

    public Date getDateForReading() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 1);
        return cal.getTime();
    }

    public Calendar getCurrentDate() {
        Calendar cal = Calendar.getInstance();
        return cal;
    }

    public long getDifferenceBetweenDate(Calendar lastDate) {
        Calendar currentDate = getCurrentDate();
        lastDate.set(Calendar.MILLISECOND, 0);
        lastDate.set(Calendar.MINUTE, 0);
        lastDate.set(Calendar.HOUR, 0);
        currentDate.set(Calendar.MILLISECOND, 0);
        currentDate.set(Calendar.MINUTE, 0);
        currentDate.set(Calendar.HOUR, 0);

        long remainingDays = Math.round((float) (lastDate.getTimeInMillis() - currentDate.getTimeInMillis()) / (24 * 60 * 60 * 1000));

        return remainingDays;
    }

    public Double getBookPrice(BookCopy copy) {
        Calendar calendar = getCurrentDate();
        int countOfDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        if (copy.getDamaged())
            return countOfDays * copy.getPriceForDay() * 0.6;
        else return countOfDays * copy.getPriceForDay();
    }

    public Long addNewBook(Book book) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        LocalDate date = LocalDate.now();
        int id = 0;
        try {
            con = executor.getConnection();
            ps = con.prepareStatement(ADD_NEW_BOOK, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, book.getRussianName());
            ps.setString(2, book.getOriginalName());
            ps.setInt(3, book.getCount());
            ps.setDate(4, java.sql.Date.valueOf(date));
            if (book.getCountPages() != null) {
                ps.setInt(5, book.getCountPages());
            } else {
                ps.setNull(5, Types.INTEGER);
            }
            if (book.getBookYear() != null) {
                ps.setDate(6, java.sql.Date.valueOf(book.getBookYear()));
            } else {
                ps.setNull(6, Types.DATE);
            }

            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            rs.next();
            id = rs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Long.valueOf(id);
    }


    public Map<Long, Book> getBooks(int start, int total) throws SQLException, ClassNotFoundException {

        Map<Long, Book> bookMap = new HashMap<>();
        GenreDaoImpl genreService = new GenreDaoImpl();
        try {
            ResultSet resultSet = executor.getResultSet(GET_BOOKS, start, total);
            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getLong(1));
                book.setName(resultSet.getString(2));
                book.setOriginalName(resultSet.getString(3));
                book.setCount(resultSet.getInt(4));
                book.setRegistrationDate(resultSet.getDate(5));
                book.setCountPages(resultSet.getInt(6));
                book.setYear(resultSet.getInt(7));
                List<Long> genresId = genreService.getGenreIdByBookId(book.getId());
                List<Genre> genres = new ArrayList<>();
                for (Long id : genresId) {
                    genres.add(genreService.getGenreById(id));
                }

                bookMap.put(book.getId(), book);
                book.setGenres(genres);

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookMap;
    }

    //лучше списком или по одному
    public Integer getBookByCopyId(Integer copyId) {
        Integer id = null;

        try (PreparedStatement stmt = ConnectionPool.getInstance().getConnection().prepareStatement(GET_COPY_BY_BOOK)) {
            stmt.setInt(1, copyId);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                copyId = resultSet.getInt("id");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return copyId;

    }

    public Order getBook(String bookName, Reader reader) throws SQLException, ClassNotFoundException {
        int countOrders = 0;
        Order returnOrder = new Order();
        List<BookCopy> copyList = new ArrayList<>();
        if (!orderService.getReaderDebt(reader)) {
            List<Long> list = new ArrayList<>();
            list = getBookByName(bookName);

            for (Long id : list) {
                copyList.add(bookCopyService.getCopyOfAvailableBook(id));
            }

            for (BookCopy copy : copyList) {
                if (copy.getId() != null) {
                    bookCopyService.makeBookNotAvailable(copy.getId());
                }
            }
            int ordersSize = copyList.size();

            if (ordersSize != 0) {
                for (BookCopy copy : copyList) {
                    Order order = new Order();
                    order.setReaderId(reader.getId());
                    order.setCopy_id(copy.getId());
                    order.setDate(getDateForReading());

                    if (countOrders > 4) {
                        order.setPrice(getBookPrice(copy) * 0.85);

                    } else if (countOrders > 2) {
                        order.setPrice(getBookPrice(copy) * 0.9);
                    } else order.setPrice(getBookPrice(copy));
                    //date doesnt work
                    orderService.addNewOrder(order);
                    returnOrder = order;
                }
            }
        }

        return returnOrder;
    }

    public Order returnBook(String bookName, Reader reader, List<String> images, Double rating, Date date) throws SQLException, ClassNotFoundException, ParseException {
        OrderDaoImpl orderDao = new OrderDaoImpl();
        BookCopyDaoImpl copyDao = new BookCopyDaoImpl();
        List<Order> orderList = new ArrayList<>();
        List<Long> list = new ArrayList<>();
        List<Long> copyList = new ArrayList<>();
        Order userOrder = new Order();
        orderList = orderDao.getOrdersByReaderId(reader.getId());
        list = getBookByName(bookName);
        for (Long id : list) {
            copyList.add(copyDao.getCopyByBookId(id));
        }
        if (orderList.size() != 0) {
            for (Order order : orderList) {
                for (Long id : copyList) {
                    userOrder = order;
                    if (id == order.getCopy_id()) {
                        copyDao.makeBookAvailable(order.getCopy_id());
                        /* orderDao.deleteOrder(order.getId());*/
                        if (images.size() != 0) {
                            for (String path : images) {
                                Long photoId = copyDao.setDamagedBookPhoto(path);
                                copyDao.addDamagePhoto(photoId, id);
                            }

                        }
                        if (rating != 0.0) {
                            copyDao.setRating(id, rating);
                        }
                        Date orderDate = order.getDate();
                        long diffInMillies = date.getTime() - orderDate.getTime();
                        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
                        if (diff > 0) {
                            Double price = order.getPrice();
                            order.setFine((price * 0.1) * diff);
                        }
                    }
                }

            }

        }

        return userOrder;
    }


    public int getNoOfRecords() {
        int rowcount = 0;
        try {
            ResultSet resultSet = executor.getResultSet(COUNT_BOOK_ROWS);
            while (resultSet.next()) {
                rowcount = (int) resultSet.getLong("rowcount");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowcount;
    }
}



