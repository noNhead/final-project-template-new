<%@ page import="com.epam.rd.izh.entity.AddedBook" %>
<!DOCTYPE html>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html lang="ru" dir="ltr">
<head>
    <meta charset="utf-8">
    <title>Изменение данных о книге</title>
    <style><%@include file="/WEB-INF/css/index.css"%></style>
</head>
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
    <table>
        <tr>
            <td>Title</td>
            <td>${objectBook.getTitle()}</td>
        </tr>
        <tr>
            <td>Author</td>
            <td>${objectBook.getAuthor()}</td>
        </tr>
        <tr>
            <td>Genre</td>
            <td>${objectBook.getGenre()}</td>
        </tr>
        <tr>
            <td>Year</td>
            <td>${objectBook.getYear()}</td>
        </tr>
    </table>
<table>
    <form:form action="bookdatachange/proceed" modelAttribute="bookDataChange">
        <p title="Book data change form by title and author">Впишите те данные, которые хотите изменить</p>

        <tr>
            <td class="subName"><label title="Title">Название</label></td>
            <td><input path="title" id="check_title" title="Title" /></td>
        </tr>
        <tr>
            <td class="subName"><label title="Author">Автор</label></td>
            <td><input path="author" id="check_author" title="Author"/></td>
        </tr>
        <tr>
            <td class="subName"><label title="Genre">Жанры</label></td>
            <td><input path="genre" id="check_genre" title="Genre"/></td>

        </tr>
        <tr>
            <td class="subName"><label title="Year">Год</label></td>
            <td><input path="year" id="check_year" title="Year"/></td>
        </tr>
        <tr>
            <td></td>
            <td><button>Подтвердить</button></td>
        </tr>
    </form:form>
    <tr>
        <td></td>
        <td><form method="LINK" action="bookdatachange/delete/process"><input type="submit" value="Удалить"></form></td>
    </tr>
</table>
</body>
</html>