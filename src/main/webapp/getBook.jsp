<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link rel="stylesheet" href="templates/css/header.css">
    <script src="templates/js/getBooks.js"></script>
    <link rel="stylesheet" href="templates/css/getNewBook.css">


</head>

<body>
<div class="header">
    <a href="addBook.jsp">Add books</a>
    <a href="addReader.jsp">Add readers</a>
    <a class="currentPage" href="getBook.jsp">Get books</a>
    <a href="returnBook.jsp">Return books</a>
    <a href="main?command=getSearchPage">Book search</a>
    <a href="main?command=getReaderTable">Reader search</a>
</div>
<br>
<a href="main?command=getBookTable"><img src="templates/image/img_3.png" class="image"></a>
<form id="readerForm">

    <h3>Email *</h3>
    <input type="text" class="input" id="email" name="email"/>
    <span id="errorInput"></span>
    <input type="button" class="searchButton" onclick="getReader()" value="Get reader"/>
    <br>
    <input type="text" id="readerInput" name="email" readonly></input>
</form>
<br>
<div>
    <div id="userInput"/>
</div>

<br>
<form action="main" id="bookForm" method="post">
    <div clas="formWrapper">

        <div class="formWrapper">
            <h3>Rus name *</h3>
            <input type="text" class="input" name="bookName" class="req"/>

            <div id="input0"></div>
            <br>
            <input type="button" class="addBook" onclick="addInput()" value="One more book"></input>
            <br>
            <input type="text" class="input" id="errorBook"></input>
            <input type="hidden" id="emailInput" name="email"></input>
            <br>
            <input type="button" class="bookButton" value="Get books" onclick="getBook()"/>
        </div>
    </div>
</form>


<div class="order">
    <div class="wrapper">
        <h2>Order</h2>
        <h3>Available book(s)</h3>
        <input type="text" class="input" id="bookName" readonly/>
        <h3>Return date</h3>
        <input type="text" class="input" id="date" readonly/>
        <h3>Preliminary cost</h3>
        <input type="text" class="input" id="price" readonly/>
        <br>
        <div id='href' ><a  id="close" href="main?command=getBookTable">Close order</a></div>
    </div>
</div>

</body>

</html>

