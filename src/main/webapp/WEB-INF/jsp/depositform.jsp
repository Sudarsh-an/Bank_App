
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



${account.account_id}

<form:form method="post" action="depositMoney" modelAttribute="amount">
	<br />
	<input type="number" id="accountId" value=${account.account_id} name="accountId" hidden/>
	<label for="balance">Enter Amount:</label>
	<input type="number" id="balance" name="balance" />
	<br />
	<br />
	<input type="submit" value="Deposit" />
</form:form>


