<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 04.01.2022
  Time: 17:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <script src="templates/js/validator.js"></script>
</head>
<body>
<form action="main" method="post" onsubmit="return validate()">
    <label>Firstname (required)</label>
    <input type="text"  class="req" name="firstName"/>
    <label class="errorInput" ></label>
    <br>
    <label>Lastname (required)</label>
    <input type="text"  class="req"  name="lastName"/>
    <label class="errorInput" ></label>
    <br>
    <label>Patronymic</label>
    <input type="text"  name="patronymic"/>
    <br>
    <label>Email (required)</label>
    <input type="email" class="req" name="email"/>
    <label class="errorInput" ></label>
    <br>
    <label>Passport number (required)</label>
    <input type="text"  class="req"  name="passportNumber"/>
    <label class="errorInput" ></label>
    <br>
    <label>Birth date (required)</label>
    <input type="date" class="req"  name="birthDate"/>
    <label class="errorInput" ></label>
    <br>
    <label>Address</label>
    <input type="text"  name="address"/>
    <input type="hidden" name="command" value="addReader" />
    <br>
    <label>Reader image</label>
    <input type="file" name="image" accept=".jpg,.png,.jpeg" >
    <br>
    <input type="submit" value="Добавить"/>
</form>
</body>
</html>
