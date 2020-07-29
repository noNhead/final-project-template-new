<!DOCTYPE html>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html lang="ru" dir="ltr">
<head>
    <meta charset="utf-8">
    <title>Изменение данных пользователя</title>
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
        <form:form action="userdatachange/proceed" modelAttribute="userDataChange">
            <tr><td class="headName"><p title="Change Password">Изменение пароля</p></td></tr>
            <tr>
                <td><table><tr><td class="subName"><label title="Password">Изменение пароля</label></td>
                    <td><form:input path="password" id="check_password" title="Password"/></td>
                </tr>
                    <tr><td></td><td><button>Подтвердить</button></td></tr>
                    <tr><td></td><td><form method="LINK" action="userdatachange/delete/process"><input type="submit" value="Удалить аккаунт"></form></td></tr>
                </table></td></tr>
        </form:form>

    </table>
</div>
</body>
</html>