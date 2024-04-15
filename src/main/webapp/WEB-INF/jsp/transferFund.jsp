<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Deposit Money</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            margin: 0;
            padding: 0;
        }

        .navbar {
            background-color: #343a40 !important;
            color: #ffffff;
        }

        .form-container {
            background-color: #ffffff;
            border: 1px solid #ced4da;
            border-radius: 8px;
            padding: 20px;
            margin: 20px auto;
            max-width: 400px;
            box-shadow: 0px 0px 10px 0px rgba(0,0,0,0.1);
        }

        label {
            font-weight: bold;
        }

        input[type="number"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ced4da;
            border-radius: 4px;
            box-sizing: border-box;
        }

        input[type="submit"] {
            background-color: #007bff;
            color: #ffffff;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #0056b3;
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
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                  Account Actions
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                  <a class="dropdown-item" href="<c:url value='/withdraw/${account.account_id}' />">Withdraw</a>
                  <a class="dropdown-item" href="<c:url value='/sendMoney/${account.account_id}' />">Send Money</a>
                  <a class="dropdown-item" href="<c:url value='/transactionHistory/${account.account_id}' />">Transaction History</a>
                  <div class="dropdown-divider"></div>
                  <a class="dropdown-item" href="<c:url value='/loginSuccess?clientId=${account.client_id}' />">Back to Dashboard</a>
                </div>
              </li>
        </ul>
    </div>
</nav>
 

    <div class="container">
        <div class="form-container">
            <h2 class="text-center mb-4">Deposit Money</h2> <br>
            <h3 class="text-center mb-4" class="form-title">Account Type: ${account.account_type}</h3> <br>
            

            <form:form method="post" action="sendMoneyBnAccounts" modelAttribute="amount">
                <input type="number" id="accountId" value="${account.account_id}" name="accountId" hidden/>
                <div class="form-group">
                    <label for="balance">Enter Amount:</label>
                    <input type="number" id="balance" name="balance" class="form-control" />
                </div>
                <div class="text-center">
                    <input type="submit" value="Deposit" class="btn btn-primary" />
                </div><br> <br>
            </form:form>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
