<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="templates/css/addBook.css">
    <script src="templates/js/addBooks.js"></script>
    <script src="templates/js/validator.js"></script>
</head>

<body>
<form action="main"  method="post" id='form' class='formWithValidation' onsubmit="return validate();">
    <div>
        <Label>Rus Name (required)</Label>
        <input type="text"  name="russianName" class="req"/>
        <label class="errorInput" ></label>
    </div>
    <div>
        <Label>Original name</Label>
        <input type="text" name="originalName" id="originName"/>
        <label for="originName" ></label>
    </div>
    <div>
        <Label>Price (required)</Label>
        <input type="price" placeholder="20.2" min="1.1" step="1.1"  name="price" id="price" class="req"/>
        <label class="errorInput" ></label>

    </div>
    <div>
        <Label>Price per day (required)</Label>
        <input type="price" placeholder="2" name="priceForDay" id="pricePerDay" class="req"/>
        <label class="errorInput" ></label>
    </div>
    <div>
        <Label>Book Copy count (required)</Label>
        <input type="number"  min="10" step="10" placeholder="10"  name="count" id="count" class="req"/>
        <label class="errorInput" ></label>
    </div>
    <div>
        <Label>Page count</Label>
        <input type="number"<%-- min="1" step="10" --%> placeholder="200"  name="countPages" id="countPages"/>
        <label class="errorInput" ></label>
    </div>
    <div>
        <Label>Year of publish</Label>
        <input type="year"  name="year" min="1800" max="2022" placeholder="2021" name="year" id="year"/>
        <label class="errorInput" ></label>
    </div>
    <div>
        <Label>Genre (required)</Label>
        <input type = "checkbox" class='genre' onclick='checkRequire()'  name = "genre" value = "Fantastic"> Fantasy
        <input type = "checkbox" class='genre' onclick='checkRequire()'  name = "genre" value = "Children"> Children's
        <input type = "checkbox" class='genre' onclick='checkRequire()'  name = "genre" value = "Fiction"> Fiction
        <input type = "checkbox" class='genre' onclick='checkRequire()'  name = "genre" value = "Detective "> Detective
        <input type = "checkbox" class='genre' onclick='checkRequire()'  name = "genre" value = "Historical "> Historical
        <input type = "checkbox" class='genre' onclick='checkRequire()'  name = "genre" value = "Horror"> Horror
        <input type = "checkbox" class='genre' onclick='checkRequire()'  name = "genre" value = "Science"> Science
        <input type = "checkbox" class='genre' onclick='checkRequire()'  name = "genre" value = "Biography "> Biography
        <input type = "checkbox" class='genre' onclick='checkRequire()'  name = "genre" value = "Science"> Science
        <input type = "checkbox" class='genre' onclick='checkRequire()'  name = "genre" value = "Drama "> Drama
        <input type = "checkbox" class='genre' onclick='checkRequire()'  name = "genre" value = "Poetry"> Poetry
        <input type="text" class="errorInput" id="genreInput"></input>
    </div>

    <form id="authorForm">
        <Label>Author (required)</Label>
        <input type="text"  name="authorName" class="req"/>
        <label class="errorInput"  ></label>
        <Label>Author image></Label>
        <input type="file" id="authorImage" name="path" accept=".jpg,.png,.jpeg ">

    </form>
    <div id="input0"></div>
    <div class="add" onclick="addAuthors()">Send</div>
    <div class="add" onclick="addInput()">+</div>



    <div>
    <label>Book image (required)</label>
        <input type="file" name="image" accept=".jpg,.png,.jpeg" class="req">
        <label class="errorInput" ></label>
    </div>

    <input type="hidden" name="command" value="addBook" />
    <input type="submit"   class='validateBtn' value="Добавить">
</form>

</body>
</html>
