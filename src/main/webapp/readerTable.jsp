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
    <Label>Email</Label>
    <input type="search" name="email"/>
    <input type="hidden" name="command" value="getReaderTable"/>
    <input type="submit" value="Search"/>
</form>

<table class="table" id="table">
    <tr id="zag">
        <td class="sorted-asc">Last name</td>
        <td>First name</td>
        <td>Date</td>
        <td>Address</td>
        <td>Email</td>
    </tr>
    <tr>
        <c:forEach items="${list}" var="book">
    <tr>
        <th>${book.getLastName()}</th>
        <th>${book.getFirstName() }</th>
        <th>${book.getDate()}</th>
        <th>${book.getAddress()}</th>
        <th>${book.getEmail()}</th>
    </tr>
    </c:forEach>
    </tr>
</table>
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


