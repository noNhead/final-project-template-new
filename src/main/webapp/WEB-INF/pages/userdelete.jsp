<!DOCTYPE html>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html lang="ru" dir="ltr">
<head>
    <meta charset="utf-8">
    <title>Удаление пользователя</title>
    <style><%@include file="/WEB-INF/css/index.css"%></style>
</head>
<body>
<body>
<header>
    <div class="headerHrefDiv">
        <a class="headerHref" href="${pageContext.request.contextPath}/search">Поиск</a>
    </div>
    <div class="headerHrefDiv">
        <a class="headerHref" href="${pageContext.request.contextPath}/bookedit">Редактировать</a>
    </div>
    <div class="headerHrefDiv">
        <a class="headerHref" href="${pageContext.request.contextPath}/userdatachange">Профиль</a>
    </div>
</header>
<div class="mainSpace">
    <p title="Are you sure you want to delete the user?">Вы действительно хотите удалить аккаунт?</p>
    <form method="LINK" action="userdelete/process">
        <input type="submit" value="Удалить">
    </form>
</div>
</body>
</html>