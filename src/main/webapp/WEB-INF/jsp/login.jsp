<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark mb-3">
        <span class="navbar-brand">Banking Application</span>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
      
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="loginForm">Login</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="registerForm">Register</a>
                </li>
            </ul>
        </div>
    </nav>
    <div class="container">
        <div class="row">
            <div class="col-12">
                <div class="card mt-5">
                    <h5 class="card-header text-center">Login Existing User</h5>
                    <div class="card-body">
                        <form:form method="post" action="validUser" modelAttribute="client">
					     	<div th:if="${error}" style="color: red;">
    							${error}
							</div>
					     	<div class="row">
                                <div class="col-12">
                                    <div class="form-group">
                                    	<label for="email">Email-Id:</label>
					        			<input type="text" class="form-control" id="email" name="email" />
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-12">
                                    <div class="form-group">
                                    	<label for="password">Password:</label>
					        			<input type="password" class="form-control" id="password" name="password" />
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-12">
                                	<input class="btn btn-primary" type="submit" value="Login" />
                                </div>
                            </div>
                            <div class="row mt-3">
                                <div class="col-12">
                                    <p class="card-text">Don't have an account? <a href="registerForm" class="card-link">Register</a></p>
                                </div>
                            </div>
					    </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
