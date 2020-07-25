<!DOCTYPE html>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html lang="en" dir="ltr">
<head>
    <meta charset="utf-8">
    <title>User deleting</title>
</head>
<body>
<header>
    <div>
        <a href="bookform">Добавить книгу</a>
        <a href="bookedit">Редактировать книгу</a>
        <a>Настройки профиля</a>
    </div>
</header>
    <p title="Are you sure you want to delete the user?">Are you sure you want to delete the user?</p>
    <form method="LINK" action="userdelete/process">
    <input type="submit" value="Delete account">
</form>
</body>
</html>