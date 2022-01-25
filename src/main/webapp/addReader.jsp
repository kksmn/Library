<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="templates/css/addReader.css">
    <link rel="stylesheet" href="templates/css/header.css">
    <script src="templates/js/readerValidator.js"></script>
</head>
<body>
<div class="header">
    <a href="addBook.jsp">Add books</a>
    <a class="currentPage" href="addReader.jsp">Add readers</a>
    <a href="getBook.jsp">Get books</a>
    <a href="returnBook.jsp">Return books</a>
    <a href="main?command=getSearchPage">Book search</a>
    <a href="main?command=getReaderTable">Reader search</a>
</div>
<br>
<a href="main?command=getBookTable"><img src="templates/image/img_3.png" class="image"></a>
<form action="main" id="form" method="post" onsubmit="return validate()">
    <h3 class="s">Firstname *</h3>
    <input type="text" class="req" name="firstName"/>
    <span class="errorInput"></span>
    <br>
    <h3 class="s">Lastname *</h3>
    <input type="text" class="req" name="lastName"/>
    <span class="errorInput"></span>
    <br>
    <h3 class="s">Patronymic</h3>
    <input type="text" class="line" name="patronymic"/>
    <br>
    <h3 class="s">Email *</h3>
    <input type="text" id="email" class="req" name="email"/>
    <span class="errorInput"></span>
    <br>
    <h3 class="s">Passport number *</h3>
    <input type="text" class="req" name="passportNumber"/>
    <span class="errorInput"></span>
    <br>
    <h3 class="s">Birth date *</h3>
    <input type="date" class="req" name="birthDate"/>
    <span class="errorInput"></span>
    <br>
    <h3 class="s">Address</h3>
    <input type="text" class="line" name="address"/>
    <input type="hidden" name="command" value="addReader"/>
    <br>
    <h3 class="s">Reader image</h3>
    <input type="file" name="image" id="path" accept=".jpg,.png,.jpeg">
    <br>
    <input type="submit" class="validateBtn" value="Add reader"/>
</form>
</body>
</html>
