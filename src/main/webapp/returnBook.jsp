<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="templates/css/addBook.css">
 <%--   <script src="templates/js/getReader.js"></script>--%>
    <script src="templates/js/returnBook.js"></script><%--
    <script src="templates/js/validator.js"></script>--%>
</head>
<body>
<form id="emailForm">
    <label>Email</label>
    <input type="text"   id="email" name="email" class="readerInput"/>
    <label class="errorInput" ></label>
    <input type="text" id="readerInput" name="email"></input>
    <input type="button" onclick="findReader()" value="Find reader"/>
</form>
<form id="readerForm">
       <h3>Rus name (required)</h3>
       <input type="text" name="rusName" class="req"/>


       <h3>Rating</h3>
       <input type="number" name="rating">
       <input type="hidden" id="emailInput" name="email"></input>
    <input type="button" onclick="getReader()" value="Get order"/>
    <br>
    <h3>Defects</h3>
    <input multiple type="file" name="path" accept=".jpg, .jpeg, .png">

    <input type="button" onclick="addInput()" value="one more"/>
    <div id="input0"/>
</form>


<div id="input"/>
<form id="orderForm">
    <div id="order0"/>
</form>

</body>
</html>
