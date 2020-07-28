<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en" dir="ltr">
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
    <p>
      ${message}!
    </p>
  <table>
    <c:forEach var="i" items="${listBook}">
    <tr>
        <td><img src="${i.getUrlImg()}"/></td>
        <td><a href="${pageContext.request.contextPath}/book/${i.getAuthor()}/${i.getTitle()}">${i.getTitle()} ${i.getAuthor()}</a></td>
        <td><p>${i.getGenre()}</p></td>
    </tr>
    </c:forEach>
  </table>
  </body>
</html>