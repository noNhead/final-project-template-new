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
<form:form action="bookedit/proceed" modelAttribute="bookEdit">
    <div class="mainSpace">
        <table>
            <tr>
                <td>
                    <p title="Book data change form by title and author">Изменение данных по названию и автору книги</p>
                </td>
            </tr>
            <tr><td><table>
                <tr>
                    <td class="subName"><label title="Title">Название</label></td>
                    <td><form:input path="title" id="check_title" title="Title" /></td>
                </tr>
                <tr>
                    <td class="subName"><label title="Author">Авторство</label></td>
                    <td><form:input path="author" id="check_author" title="Author"/></td>
                </tr>
                <tr>
                    <td><button>Поиск</button></td>
                </tr>
            </table></td></tr>
        </table>
    </div>
</form:form>
</body>
</html>