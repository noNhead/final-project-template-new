<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru" dir="ltr">
<head>
    <meta charset="utf-8"/>
    <title>Book</title>
    <style><%@include file="/WEB-INF/css/index.css" %></style>
</head>
<body>
<table>
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
            <tr>
                <td><img src="${book.getUrlImg()}"></td>
                <td>
                    <table>
                        <tr>
                            <td class="subName"><p>Название:</p></td>
                            <td class="subNameValue"><p>${book.getTitle()}</p></td>
                        </tr>
                        <tr>
                            <td class="subName"><p>Авторство:</p></td>
                            <td class="subNameValue"><p>${book.getAuthor()}</p></td>
                        </tr>
                        <tr>
                            <td class="subName"><p>Год написания:</p></td>
                            <td class="subNameValue"><p>${book.getYear()}</p></td>
                        </tr>
                        <tr>
                            <td class="subName"><p>Жанры:</p></td>
                            <td class="subNameValue"><p>${book.getGenre()}</p></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><a href="${pageContext.request.contextPath}/book/download/${book.getAuthor()}/${book.getTitle()}">Скачать</a></td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>
    </div>
</body>
</html>
