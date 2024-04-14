<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

${client }

Account List : ${accountList }

<h1>Hi ${client.getName()}, Login Success</h1>

<p>Test: ${amount.getAccountId()}</p>
<p>Test: ${amount.getBalance()}</p>

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

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>LoginSuccess</title>
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
                <li class="nav-item">
                    <a class="nav-link" id="logout-btn" href="#">Logout</a>
                </li>
            </ul>
        </div>
    </nav>

    <div class="container">
        <div class="row mb-5">
            <div class="col-12">
                <div class="card">
                    <div class="card-body">
                        <div class="row">
                            <div class="col-12">
                                <h5 class="card-title text-center">Welcome ${client.getName()}!</h5>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-2 text-center">
                                <p class="card-text">User Id: ${client.getClient_id() }</p>
                            </div>
                            <div class="col-2 text-center">
                                <p class="card-text">User Name: ${client.getName()}</p>
                            </div>
                            <div class="col-2 text-center">
                                <p class="card-text">Email: ${client.getEmail() }</p>
                            </div>
                            <div class="col-2 text-center">
                                <p class="card-text">Account: ${account.getAccount_type() }</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-6">
                <div class="card">
                    <div class="card-header" id="account-info-account-header">
                    </div>
                    <div class="card-body">
                        <form>
                            <div class="row">
                                <div class="col-12 d-flex">
                                    <button class="btn btn-success ml-auto" id="account-info-backbtn">Back home</button>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-12">
                                    <div class="form-group">
                                        <label for="account-info-balance">Balance</label>
                                        <input type="text" class="form-control-plaintext" id="account-info-balance" readonly>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-12">
                                    <div class="form-group">
                                        <label for="account-info-status">Account Status</label>
                                        <select class="form-control" id="account-info-status">
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-12">
                                    <div class="form-group">
                                        <label for="account-info-type">Account Type</label>
                                        <input type="text" class="form-control-plaintext" id="account-info-type" readonly>
                                    </div>
                                </div>
                            </div>
                            <div class="row mb-3" id="modify-account-btn-container">
                                <div class="col-4" id="account-info-editbtn-container">
                                </div>
                                <div class="col-4" id="account-info-savebtn-container">
                                </div>
                                <div class="col-4" id="account-info-deletebtn-container">
                                </div>
                            </div>
                            <div class="row mb-3" id="act-on-account-btn-container">
                                <div class="col-3">
                                    <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#deposit-form">Deposit</button>
                                </div>
                                <div class="col-3">
                                    <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#withdraw-form">Withdraw</button>
                                </div>
                                <div class="col-3">
                                    <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#transfer-form">Transfer</button>
                                </div>
                                <div class="col-3">
                                    <button class="btn btn-primary" id="add-user-toggle" type="button" data-toggle="collapse" data-target="#add-user-form">Add user to account</button>
                                </div>
                            </div>
                        </form>
                        <form class="collapse" id="deposit-form">
                            <div class="row">
                                <div class="col-6">
                                    <div class="form-group">
                                        <label for="deposit-amount">Deposit Amount</label>
                                        <input type="text" class="form-control" id="deposit-amount">
                                    </div>
                                </div>
                                <div class="col-6 d-flex" id="deposit-submitbtn-container">
                                </div>
                            </div>
                        </form>
                        <form class="collapse" id="withdraw-form">
                            <div class="row">
                                <div class="col-6">
                                    <div class="form-group">
                                        <label for="withdraw-amount">Withdraw Amount</label>
                                        <input type="text" class="form-control" id="withdraw-amount">
                                    </div>
                                </div>
                                <div class="col-6 d-flex" id="withdraw-submitbtn-container">
                                </div>
                            </div>
                        </form>
                        <form class="collapse" id="transfer-form">
                            <div class="row">
                                <div class="col-4">
                                    <div class="form-group">
                                        <label for="transfer-amount">Transfer Amount</label>
                                        <input type="text" class="form-control" id="transfer-amount">
                                    </div>
                                </div>
                                <div class="col-4">
                                    <div class="form-group">
                                        <label for="transfer-target">Transfer Target Account ID</label>
                                        <input type="text" class="form-control" id="transfer-target">
                                    </div>
                                </div>
                                <div class="col-4 d-flex" id="transfer-submitbtn-container">
                                </div>
                            </div>
                        </form>
                        <form class="collapse" id="add-user-form">
                            <div class="row">
                                <div class="col-6">
                                    <div class="form-group">
                                        <label for="added-user">User ID to add to account</label>
                                        <input type="text" class="form-control" id="added-user">
                                    </div>
                                </div>
                                <div class="col-6 d-flex" id="add-user-submitbtn-container">
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
	</div>
    
        

    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" ></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>

