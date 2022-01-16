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
        <h3>Rus Name (required)</h3>
        <input type="text"  name="russianName" class="req"/>
        <h4 class="errorInput" ></h4>
    </div>
    <div>
        <h3>Original name</h3>
        <input type="text" name="originalName" id="originName"/>
        <h4 for="originName" ></h4>
    </div>
    <div>
        <h3>Price (required)</h3>
        <input type="price" placeholder="20.2" min="1.1" step="1.1"  name="price" id="price" class="req"/>
        <h4 class="errorInput" ></h4>

    </div>
    <div>
        <h3>Price per day (required)</h3>
        <input type="price" placeholder="2" name="priceForDay" id="pricePerDay" class="req"/>
        <h4 class="errorInput" ></h4>
    </div>
    <div>
        <h3>Book Copy count (required)</h3>
        <input type="number"  min="10" step="10" placeholder="10"  name="count" id="count" class="req"/>
        <h4 class="errorInput" ></h4>
    </div>
    <div>
        <h3>Page count</h3>
        <input type="number"<%-- min="1" step="10" --%> placeholder="200"  name="countPages" id="countPages"/>
        <h4 class="errorInput" ></h4>
    </div>
    <div>
        <h3>Year of publish</h3>
        <input type="year"  name="year" min="1800" max="2022" placeholder="2021" name="year" id="year"/>
        <h4 class="errorInput" ></h4>
    </div>
    <div>
        <h3>Genre (required)</h3>
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
        <h3>Author (required)</h3>
        <input type="text"  name="authorName" class="req"/>
        <label class="errorInput"  ></label>
        <h3>Author image></h3>
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
