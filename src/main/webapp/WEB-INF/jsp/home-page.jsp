<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>

<body>
	${helloPhrase}${userName}

	<br>
${currentUserRole}

	<c:if test="${currentUserRole == 'ADMIN'}">
Кнопка доступная админу
</c:if>

	<table>
		<c:forEach var="bookIterator" items="${books}">
			<tr>
				<td><a href="/book/${bookIterator.id}">${bookIterator.name}</a></td>
				<td>${bookIterator.author}</td>
			</tr>
		</c:forEach>
	</table>



</body>
</html>



