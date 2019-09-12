<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="header.jsp"%>
<html>

<body>

	Название комнаты : ${room.name}
	<br>
	Описание комнаты : ${room.description}
	<br>
	<br>
	<table>
    		<c:forEach var="message" items="${room.messages}">
    			<tr>
    				<td>${message.creationTime} - </td>
    				<td>${message.author.name} : </td>
    				<td>${message.message}</td>
    			</tr>
    		</c:forEach>
    	</table>

    	<br>
        	<br>
    	<form action="${room.id}/message" method="post">
        		<fieldset>
        			<legend>Сообщение</legend>
        			<p>
        				<label>Сообщение</label>
        				<input name="message">
        			</p>
        		</fieldset>

        		<p>
        			<input type="submit" value="Отправить">
        		</p>
        	</form>
</body>
</html>



