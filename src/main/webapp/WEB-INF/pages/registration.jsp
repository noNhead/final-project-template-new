<!DOCTYPE html>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html lang="ru" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>Регистрация</title>
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
    <form:form action="registration/proceed" method="post" modelAttribute="registrationForm">
      <table>
        <tr><td class="headName"><p title="Registration form">Регистрация</p></td></tr>
        <tr><td><table>
          <tr><td class="subName"><label title="Login">Логин</label></td>
            <td><form:input path="login" id="check_login" title="Login"/></td></tr>
          <tr><td class="subName"><label title="Password">Пароль</label></td>
            <td><form:input path="password" id="check_password" type="password" title="Password" /></td></tr>
          <tr><td></td><td><button>Подтвердить</button></td></tr>
        </table></td></tr>
      </table>
    </form:form>
  </div>
  </body>
</html>

