<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>Send Money</h1>

	<form action="sendMoney" method="post">
		<label for="amount">Enter Amount:</label>
		 <input type="text" id="balance" name="balance" required> 
		 <input type="submit" value="Deposit">
	</form>

</body>
</html>