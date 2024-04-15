<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Success</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
    
        .navbar {
            background-color: #343a40 !important;
            color: #ffffff;
        }
        .form-container {
            border: 3px solid #dee2e6;
    border-radius: 8px;
    transition: box-shadow 0.3s ease-in-out;
    padding: 80px;
    margin-bottom: 20px;
    background-color: #233649e6;}

        .form-container:hover {
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        .form-title {
            margin-bottom: 10px;
            font-size: 1.5rem;
            font-weight: bold;
            color: #ffff;
        }

        .form-text {
            margin-bottom: 10px;
            font-size: 1.1rem;
            color: #ffff;
        }

        .form-footer {
            padding-top: 10px;
            border-top: 1px solid #dee2e6;
        }

        .form-footer a {
            margin-right: 10px;
            color:#ffff;
            text-decoration: none;
            transition: color 0.3s;
        }

        .form-footer a:hover {
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
        <h1 class="text-center">Hi ${client.getName()}, Welcome to Your Account</h1> <br>

        <div class="row justify-content-center">
            <c:forEach var="account" items="${accountList}">
                <div class="col-md-6 mb-3">
                    <div class="form-container">
                        <h2 class="form-title">Account Type: ${account.account_type}</h2>
                        <p class="form-text">Account ID: ${account.account_id}</p>
                        <p class="form-text">Balance: ${account.balance}</p>
                        <div class="form-footer">
                            <a href="${pageContext.request.contextPath}/deposit/${account.account_id}">Deposit</a>
                            <a href="${pageContext.request.contextPath}/withdraw/${account.account_id}">Withdraw</a>
                            <a href="${pageContext.request.contextPath}/sendMoney/${account.account_id}">Send Money</a>
                            <a href="${pageContext.request.contextPath}/transactionHistory/${account.getAccount_id()}">Transaction History</a>
                            <a href="${pageContext.request.contextPath}/sendMoneyBnAccounts/${account.getAccount_id()}">Transfer Funds</a>
                            
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
