<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>MyLibrary</title>
    <link rel="stylesheet" href="templates/css/mainPage.css">
    <script src="templates/js/findBook.js"></script>
</head>
<body>
<form action="main" method="post">
    <Label>Name</Label>
    <input type="search" name="name"/>
    <input type="hidden" name="command" value="getSearchPage"/>
    <input type="submit" value="Search"/>
</form>

<table class="table" id="table">
    <tr id="zag">
        <td class="sorted-asc">Название</td>
        <td>Жанр</td>
        <td>Год выпуска</td>
        <td>Общее количество</td>
        <td>Доступно</td>
    </tr>
    <tr>
        <c:forEach items="${list.entrySet()}" var="book">
            <tr>
            <th>${book.getValue().getName() }</th>

            <th><c:forEach items="${book.getValue().getGenres()}" var="genre">
                ${genre.getGenreName() } </c:forEach></th>

            <th>${book.getValue().getYear() }</th>
            <th>${book.getValue().getCount() }</th>
            <th>${book.getValue().getCountAvailableCopies() }</th>
            </td>
        </c:forEach>
    </tr>
</table>
<hr/>
<c:if test="${currentPage != 1}">
    <td><a href="main?command=getSearchPage&page=${currentPage - 1}&name=${name}">Previous</a></td>
</c:if>

<table border="1" cellpadding="5" cellspacing="5">
    <tr>
        <c:forEach begin="1" end="${noOfPages}" var="i">
            <c:choose>
                <c:when test="${currentPage eq i}">
                    <td>${i}</td>
                </c:when>
                <c:otherwise>
                    <td><a href="main?command=getSearchPage&page=${i}&name=${name}">${i}</a></td>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </tr>
</table>
<c:if test="${currentPage lt noOfPages}">
    <td><a href="main?command=getSearchPage&page=${currentPage + 1}&name=${name}">Next</a></td>
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


