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


<h1>Transaction List</h1>
	<table border="2" width="70%" cellpadding="2">
	<tr>
	<th>To_Account_Id</th>
	<th>Amount</th>
	<th>Date</th>
	</tr>
    <c:forEach var="tra" items="${list}"> 
    <tr>
    <td>${tra.to_account_id}</td>
    <td>${tra.amount}</td>
    <td>${tra.date}</td>
    </tr>
    </c:forEach>
    </table>
    <br/>
    <a href="loginSuccess">Press here to go back</a>
${list} 
</body>
</html>