<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ru" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>Главная</title>
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
      <c:forEach var="i" items="${listBook}">
        <tr>
          <td><img src="${i.getUrlImg()}"/></td>
          <td class="subName"><a href="${pageContext.request.contextPath}/book/${i.getAuthor()}/${i.getTitle()}">${i.getTitle()} ${i.getAuthor()}</a></td>
          <td class="subName"><p>${i.getGenre()}</p></td>
        </tr>
      </c:forEach>
    </table>
  </div>
  </body>
</html>