<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  

     <h1>Login</h1>  

     <form:form method="post" action="validUser" modelAttribute="login">
     	<div th:if="${error}" th:text="${error}" style="color: red;"></div>
        <label for="account_id">Account-Id:</label>
        <input type="text" id="account_id" name="account_id" />
        <br/>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" />
        <br/>
        <input type="submit" value="Login" />
    </form:form>
