<!DOCTYPE html>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>

<html lang="ru" dir="ltr">
<head>
    <meta charset="utf-8">
    <title>Добавить книгу</title>
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
<div class="mainSpace">
    <form:form action="bookform/proceed" method="post" modelAttribute="bookForm">
        <table>
            <tr><td><p title="Book form">Заполните все данные</p></td></tr>
            <tr><td><table>
                <tr>
                    <td class="subName"><label title="Book title">Название книги:</label></td>
                    <td><input:form path="title" id="check_title" title="Title"/></td>
                </tr>
                <tr>
                    <td class="subName"><label title="Book author">Полное имя автора:</label></td>
                    <td><input:form path="author" id="check_author" title="Author"/></td>
                </tr>
                <tr>
                    <td class="subName"><label title="Book genre">Жанры, через пробел:</label></td>
                    <td><input:form path="genre" id="check_genre" title="Genre"/></td>
                </tr>
                <tr>
                    <td class="subName"><label title="Book date">Год написания (ГГГГ):</label></td>
                    <td><input:form path="Year" id="Year" title="Year"/></td>
                </tr>
                <tr>
                    <td class="subName"><label title="Book">Сам текстовый файл</label></td>
                    <td><input path="file" type="file" title="file"/></td>
                </tr>
                <tr><td></td><td><button>Confirm</button></td></tr>
            </table></td></tr>
        </table>
    </form:form>
</div>
</body>
</html>