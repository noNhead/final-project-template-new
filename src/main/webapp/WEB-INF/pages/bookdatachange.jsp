<%@ page import="com.epam.rd.izh.entity.AddedBook" %>
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
        <tr>
            <td>Image Url</td>
            <td>${objectBook.getImgUrl()}</td>
        </tr>
        <tr>
            <td>Image</td>
            <td><img src="${objectBook.getImgUrl()}" alt=""/></td>
        </tr>
    </table>
        <form:form action="bookdatachange/proceed" modelAttribute="bookDataChange">
        <p title="Book data change form by title and author">Book data change by title and author</p>
        <div>
            <label title="Title">Title</label>
            <form:input path="title" id="check_title" title="Title" />
        </div>
        <div>
            <label title="Author">Author</label>
            <form:input path="author" id="check_author" title="Author"/>
        </div>
        <div>
            <label title="Genre">Genre</label>
            <form:input path="genre" id="check_genre" title="Genre"/>

        </div>
        <div>
            <label title="Year">Year</label>
            <form:input path="year" id="check_year" title="Year"/>
        </div>
        <div>
            <label title="Genre">Genre</label>
            <form:input path="imgUrl" id="check_imgUrl" title="imgUrl"/>
        </div>
        <div>
            <button>Confirm</button>
        </div>
    </form:form>
    <form method="LINK" action="bookdatachange/delete/process">
        <input type="submit" value="Delete book">
    </form>
</body>
</html>