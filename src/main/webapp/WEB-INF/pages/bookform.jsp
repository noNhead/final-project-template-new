<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
    <meta charset="utf-8">
    <title>Registration</title>
</head>

<body>
    <div>
        <form:form action="bookform/proceed" method="post" modelAttribute="bookForm">
            <p title="Book form">Add Book</p>
            <div class="group">
                <label title="Book title">Book title</label>
                <form:input path="title" id="check_title" title="Title"/>
            </div>
            <div class="group">
                <label title="Book author">Book author</label>
                <form:input path="author" id="check_author" title="Author"/>
            </div>
            <div class="group">
                <label title="Book genre">Book genre</label>
                <form:input path="genre" id="check_genre" title="Genre"/>
            </div>
            <div class="group">
                <label title="Book date">Book date</label>
                <form:input path="Year" id="Year" title="Year"/>
            </div>
            <div>
                <label title="Book image URL">image URL</label>
                <form:input path="imgUrl" id="check_url" title="img_url"/>
            </div>
            <div>
                <button>Confirm</button>
            </div>
        </form:form>
    </div>
</body>
</html>