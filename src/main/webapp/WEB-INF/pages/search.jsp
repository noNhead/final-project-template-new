<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru" dir ="ltr">
<head>
    <meta charset="utf-8">
    <title>Поиск</title>
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
    <table>
        <tr><th></th>
            <th class="subName">Название</th>
            <th class="subName">Автор</th>
            <th class="subName">Жанры</th>
            <th class="subName">Год (ГГГГ)</th>
            <th></th>
        </tr>
        <tr>
            <form:form modelAttribute="searchForm" method="post">
                <td></td>
                <td>
                    <form:input path="title" id="title_check" title="Title"/>
                </td>
                <td>
                    <form:input path="author" id="author_check" title="Author"/>
                </td>
                <td>
                    <form:input path="genre" id="genre_check" title="Genre"/>
                </td>
                <td>
                    <form:input path="year" id="year_check" title="Year"/>
                </td>
                <td>
                    <button class="button">Search</button>
                </td>
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
</div>
</body>
</html>


