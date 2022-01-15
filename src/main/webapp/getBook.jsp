<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <link rel="stylesheet" href="templates/css/addBook.css">
    <script src="templates/js/getBook.js"></script>
</head>
<body>
<form action="main" method="post">
    <Label>Rus name</Label>
<br>
    <input type="text"  name="bookName"/>
    <input type="text"  name="bookName"/>
    <div id="input0"></div>
    <div class="add" onclick="addInput()">One more </div>
    <input type="hidden" name="command" value="getBook"/>
    <input type="hidden" name="email" value="${email}"/>
    <input type="submit" value="Get">
</form>
<div>
    <label>Email</label>
    <input type="text"  placeholder="${email}" id="email" name="email"/>
    <input type="button" onclick="getReader()" value="Get reader>"/>
<br>
    <label id="result"/>
</div>
<%--<div class="add" onclick="addAuthors()">Send</di>v&ndash;%&gt;&lt;%&ndash;--%>
<%--<td><a href="main?email=${email}&command=checkUser">Check</a></td>&ndash;%&gt;--%>
<td><a href="addBook.jsp">Add reader</a></td>

</body>
<script>
    window.addEventListener("DOMContentLoaded", function() {
        (function(f) {
            function g(c) {
                return function(b, a) {
                    b = b.cells[c].textContent;
                    a = a.cells[c].textContent;
                    b = +b || b;
                    a = +a || a;
                    return b > a ? 1 : b < a ? -1 : 0
                }
            }
            var d = document.querySelector(f),
                e = [].slice.call(d.rows, 1);
            [].slice.call(d.rows[0].cells).forEach(function(c, b) {
                var a = 0;
                c.addEventListener("click", function() {
                    e.sort(g(b));
                    a && e.reverse();
                    e.forEach(function(a) {
                        d.appendChild(a)
                    });
                    a ^= 1
                })
            })
        })(".table")
    });
</script>
</html>
<%--/* reader  table--%>
<%--
<table class="table" id="table">
    <tr id="zag">
        <td class="sorted-asc">Фамилия</td>
        <td>Имя</td>
        <td>Дата рождения</td>
        <td>Адрес</td>
        <td>Email</td>
    </tr>
    <c:forEach items="${list}" var="reader" >
        <tr>
            <td>${reader.getLastName() }</td>
            <td>${reader.getFirstName() }</td>
            <td>${reader.getDate() }</td>
            <td>${reader.getAddress() }</td>
            <td>${reader.getEmail() }</td>
        </tr>
    </c:forEach>
</table>
<hr />
<c:if test="${currentPage != 1}">
    <td><a href="getBook?page=${currentPage - 1}">Previous</a></td>
</c:if>
<table border="1" cellpadding="5" cellspacing="5">
    <tr>
        <c:forEach begin="1" end="${noOfPages}" var="i">
            <c:choose>
                <c:when test="${currentPage eq i}">
                    <td>${i}</td>
                </c:when>
                <c:otherwise>
                    <td><a href="getBook?page=${i}">${i}</a></td>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </tr>
</table>
<c:if test="${currentPage lt noOfPages}">
    <td><a href="getBook?page=${currentPage + 1}">Next</a></td>
</c:if>--%>
