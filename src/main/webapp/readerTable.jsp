<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>MyLibrary</title>
    <link rel="stylesheet" href="templates/css/header.css">
    <link rel="stylesheet" href="templates/css/readers.css">
</head>
<body>
<div class="header">
    <a href="addBook.jsp">Add books</a>
    <a href="addReader.jsp">Add readers</a>
    <a href="getBook.jsp">Get books</a>
    <a href="returnBook.jsp">Return books</a>
    <a  href="main?command=getSearchPage">Book search</a>
    <a class="currentPage" href="main?command=getReaderTable">Reader search</a>
</div>
<br>
<a href="main?command=getBookTable"><img src="templates/image/img_3.png" class="image"></a>
<form action="main" method="post" class="form">
    <input class="searchLine" placeholder="Enter email " type="search" name="email"/>
    <input type="hidden" name="command" value="getReaderTable"/>
    <input type="submit" class="searchButton" value="Search"/>
</form>
<div class="blok">
<table id="table" class="table">
    <thead>
    <tr id="zag">
        <th class="first">Last name</th>
        <th>First name</th>
        <th>Date</th>
        <th>Address</th>
        <th>Email</th>
    </tr>
    </thead>
    <tbody>
        <c:forEach items="${list}" var="book">
    <tr>
        <td>${book.getLastName()}</td>
        <td>${book.getFirstName() }</td>
        <td>${book.getDate()}</td>
        <td>${book.getAddress()}</td>
        <td>${book.getEmail()}</td>

    </c:forEach>
    </tr>
    </tbody>
</table>
</div>
<hr/>
<c:if test="${currentPage != 1}">
    <td><a href="main?command=getReaderTable&page=${currentPage - 1}&email=${email}">Previous</a></td>
</c:if>

<table border="1" cellpadding="5" cellspacing="5">
    <tr>
        <c:forEach begin="1" end="${noOfPages}" var="i">
            <c:choose>
                <c:when test="${currentPage eq i}">
                    <td>${i}</td>
                </c:when>
                <c:otherwise>
                    <td><a href="main?command=getReaderTable&page=${i}&email=${email}">${i}</a></td>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </tr>
</table>
<c:if test="${currentPage lt noOfPages}">
    <td><a href="main?command=getReaderTable&page=${currentPage + 1}&email=${email}">Next</a></td>
</c:if>


<script>
    window.addEventListener("DOMContentLoaded", function () {
        (function (f) {
            function g(c) {
                return function (b, a) {
                    b = b.cells[c].textContent;
                    a = a.cells[c].textContent;
                    b = +b || b;
                    a = +a || a;
                    return b > a ? 1 : b < a ? -1 : 0
                }
            }

            var d = document.querySelector(f),
                e = [].slice.call(d.rows, 1);
            [].slice.call(d.rows[0].cells).forEach(function (c, b) {
                var a = 0;
                c.addEventListener("click", function () {
                    e.sort(g(b));
                    a && e.reverse();
                    e.forEach(function (a) {
                        d.appendChild(a)
                    });
                    a ^= 1
                })
            })
        })(".table")
    });
</script>
</body>

</html>


