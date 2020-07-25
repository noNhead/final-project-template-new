<!DOCTYPE html>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html lang="en" dir="ltr">
<head>
    <meta charset="utf-8">
    <title>Book data change</title>
</head>

<body>
<header>
    <div>
        <a href="bookform">Добавить книгу</a>
        <a>Редактировать книгу</a>
        <a href="checkuserpass">Настройки профиля</a>
    </div>
</header>
<form:form action="bookedit/proceed" modelAttribute="bookEdit">
    <p title="Book data change form by title and author">
        Book data change by title and author
    </p>

    <div>
        <label title="Title">Title</label>
        <form:input path="title" id="check_title" title="Title" />
    </div>

    <div>
        <label title="Author">Author</label>
        <form:input path="author" id="check_author" title="Author"/>
    </div>

    <div>
        <button>Confirm</button>
    </div>
</form:form>

</body>
</html>
