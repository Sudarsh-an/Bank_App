<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>LoginSuccess</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        .form-container {
            border: 1px solid #dee2e6;
            border-radius: 8px;
            transition: box-shadow 0.3s ease-in-out;
            padding: 6.25rem;
            margin-bottom: 1rem;
        }

        .form-container:hover {
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        .form-title {
            margin-bottom: 0.75rem;
            font-size: 1.25rem;
        }

        .form-text {
            margin-bottom: 0.5rem;
            color: #6c757d;
        }

        .form-footer {
            padding: 0.75rem 1.25rem;
            background-color: #f8f9fa;
            border-top: 1px solid #dee2e6;
            border-bottom-left-radius: 8px;
            border-bottom-right-radius: 8px;
        }

        .form-footer a {
            margin-right: 0.5rem;
            color: #007bff;
            text-decoration: none;
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
                    <a class="nav-link" id="logout-btn" href="#">Logout</a>
                </li>
            </ul>
        </div>
    </nav>

    <div class="container">
        <h1 class="text-center">Hi ${client.getName()}, Login Success</h1>

        <div class="row justify-content-center">
            <c:forEach var="account" items="${accountList}">
                <div class="col-md-6 mb-3">
                    <div class="form-container">
                        <h5 class="form-title">Account Type: ${account.account_type}</h5>
                        <p class="form-text">Account ID: ${account.account_id}</p>
                        <p class="form-text">Balance: ${account.balance}</p>
                        <div class="form-footer">
                            <a href="deposit/${account.account_id}">Deposit</a> 
                            <a href="withdraw/${account.account_id}">Withdraw</a>
                            <a href="sendMoney/${account.account_id}">Send Money</a> <br>
                            <a href="transactionHistory/${account.getAccount_id()}">Transaction History</a>
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