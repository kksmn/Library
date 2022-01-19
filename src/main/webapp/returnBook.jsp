<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="templates/css/addBook.css">
    <script src="templates/js/returnBook.js"></script>
    <script src="templates/js/getReader.js"></script>

    <script src="templates/js/validator.js"></script>
</head>
<body>
<form id="readerForm">
    <label>Email</label>
    <input type="text"   id="email" name="email" class="readerInput"/>
    <label class="errorInput" ></label>
    <input type="button" onclick="getReader()" value="Get reader"/>
    <br>
    <input type="text" id="readerInput" name="email"></input>

</form>
<form id="authorForm">
    <h3>Author (required)</h3>
    <input type="text" name="authorName" id="authorName" class="req"/>
    <h3>Image</h3>
    <input type="file" name="path">
    <div id="input0"></div>
</form>

<div class="add" onclick="addAuthors()">Send</div>
<div class="add" onclick="addInput()">add input</div>
</body>
</html>
