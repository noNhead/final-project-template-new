<!DOCTYPE html>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html lang="ru" dir="ltr">
<head>
    <meta charset="utf-8">
    <title>Подтверждение пароля</title>
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
    <form:form action="checkuserpass/proceed" method="post" modelAttribute="checkUserPass">
        <table>
            <tr>
                <td><p title="Book data change form by title and author">Подтвердите пароль от своего аккаунта</p></td>
            </tr>
            <tr><td>
                <table>
                    <tr>
                        <td class="subName"><label title="Password">Пароль</label></td>
                        <td><form:input path="password" type="password" id="check_password" name="password" /></td>
                    </tr>
                    <tr><td></td><td><button>Подтвердить</button></td></tr>
                </table>
            </td>
            </tr>
        </table>
    </form:form>
</div>
</body>
</html>