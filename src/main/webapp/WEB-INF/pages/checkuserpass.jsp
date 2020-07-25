<!DOCTYPE html>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html lang="en" dir="ltr">
<head>
    <meta charset="utf-8">
    <title>Confirm the password</title>
</head>

<body>
<header>
    <div>
        <a href="bookform">Добавить книгу</a>
        <a href="bookedit">Редактировать книгу</a>
        <a>Настройки профиля</a>
    </div>
</header>
<form:form action="checkuserpass/proceed" method="post" modelAttribute="checkUserPass">
    <p title="Book data change form by title and author">
        Confirm the password
    </p>
    <div>
        <label title="Password">Password</label>
        <form:input path="password" type="password" id="check_password" name="password" />
    </div>
    <div>
        <button>Confirm</button>
    </div>
</form:form>

</body>
</html>