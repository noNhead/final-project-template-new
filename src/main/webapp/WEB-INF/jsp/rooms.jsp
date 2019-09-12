<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>

<body>
	Список комнат
	<br>
	<br>
	<table>
    		<c:forEach var="room" items="${rooms}">
    			<tr>
    				<td><a href="room/${room.id}">${room.name}</a> - </td>
    				<td>${room.description}</td>
    			</tr>
    		</c:forEach>
    	</table>


    	<form action="room/search" method="get">
                		<fieldset>
                			<legend>Поиск</legend>
                			<p>
                				<label>Поиск</label>
                				<input name="word">
                			</p>
                		</fieldset>

                		<p>
                			<input type="submit" value="Найти">
                		</p>
                	</form>
</body>
</html>



