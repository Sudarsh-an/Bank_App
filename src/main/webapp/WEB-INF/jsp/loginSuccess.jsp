  <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  


${client }

Account List : ${accountList }

<h1>Hi ${client.getName()}, Login Success</h1>

<h1>Account Details: </h1>
<table border="2" width="70%" cellpadding="2">
	<tr>
	<th>Account-Id</th><th>Account-Type</th><th>Balance</th><th>Deposit Option</th><th>Withdraw Option</th>
	<th>Send Money</th>
	</tr>
    <c:forEach var="account" items="${accountList}"> 
    <tr>
    <td>${account.account_id}</td>
    <td>${account.account_type}</td>
    <td>${account.balance}</td>

   
    <td><a href="deposit/${account.account_id}">Deposit</a></td>
    <td><a href="withdraw/${account.account_id}">Withdraw</a></td>
    <td><a href="sendMoney/${account.account_id}">Send Money</a></td>
    
    </tr>
    </c:forEach>
    </table>
<div class = "col-md-4">
 <a href="transactionHistory/${account.getAccount_id()}">Transaction History</a>

</div>
</div>
</div>