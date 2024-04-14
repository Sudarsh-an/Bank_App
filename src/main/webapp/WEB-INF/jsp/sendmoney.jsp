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
	${account}

	<h3>Your current balance is: ${account.balance}</h3>
	<h2>Withdraw Amount</h2>
	<small>Please enter amount less than the Balance remaining</small>
	<h1>Send Money</h1>

	<form:form method="post" action="send" modelAttribute="sendmoney">
		<div th:if="${error}" th:text="${error}" style="color: red;">
			${error}</div>
		<br />
		<input type="number" id="accountId" value=${account.account_id} name="accountId" hidden />
		<label for="balance">Enter Amount to Send:</label>
		<input type="number" id="amount" name="amount" />
		<br />
		<br />
		
		<label for="email">Enter Recipient Email:</label>
		<input type="email" id="email" name="email" />
		<br />
		<br />
		<input type="submit" value="Withdraw" />
	</form:form>

</body>
</html>