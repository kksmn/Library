<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <%--    <script src="templates/js/getReader.js"></script>--%>
    <link rel="stylesheet" href="templates/css/addBook.css">
    <script src="templates/js/getBooks.js"></script>


</head>

<body>
<form id="readerForm">
    <label>Email</label>
    <input type="text" id="email" name="email" class="readerInput"/>
    <label class="errorInput"></label>
    <input type="button"  onclick="getReader()" value="Get reader"/>
    <br>
    <input type="text" id="readerInput" name="email"></input>

</form>
<br>
<div>
    <div id="userInput"/>
</div>

<br>
<form action="main" id="bookForm" method="post">
    <Label>Rus name</Label>
    <hr>
    <input type="text" name="bookName" class="req"/>
    <label class="errorInput"></label>

    <div id="input0"></div>
    <div class="add" onclick="addInput()">One more</div>
    <input type="hidden" id="emailInput" name="email"></input>

</form>

<input type="button" value="Get books" onclick="getBook()" />
<div>
    <h3>Reader's order</h3>
    <h4>Books</h4>
    <input type="text" id="bookName" readonly/>
    <h4>Return date</h4>
    <input type="text" id="date" readonly/>
    <h4>Preliminary cost</h4>
    <input type="text" id="price" readonly/>
</div>
<a href="main?command=getBookTable">Close order</a>
</body>

</html>

