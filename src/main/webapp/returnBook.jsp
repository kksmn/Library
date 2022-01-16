<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="templates/css/addBook.css">
    <script src="templates/js/returnBooks.js"></script>
    <script src="templates/js/validator.js"></script>
</head>
<body>
<form id="bookForm">
    <h3>Rus name</h3>
    <input type="text"  name="rusname" class="req"/>
    <input type="text" class="errorInput"  ></input>
    <h3>Rating></h3>
    <input type="number" name="rating"></input>
    <h3>Defects</h3>
    <input type="file" id="authorImage" name="path" accept=".jpg,.png,.jpeg ">
    <div id="input0"></div>
</form>

<div class="add" onclick="addInput()">+</div>
</body>
</html>
