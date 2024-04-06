<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  

		<h1>Register</h1>

       
       <form:form method="post" action="registerUser" modelAttribute="acc">
        <label for="account_id">Account-Id:</label>
        <input type="text" id="account_id" name="account_id" />
        <br/>
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" />
        <br/>
         <label for="contact">Contact:</label>
        <input type="text" id="contact" name="contact" />
        <br/>
        <label for="email">Email:</label>
        <input type="text" id="email" name="email" />
        <br/>
        <input type="submit" value="Register" />
    </form:form>
