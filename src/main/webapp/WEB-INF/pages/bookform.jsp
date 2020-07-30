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
<form:form action="bookform/proceed" method="post" modelAttribute="bookForm">
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
        <table>
            <tr><td><p title="Book form">Заполните все данные</p></td></tr>
            <tr>
                <td>
                    <table>
                            <tr>
                                <td class="subName"><label title="Book title">Название книги:</label></td>
                                <td><form:input path="title" id="check_title" name="title"/></td>
                            </tr>
                            <tr>
                                <td class="subName"><label title="Book author">Полное имя автора:</label></td>
                                <td><form:input path="author" id="check_author" name="author"/></td>
                            </tr>
                            <tr>
                                <td class="subName"><label title="Book genre">Жанры, через пробел:</label></td>
                                <td><form:input path="genre" id="check_genre" name="genre"/></td>
                            </tr>
                            <tr>
                                <td class="subName"><label title="Book date">Год написания (ГГГГ):</label></td>
                                <td><form:input path="Year" id="Year" name="year"/></td>
                            </tr>
                            <tr>
                                <td></td>
                                <td><button>Confirm</button></td>
                            </tr>

                    </table>
                </td>
            </tr>
        </table>
    </form:form>
</div>
</body>
</html>