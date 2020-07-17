<!DOCTYPE html>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<html>
  <head>
    <meta charset="UTF-8">
    <title>Sign up</title>
    <script type="text/javascript" src="./js/main.js"></script>
    <title>library - login</title>
    <style><%@include file="/WEB-INF/css/style.css"%></style>
    <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">
  </head>

  <body>
  <div class="container">
  <header>
    <!--<div class="logo navigationElement"><img src="" alt=""></div>-->
    <div class="navigationElement t_font_link"><a href="" class="navLink">Главная</a></div>
    <div class="navigationElement t_font_link"><a href="" class="navLink">Книги</a></div>
    <div class="navigationElement t_font_link"><a href="" class="navLink">Авторы</a></div>
    <div class="t_font_link login"><a href="" class="navLink">Войти</a></div>
    <div class="navigationElement t_font_link"></div>
  </header>
    <div class="mainPage">
      <div class="leftNavigation"></div>
      <div class="page">
        <div class="loginForm">
          <div class="subRegForm">
    <form action="login/process" method="post">
      <p title="Login form">Sign up</p>
      <div class="group">
        <label for="">Login</label>
        <input name="login" />
      </div>

      <div class="group">
        <label for="">Password</label>
        <input name="password" type="password" />
        <div class="error">${error_login_placeholder}</div>
      </div>


      <div class="group">
        <button class="button">Confirm</button>
      </div>
    </form>

    <form action="registration" method="get">
      <div>
        <button class="button">registration</button>
      </div>
    </form>
          </div>
        </div>
      </div>
      <div class="rightNavigation"></div>
    </div>
    <footer><!--<div class="arr">All Rights Reserved</div>--></footer>
  </div>
  </body>
</html>
