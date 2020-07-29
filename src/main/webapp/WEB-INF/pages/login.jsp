<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<html>
  <head>
    <meta charset="UTF-8">
    <script type="text/javascript" src="./js/main.js"></script>
    <title>Вход</title>
    <style><%@include file="/WEB-INF/css/index.css"%></style>
    <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">
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
      <form:form action="login/process" method="post" modelAttribute="loginForm">
      <tr><td class="headName"><p title="Login form">Войти</p></td></tr>
      <tr><td><table>
        <tr>
          <td class="subName"><label for="">Логин</label></td>
          <td><form:input path="login" id="check_login" name="login" /></td>
        </tr>
        <tr>
          <td class="subName"><label for="">Пароль</label></td>
          <td><form:input path="password" id="check_password" name="password" type="password" /></td>
        </tr>
        <tr><td class="subName"><div class="error">${error_login_placeholder}</div></td></tr>
        <tr><td></td><td><button class="button">Войти</button></td></tr>
        </form:form>
        <tr><td></td><td><form action="registration" method="get"><button class="button">Регистрация</button></form></td></tr>
      </table></td></tr>
    </table>
  </div>
  </body>
</html>
