<!DOCTYPE html>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html lang="en" dir="ltr">
<head>
    <meta charset="utf-8">
    <title>User data change</title>
</head>
<body>
<header>
    <div>
        <a href="index">Главная</a>
        <a href="bookform">Редактировать книгу</a>
        <a>Настройки профиля</a>
    </div>
</header>
<form:form action="userdatachange/proceed" modelAttribute="userDataChange">
    <p title="Change Password">Change Password</p>
    <div>
        <label title="Password">Password</label>
        <form:input path="password" id="check_password" title="Password" />
    </div>
    <div>
        <button>Confirm</button>
    </div>
</form:form>
<form method="LINK" action="userdatachange/delete/process">
    <input type="submit" value="Delete account">
</form>
</body>
</html>