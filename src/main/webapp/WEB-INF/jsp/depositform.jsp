
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<%@page  isELIgnored= "false" %>



${account}
<div class="row">
<div class="col-md-4">
<h2>Current Balance : </h2>
</div>
<div class="col-md-4">
<h2>${account.balance }</h2>
</div>
</div>


<form:form method="post" action="registerUser" modelAttribute="acc">
	<br />
	<label for="client.name">Name:</label>
	<input type="text" id="name" name="client.name" />
	<br />
	<br />
	
</form:form>
	
    
