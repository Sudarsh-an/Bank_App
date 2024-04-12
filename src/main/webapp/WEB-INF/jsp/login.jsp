<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  

     <h1>Login</h1>  

     <form:form method="post" action="validUser" modelAttribute="client">
     
     	<div th:if="${error}" th:text="${error}" style="color: red;">
     	${error}
     	</div>
        <label for="email">Email-Id:</label>
        <input type="text" id="email" name="email" />
        <br/>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" />
        <br/>
        <input type="submit" value="Login" />
    </form:form>
