<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
  </body>
</html>