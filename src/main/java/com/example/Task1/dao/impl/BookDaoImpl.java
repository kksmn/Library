package com.example.Task1.dao.impl;

import com.example.Task1.dao.BookDao;
import com.example.Task1.dao.executor.QueryExecutor;
import com.example.Task1.dao.pool.ConnectionPool;
import com.example.Task1.models.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.*;
import java.util.Date;
import java.util.concurrent.TimeUnit;


public class BookDaoImpl implements BookDao {

    private QueryExecutor executor = QueryExecutor.getInstance();
    private static final Logger LOGGER = LogManager.getLogger(BookDaoImpl.class);


    private static final String GET_GENRE_ID_BY_BOOK = "SELECT genre_id FROM GENRE_BOOK where book_id=?";
    private static final String ADD_NEW_BOOK = "INSERT INTO BOOK (russianname, originalname,count,registrationdate,countpages,year) VALUES (?, ?,?,?,?,?)";
    private static final String SELECT_FROM_BOOK = "SELECT id,russianname,count,year FROM BOOK WHERE russianname=? limit ? OFFSET ? ";
    private static final String COUNT_BOOK_ROWS = "SELECT COUNT(*) AS rowcount FROM BOOK";
    private static final String COUNT_BOOK_ROWS_BY_NAME = "SELECT COUNT(*) AS rowcount FROM BOOK WHERE russianname=?";
    private static final String GET_BOOK_BY_ID = "SELECT russianname FROM BOOK WHERE id=?";
    private static final String UPDATE_USER = "UPDATE users SET password = ?, email = ? WHERE id = ?";
    private static final String GET_BOOK_BY_NAME = "SELECT id FROM BOOK WHERE russianname=?";
    private static final String GET_BOOKS = "SELECT id,russianname,count,year FROM Book limit ? OFFSET ? ";
    private static final String GET_COPY_BY_BOOK = "SELECT bookid FROM BookCopy WHERE book_id=?";
    private static final String ADD_NEW_PHOTO = "INSERT INTO BOOKPICTURE(book_id,bookpicturepath) values (?,?)";
    private static final String GET_GENRE_BY_ID = "SELECT * FROM Genres  WHERE id=?";
    private static final String GET_COPY_OF_AVAILABLE_BOOK = "SELECT * FROM BOOKCOPY WHERE bookid=? AND isavailable=true ";
    private static final String MAKE_BOOK_NOT_AVAILABLE = "Update BookCopy SET isavailable=false WHERE id = ?";
    private static final String COUNT_COPY_ROWS = "SELECT COUNT(*) AS rowcount FROM BOOKCOPY WHERE bookid=  ? AND isavailable=true";
    private static final String GET_COPY_ID = "SELECT id FROM BookCopy WHERE book_id=?";

    public Genre getGenreById(Long id) {
        Genre genre = new Genre();
        try {
            ResultSet resultSet = executor.getResultSet(GET_GENRE_BY_ID,id);
            while (resultSet.next()) {
                genre.setId(id);
                genre.setGenreName(resultSet.getString("genrename"));
            }

        } catch (SQLException e) {
            LOGGER.error("Error :" + e.getMessage());
        }
        return genre;

    }

    public void addNewPicture(Long bookId, String path) {
        try {
            executor.executeStatement(ADD_NEW_PHOTO, bookId, path);

        } catch (Exception e) {
            LOGGER.error("Error :" + e.getMessage());
        }
    }
    public List<Long> getGenreIdByBookId(Long bookId) {
        List<Long> genreId = new ArrayList<>();
        try {
            ResultSet resultSet = executor.getResultSet(GET_GENRE_ID_BY_BOOK,bookId);
            while (resultSet.next()) {
                genreId.add(resultSet.getLong("genre_id"));
            }

        } catch (SQLException e) {
            LOGGER.error("Error :" + e.getMessage());
        }
        return genreId;

    }
    public Map<Long, Book> getBookMap(String bookName,int start, int total) {
        Map<Long, Book> bookMap = new HashMap<>();
        try {
            ResultSet resultSet = executor.getResultSet(SELECT_FROM_BOOK, bookName,start,total);
            while (resultSet.next()) {
                Book book = new Book();
                List<Author> authors = new ArrayList<>();
                List<Genre> genres = new ArrayList<>();
                book.setId(resultSet.getLong(1));
                book.setName(resultSet.getString(2));
                book.setCount(resultSet.getInt(3));
                if (resultSet.getDate(4) != null) {
                    book.setBookYear(resultSet.getDate(4).toLocalDate());
                }

                for (Long id : getGenreIdByBookId(book.getId())) {
                    genres.add(getGenreById(id));
                }
                bookMap.put(book.getId(), book);
                book.setAuthors(authors);
                book.setGenres(genres);

            }

        } catch (SQLException e) {
            LOGGER.error("Error :" + e.getMessage());
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

        } catch (Exception e) {
            LOGGER.error("Error :" + e.getMessage());
        }
        return bookName;
    }

    public List<Long> getBookByName(String bookName) {

        List<Long> list = new ArrayList<>();
        try {
            ResultSet resultSet = executor.getResultSet(GET_BOOK_BY_NAME, bookName);
            while (resultSet.next()) {
                list.add(resultSet.getLong("id"));
            }
        }catch (Exception e) {
            LOGGER.error("Error :" + e.getMessage());
        }
        return list;
    }


    public Long getCopyId(String name) {

        Long copyId = null;
        try {
            executor.executeStatement(GET_COPY_ID);
        } catch (Exception e) {
            LOGGER.error("Error :" + e.getMessage());
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

    public Double getBookPrice(BookCopy copy) {
        Calendar calendar = getCurrentDate();
        int countOfDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        return countOfDays * copy.getPriceForDay();
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
        } catch (SQLException e) {
            LOGGER.error("Error :" + e.getMessage());
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
                book.setRussianName(resultSet.getString(2));
                book.setCount(resultSet.getInt(3));
                if (resultSet.getDate(4) != null) {
                    book.setBookYear(resultSet.getDate(4).toLocalDate());
                }
                List<Long> genresId = getGenreIdByBookId(book.getId());
                List<Genre> genres = new ArrayList<>();
                for (Long id : genresId) {
                    genres.add(getGenreById(id));
                }

                bookMap.put(book.getId(), book);
                book.setGenres(genres);

            }


        }catch (SQLException e) {
            LOGGER.error("Error :" + e.getMessage());
        }
        return bookMap;
    }

    public Integer getBookByCopyId(Integer copyId) {
        Integer id = null;

        try (PreparedStatement stmt = ConnectionPool.getInstance().getConnection().prepareStatement(GET_COPY_BY_BOOK)) {
            stmt.setInt(1, copyId);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                copyId = resultSet.getInt("id");
            }

        } catch (SQLException e) {
            LOGGER.error("Error :" + e.getMessage());
        }
        return copyId;

    }
    public int countAvailableCopy(Long id) {
        int amount=0;
        try{
            ResultSet resultSet=executor.getResultSet(COUNT_COPY_ROWS,id);
            while(resultSet.next()){
                amount=resultSet.getInt("rowcount");
            }
        }catch (SQLException e) {
            LOGGER.error("Error :" + e.getMessage());
        }
        return amount;
    }
    public ConfirmedOrder getBook(List<String> bookNames, Reader reader) throws SQLException, ClassNotFoundException {
        int countOrders = 0;
        ConfirmedOrder returnOrder=new ConfirmedOrder();
        OrderDaoImpl orderService=new OrderDaoImpl();
        List<String>books=new ArrayList<>();
        double totalPrice = 0;
        BookCopy copy;
        List<BookCopy> copyList = new ArrayList<>();
        try {
            for (String name : bookNames) {
                List<Long> list = getBookByName(name);
                for (Long id : list) {
                    copy = getCopyOfAvailableBook(id);
                    if (copy.getId() != null) {
                        copyList.add(copy);
                        books.add(name);
                        break;
                    }

                }
            }
            if (copyList.size() != 0) {
                for (BookCopy bookCopy : copyList) {
                    if (bookCopy.getId() != null) {
                        makeBookNotAvailable(bookCopy.getId());
                    }
                }
                countOrders = copyList.size();
                for (BookCopy bookCopy : copyList) {
                    Order order = new Order();
                    order.setReaderId(reader.getId());
                    order.setCopy_id(bookCopy.getId());
                    order.setDate(getDateForReading());

                    if (countOrders > 4) {
                        order.setPrice(getBookPrice(bookCopy) * 0.85);

                    } else if (countOrders > 2) {
                        order.setPrice(getBookPrice(bookCopy) * 0.9);
                    } else order.setPrice(getBookPrice(bookCopy));
                    orderService.addNewOrder(order);
                    totalPrice += order.getPrice();
                    Format dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String strDate = dateFormat.format(order.getDate());
                    returnOrder.setDateFormatString(strDate);

                }
                returnOrder.setBookNames(books);
                returnOrder.setPrice(totalPrice);

            }
        }catch (Exception e) {
            LOGGER.error("Error :" + e.getMessage());
        }
        return returnOrder;


    }
    public BookCopy getCopyOfAvailableBook(Long id) {
        BookCopy copy=new BookCopy();
        try{
            ResultSet resultSet=executor.getResultSet(GET_COPY_OF_AVAILABLE_BOOK,id);
            while(resultSet.next()){
                copy.setId(resultSet.getLong("id"));
                copy.setBook_id(resultSet.getLong("bookid"));
                copy.setDamaged(resultSet.getBoolean("isdamaged"));
                copy.setAvailable(resultSet.getBoolean("isavailable"));
                copy.setPrice(resultSet.getDouble("price"));
                copy.setPriceForDay(resultSet.getDouble("priceforday"));
            }

        }catch (SQLException e) {
            LOGGER.error("Error :" + e.getMessage());
        }
        return copy;
    }
    public Order returnBook(String bookName, Reader reader, List<String> images, Double rating, Date date) throws SQLException, ClassNotFoundException, ParseException {
        OrderDaoImpl orderDao = new OrderDaoImpl();
        BookCopyDaoImpl copyDao = new BookCopyDaoImpl();
        List<Order> orderList = new ArrayList<>();
        List<Long> list = new ArrayList<>();
        List<Long> copyList = new ArrayList<>();
        Order userOrder = new Order();
        try {
            orderList = orderDao.getOrdersByReaderId(reader.getId());
            list = getBookByName(bookName);
            for (Long id : list) {
                copyList.add(copyDao.getCopyByBookId(id));
            }
            if (orderList.size() != 0) {
                for (Order order : orderList) {
                    for (Long id : copyList) {
                        if (id.equals(order.getCopy_id())) {
                            userOrder = order;
                            copyDao.makeBookAvailable(order.getCopy_id());
                            orderDao.deleteOrder(order.getCopy_id());
                            if (images.size() != 0) {
                                for (String path : images) {
                                    Long photoId = copyDao.setDamagedBookPhoto(path);
                                    copyDao.addDamagePhoto(order.getCopy_id(), photoId);
                                    copyDao.makeCopyDamaged(id);
                                    copyDao.setDamagedPrice(id);
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
        }
        catch (Exception e) {
            LOGGER.error("Error :" + e.getMessage());
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

        }catch (SQLException e) {
            LOGGER.error("Error :" + e.getMessage());
        }
        return rowcount;

    }
    public int getNoOfRecordsByName(String name) {
        int rowcount = 0;
        try {
            ResultSet resultSet = executor.getResultSet(COUNT_BOOK_ROWS_BY_NAME,name);
            while (resultSet.next()) {
                rowcount = (int) resultSet.getLong("rowcount");
            }

        } catch (SQLException e) {
            LOGGER.error("Error :" + e.getMessage());
        }
        return rowcount;

    }
    public void makeBookNotAvailable(Long id){
        try {
            executor.executeStatement(MAKE_BOOK_NOT_AVAILABLE,id);
        } catch (Exception e) {
            LOGGER.error("Error :" + e.getMessage());
        }
    }
}



