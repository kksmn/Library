<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:h="http://xmlns.jcp.org/jsf/html"
      xmlns:ui="http://xmlns.jcp.org/jsf/facelets"
      xmlns:f="http://xmlns.jcp.org/jsf/core">
<head>
    <link rel="stylesheet" href="templates/css/addBook.css">
    <script src="templates/js/getReader.js"></script>
    <script src="templates/js/returnBook.js"></script>
</head>
<body>
<div>
    <label>Email</label>
    <input type="text"  placeholder="${email}" id="email" name="email"/>
    <input type="button" onclick="getReader()" value="Get reader>"/>
</div>

<table>
        <tr>
            <th>Фамилия</th>
            <td>${reader.getLastName() }</td>
            <th>Имя</th>
            <td>${reader.getName() }</td>
            <th>Дата рождения</th>
            <td>${reader.getDate() }</td>
            <th>Адрес</th>
            <td>${reader.getAddress() }</td>
            <th>Email</th>
            <td>${reader.getEmail() }</td>
        </tr>
</table>
<hr />
<div>
    <label>Return book</label>
    <Label>Rus name</Label>
    <input type="text" class="rusname"/>
    <Label>Rating</Label>
    <input type="number" class="rating"/>
    <Label>Defects</Label>
    <input type="file" id="authorImage" name="path" accept=".jpg,.png,.jpeg ">
    <input type="button" onclick="returnBook()"/>

</div>
<div>
    <input type="number" value=${date}/>
    <input type="number" valuew="${price}"/>
</div>


</body>
</html>
