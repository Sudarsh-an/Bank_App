<h1>Hi ${account.getName()} Login Success</h1>

<div class="align-center">
<div class= "row">
<div class = "col-md-3">
<h3> Balance: </h3>
</div>
<div class = "col-md-3">
 <span style="font-size: large;">${ account.getBalance()} </span> 
</div>
<div class="row">
<div class = "col-md-4">
<button >Send Money</button>
</div>
<div class = "col-md-4">
 <a href="transactionHistory/${account.getAccount_id()}">Transaction History</a>

</div>
</div>
</div>