<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="templates/css/header.css">
    <script src="templates/js/returnBooks.js"></script>

    <link rel="stylesheet" href="templates/css/returnBooks.css">
</head>
<body>
<div class="header">
    <a href="addBook.jsp">Add books</a>
    <a href="addReader.jsp">Add readers</a>
    <a href="getBook.jsp">Get book</a>
    <a class="currentPage" href="returnBook.jsp">Return book</a>
    <a href="main?command=getSearchPage">Book search</a>
    <a href="main?command=getReaderTable">Reader search</a>
</div>
<br>
<a href="main?command=getBookTable"><img src="templates/image/img_3.png" class="image"></a>
<form id="emailForm">

    <h3 class="name">Email *</h3>
    <input type="text" id="email" name="email" class="readerInput"/>
    <span class="errorInput"></span>
    <br>
    <input type="text" id="readerInput" name="email"></input>
    <br>
    <input type="button" class="findButton" onclick="findReader()" value="Find reader"/>
</form>

<form id="readerForm">
    <div class="wrapper">
    <h3 class="name">Rus name *</h3>
    <input type="text" name="rusName" class="req"/>
        <input type="text" class="input" id="errorBook"></input>
    <h3 class="name"> Rating</h3>
    <input type="number" class="line" name="rating">
    <input type="hidden" id="emailInput" name="email"></input>

    <h3 class="name">Defects</h3>
    <input multiple type="file" name="path" id="path" accept=".jpg, .jpeg, .png">

    <input type="button" class="imageButton" onclick="addInput()" value="One more image"/>
        <div id="input0"></div>
    </div>
    <br>
    <input type="button" class="getOrder" onclick="getReader()" value="Get order"/>
    <br>
</form>

<form id="orderForm">
    <div class="wrapper">
    <h2>Reader order</h2>
    <h3>Return date</h3>
    <input type="text" id="date" class="line" readOnly/>
    <h3>Total price</h3>
    <input type="text" id="price"  class="line" readOnly/>
        <br>
        <div id='href' ><a id="close" href="main?command=getBookTable">Close order</a></div>
    </div>';
</form>



</body>
</html>
