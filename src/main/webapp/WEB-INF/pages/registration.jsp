<!DOCTYPE html>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>Registration</title>
  </head>

  <body>
    <form:form action="registration/proceed" method="post" modelAttribute="registrationForm">
      <p title="Registration form">
        Registration
      </p>

      <div>
        <label title="Login">Login</label>
        <form:input path="login" id="check_login" title="Login" />
      </div>

      <div">
        <label title="Password">Password</label>
        <form:input path="password" id="check_password" type="password" title="Password" />
      </div>

      <div>
        <button>Confirm</button>
      </div>
    </form:form>

  </body>
</html>
