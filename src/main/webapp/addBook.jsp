<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <script src="templates/js/bookValidator.js"></script>
    <script src="templates/js/addBook.js"></script>
    <link rel="stylesheet" href="templates/css/header.css">
    <link rel="stylesheet" href="templates/css/newBook.css">
</head>

<body>

<div class="header">
    <a class="currentPage" href="addBook.jsp">Add books</a>
    <a href="addReader.jsp">Add readers</a>
    <a href="getBook.jsp">Get books</a>
    <a href="returnBook.jsp">Return books</a>
    <a href="main?command=getSearchPage">Book search</a>
    <a href="main?command=getReaderTable">Reader search</a>
</div>
<br>
<a href="main?command=getBookTable"><img src="templates/image/img_3.png" class="image"></a>
<form action="main" method="post" id='form' class='formWithValidation' onsubmit="return validateAllInputs()">
    <div>
        <h3 class="s">Book image *</h3>
        <br>
        <input type="file" name="image" accept=".jpg,.png,.jpeg" class="req" id="file">
        <span class="error"/>
    </div>
    <div id="inputIm0"></div>
    <input type="button" class="button" onclick="addInputImage()" value="One more image"></input>
    <div>
        <h3 class="s">Rus Name * </h3>
        <input type="text" name="russianName" class="req"/>
        <span class="error"/>
    </div>
    <div>
        <h3 class="s">Original name</h3>
        <input type="text" name="originalName" class="line" id="originName"/>
    </div>
    <div>
        <h3 class="s">Price *</h3>
        <input type="price" placeholder="20.2" name="price" id="price" class="req"/>
        <span class="error"/>

    </div>
    <div>
        <h3 class="s">Price per day *</h3>
        <input type="price" placeholder="2" name="priceForDay" id="pricePerDay" class="req"/>
        <span class="error"/>
    </div>
    <div>
        <h3 class="s">Book copy count *</h3>
        <input type="number" placeholder="10" name="count" id="count" class="req"/>
        <span class="error"/>

    </div>
    <div>
        <h3 class="s">Page count</h3>
        <input type="number" placeholder="200" name="countPages" class="line" id="countPages"/>
        <span id="errorPageCount"/>
    </div>
    <h3 class="s">Genre *</h3>
    <div>
        <input type="checkbox" class='genre' name="genre" id="Fantastic" value="Fantastic"><label for="Fantastic">Fantasy</label>
        <input type="checkbox" class='genreNew' name="genre" id="Children" value="Children"> <label for="Children">
        Children</label>
        <br>
        <input type="checkbox" class='genre' name="genre" id="fiction" value="Fiction"> <label
            for="fiction">Fiction</label>
        <input type="checkbox" class='genreNew' name="genre" id="Detective" value="Detective"><label for="Detective">Detective</label>
        <br>
        <input type="checkbox" class='genre' name="genre" id="Historical" value="Historical"> <label for="Historical">History</label>
        <input type="checkbox" class='genreNew' name="genre" id="Horror" value="Horrors"> <label
            for="Horror">Horrors</label>
        <br>
        <input type="checkbox" class='genre' name="genre" id="Science" value="Science"><label
            for="Science">Science</label>
        <input type="checkbox" class='genreNew' name="genre" id="Mistery" value="Mistery"> <label
            for="Mistery">Mistery</label>
    </div>
    <br>
    <span id="genreError"></span>
    <div>
        <h3 class="s">Year of publish</h3>
        <input type="year" name="year" class="line" placeholder="2021" name="year" id="year"/>
    </div>
    <div id="inputAuthor0">
        <input type="hidden" id="isAuthor">
    </div>
    <input type="hidden" name="command" value="addBook"/>
    <input type="submit" class='validateBtn' value="Add book"/>
</form>
<br>
<form id="authorForm">
    <input type="text" class="showAuthor" id="input"></input>
    <div>
        <h3 class="s">Author (En) * </h3>
        <input type="text" id="authorName" name="author" class="line"/>
        <span class="error" id="authorError"/>
    </div>
    <div>
        <h3 class="s">Image</h3>
        <input multiple type="file" name="path" id="path" accept=".jpg, .jpeg, .png"/>
    </div>
    <br>
    <input type="button" value="Upload author" class="button" onclick="addAuthors()"></input>

</form>


</body>
</html>
