<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en" dir ="ltr">
<head>
    <meta charset="utf-8">
    <title>Home page</title>
</head>

<body>
<header>
    <div>
        <a href="bookform">Добавить книгу</a>
        <a href="bookedit">Редактировать книгу</a>
        <a href="checkuserpass">Настройки профиля</a>
    </div>
</header>
<table>
    <tr>
<form:form modelAttribute="searchForm" method="post">
    <th></th>
    <th>
        <form:input path="title" id="title_check" title="Title"/>
    </th>
    <th>
        <form:input path="author" id="author_check" title="Author"/>
    </th>
    <th>
        <form:input path="genre" id="genre_check" title="Genre"/>
    </th>
    <th>
        <form:input path="year" id="year_check" title="Year"/>
    </th>
    <th>
        <button class="button">Search</button>
    </th>
</form:form>
    </tr>
    <c:forEach var="i" items="${listBook}">
        <tr>
            <td><img src="${i.getUrlImg()}"/></td>
            <td><a href="${pageContext.request.contextPath}/book/${i.getAuthor()}/${i.getTitle()}">${i.getTitle()} ${i.getAuthor()}</a></td>
            <td><p>${i.getGenre()}</p></td>
        </tr>
    </c:forEach>
</table>
</body>
<script type="text/javascript"><%@include file="/WEB-INF/js/jquery-3.5.1.min.js"%></script>
<script type="text/javascript"><%@include file="/WEB-INF/js/main.js"%></script>
</html>

