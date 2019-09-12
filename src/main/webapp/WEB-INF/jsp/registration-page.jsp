<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Регистрация</title>

<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js" type="text/javascript"></script> -->
<!-- <script src="/js/form-login-input-ajax-check.js" type="text/javascript"></script> -->

</head>
<body>
${lastregistration}
<br>
<br>
	<form action="registration" method="post" id="registration-form">
		<fieldset>
			<legend>Регистрация</legend>
			<p>
				<label>Login<em>*</em></label>
				<input name="login" id="login">
			</p>
			<p>
				<label>Пароль<em>*</em></label>
				<input name="password">
			</p>
			<p>
				<label>Имя</label>
				<input name="name">
			</p>
			<p>
				<label>E-mail</label>
				<input name="email">
			</p>
			
<!-- 			<p> -->
<!-- 				<label>Дата рождения</label> -->
<!-- 				<input name="birthday"> -->
<!-- 			</p> -->
			
<!-- 			<p> -->
<!-- 				<label>Enum (ADMINISTRATOR/USER)</label> -->
<!-- 				<input name="role"> -->
<!-- 			</p> -->
		</fieldset>

		<p>
			<input type="submit" value="Регистрация">
		</p>
	</form>
	<br>
	
	<br>
	<c:forEach var="error" items="${errors}">

			${error.defaultMessage}<br>

	</c:forEach>

	${errorMsg}
</body>
</html>