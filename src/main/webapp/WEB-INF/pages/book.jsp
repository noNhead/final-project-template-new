<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
    <meta charset="utf-8"/>
    <title>Book</title>
</head>
<body>
<table>
    <div>
        <table>
            <tr>
                <td><img src="${book.getUrlImg()}"></td>
                <td></td>
            </tr>
            <tr>
                <td><p>Название:</p></td>
                <td><p>${book.getTitle()}</p></td>
            </tr>
            <tr>
                <td><p>Авторство:</p></td>
                <td><p>${book.getAuthor()}</p></td>
            </tr>
            <tr>
                <td><p>Год написания:</p></td>
                <td><p>${book.getYear()}</p></td>
            </tr>
            <tr>
                <td><p>Жанры:</p></td>
                <td><p>${book.getGenre()}</p></td>
            </tr>
            <tr>
                <td></td>
                <td><a href="${pageContext.request.contextPath}/book/download/${book.getAuthor()}/${book.getTitle()}">Скачать</a></td>
            </tr>
        </table>
    </div>
</table>
</body>
</html>
