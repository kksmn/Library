<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="templates/css/addBook.css">
    <script src="templates/js/addBooks.js"></script>
    <script src="templates/js/validator.js"></script>
</head>

<body>
<form action="main" method="post" id='form' class='formWithValidation' onsubmit="return validate()">
    <div>
        <label>Book image (required)</label>
        <br>
        <input type="file" name="image" accept=".jpg,.png,.jpeg" class="req">
        <span class="error"/>
    </div>
    <div id="inputIm0"></div>
    <input type="button" onclick="addInputImage()" value="one more"></input>
    <div>
        <h3>Rus Name (required)</h3>
        <input type="text" name="russianName" class="req"/>
        <span class="error"/>
    </div>
    <div>
        <h3>Original name</h3>
        <input type="text" name="originalName" id="originName"/>
    </div>
    <div>
        <h3>Price (required)</h3>
        <input type="price" placeholder="20.2" name="price" id="price" class="req"/>
        <span class="error"/>

    </div>
    <div>
        <h3>Price per day (required)</h3>
        <input type="price" placeholder="2" name="priceForDay" id="pricePerDay" class="req"/>
        <span class="error"/>
    </div>
    <div>
        <h3>Book Copy count (required)</h3>
        <input type="number" placeholder="10" name="count" id="count" class="req"/>
        <span class="error"/>
    </div>
    <div>
        <h3>Page count</h3>
        <input type="number" placeholder="200" name="countPages" id="countPages"/>
    </div>
    <div>
        <h3>Genre (required)</h3>
        <div>
            <input type="checkbox" class='genre' onclick='checkRequire()' name="genre" value="Fantastic"> Fantasy
            <br>
            <input type="checkbox" class='genre' onclick='checkRequire()' name="genre" value="Children"> Children's
            <br>
            <input type="checkbox" class='genre' onclick='checkRequire()' name="genre" value="Fiction"> Fiction
            <br>
            <input type="checkbox" class='genre' onclick='checkRequire()' name="genre" value="Detective "> Detective
            <br>
            <input type="checkbox" class='genre' onclick='checkRequire()' name="genre" value="Historical "> Historical
            <br>
            <input type="checkbox" class='genre' onclick='checkRequire()' name="genre" value="Horror"> Horror
            <br>
            <input type="checkbox" class='genre' onclick='checkRequire()' name="genre" value="Science"> Science
            <br>
            <input type="checkbox" class='genre' onclick='checkRequire()' name="genre" value="Biography "> Biography
            <br>
            <input type="checkbox" class='genre' onclick='checkRequire()' name="genre" value="Science"> Science
            <br>
            <input type="checkbox" class='genre' onclick='checkRequire()' name="genre" value="Drama "> Drama
            <br>
            <input type="checkbox" class='genre' onclick='checkRequire()' name="genre" value="Poetry"> Poetry
        </div>
    </div>
    <span class="error" id="genreError"/>
    <div>
        <h3>Year of publish</h3>
        <input type="year" name="year" placeholder="2021" name="year" id="year"/>
    </div>
<div id="inputAuthor0">
    <input type="hidden" id="isAuthor">
</div>
    <input type="hidden" name="command" value="addBook"/>
    <input type="submit" class='validateBtn' value="Добавить"/>
</form>
<br>
<form id="authorForm">
    <input type="text" class="showAuthor" id="input"></input>
    <div>
        <h3>Author (required)</h3>
        <input type="text" id="authorName" name="author" />
        <span class="error" id="authorError"/>
    </div>
    <div>
        <h3>Image</h3>
        <input multiple type="file" name="path" id="path" accept=".jpg, .jpeg, .png"/>
    </div>
    <input type="button" value="add" onclick="addAuthors()"></input>

</form>


</body>
</html>
