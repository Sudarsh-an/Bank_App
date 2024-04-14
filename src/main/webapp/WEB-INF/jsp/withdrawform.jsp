
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



${account.account_id}
<h3>Your current balance is: ${account.balance}</h3>
<h2>Withdraw Amount</h2> <small>Please enter amount less than the Balance remaining</small>
<form:form method="post" action="withdrawMoney" modelAttribute="amount">
	<div th:if="${error}" th:text="${error}" style="color: red;">
     	${error}
     	</div>
	<br />
	<input type="number" id="accountId" value=${account.account_id} name="accountId" hidden/>
	<label for="balance">Enter Amount:</label>
	<input type="number" id="balance" name="balance" />
	<br />
	<br />
	<input type="submit" value="Withdraw" />
</form:form>


