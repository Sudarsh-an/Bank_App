<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Deposit Successful</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 600px;
            margin: 50px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            text-align: center;
        }

        h1 {
            font-size: 32px;
            margin-bottom: 20px;
            color: #007bff;
        }

        p {
            font-size: 18px;
            color: #6c757d;
        }

        a {
            color: #007bff;
            text-decoration: none;
            transition: color 0.3s;
        }

        a:hover {
            color: #0056b3;
        }
    </style>
</head>
<body>
 <nav class="navbar navbar-expand-lg navbar-dark bg-dark mb-3">
    <span class="navbar-brand">Banking Application</span>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
  
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/loginForm">Logout</a>
            </li>
            <!-- Add margin-right to create space -->
            <li class="nav-item" style="margin-right: 10px;">
                    <a class="nav-link" href="${pageContext.request.contextPath}/registerForm">Register</a>
            </li>
        </ul>
    </div>
</nav>
 

    <div class="container">
        <h1>Deposit Successful</h1> 
          <h5 class="form-title">Account Type: ${account.account_type}</h5><br>
                   <h4 class="card-subtitle mb-3">Your Balance is: ${account.balance}</h4>
        <a href="<c:url value='/withdraw/${account.account_id}' />">Withdraw</a><br>
<a href="<c:url value='/sendMoney/${account.account_id}' />">Send Money</a> <br>
<a href="<c:url value='/transactionHistory/${account.account_id}' />">Transaction History</a> <br>



        
    </div>
</body>
</html>
