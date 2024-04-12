<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h1>Register</h1>

<form:form method="post" action="registerUser" modelAttribute="acc">
	<br />
	<label for="client.name">Name:</label>
	<input type="text" id="name" name="client.name" />
	<br />
	<br />
	<label for="client.contact">Contact:</label>
	<input type="text" id="contact" name="client.contact" />
	<br />
	<br />
	<label for="client.email">Email:</label>
	<input type="text" id="email" name="client.email" />
	<br />
	<br />
	<label for="client.password">Password:</label>
	<input type="password" id="password" name="client.password" />
	<br />
	<br />
	 <label for="account_type">Select Account Types:</label><br>
    <input type="checkbox" id="savings" name="account_type" value="Savings">
    <label for="savings">Savings</label><br>
    <input type="checkbox" id="checkin" name="account_type" value="Checkin">
    <label for="checkin">Check-in</label><br>
    <input type="checkbox" id="tfsa" name="account_type" value="TFSA">
    <label for="tfsa">TFSA</label><br><br>
	<br />
	<br />
	<input type="submit" value="Register" />
</form:form>


