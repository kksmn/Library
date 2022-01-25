<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link rel="stylesheet" href="templates/css/header.css">
    <link rel="stylesheet" href="templates/css/main.css">
</head>

<body>
<div class="header">
    <a href="addBook.jsp">Add books</a>
    <a href="addReader.jsp">Add readers</a>
    <a href="getBook.jsp">Get books</a>
    <a href="returnBook.jsp">Return books</a>
    <a href="main?command=getSearchPage">Book search</a>
    <a href="main?command=getReaderTable">Reader search</a>
</div>
<div class="blok">
    <table class="table">
        <thead>
        <tr>
            <th class="first">Name</th>
            <th>Genre</th>
            <th>Publish year</th>
            <th>Quanity</th>
            <th class="second">Available</th>
        </tr>
        </thead>

        tbody id="tbody">
        <c:forEach items="${list.entrySet()}" var="book">
            <tr>
                <td>${book.getValue().getName() }</td>

                <td><c:forEach items="${book.getValue().getGenres()}" var="genre">
                    ${genre.getGenreName() } </c:forEach></td>

                <td>${book.getValue().getBookYear().getYear() }</td>
                <td>${book.getValue().getCount() }</td>
                <td>${book.getValue().getCountAvailableCopies() }</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<hr/>
<c:if test="${currentPage != 1}">
    <td><a href="main?command=getBookTable&page=${currentPage - 1}">Previous</a></td>
</c:if>

<table class="pagination" border="1" cellpadding="5" cellspacing="5" >
    <tr>
        <c:forEach begin="1" end="${noOfPages}" var="i">
            <c:choose>
                <c:when test="${currentPage eq i}">
                    <td>${i}</td>
                </c:when>
                <c:otherwise>
                    <td><a href="main?command=getBookTable&page=${i}">${i}</a></td>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </tr>
</table>
<c:if test="${currentPage lt noOfPages}">
    <td><a href="main?command=getBookTable&page=${currentPage + 1}">Next</a></td>
</c:if>
<script src="templates/js/main.js"></script>
</body>
</html>