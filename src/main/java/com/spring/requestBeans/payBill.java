package com.spring.requestBeans;

public class payBill 
{
	
private int account_Id;
private int amount;
private String paybill;
public payBill(int account_Id, int amount, String paybill) {
	super();
	this.account_Id = account_Id;
	this.amount = amount;
	this.paybill = paybill;
}
public payBill() {
	super();
}
public int getAccount_Id() {
	return account_Id;
}
public void setAccount_Id(int account_Id) {
	this.account_Id = account_Id;
}
public int getAmount() {
	return amount;
}
public void setAmount(int amount) {
	this.amount = amount;
}
public String getPaybill() {
	return paybill;
}
public void setPaybill(String paybill) {
	this.paybill = paybill;
}
@Override
public String toString() {
	return "payBills [account_Id=" + account_Id + ", amount=" + amount + ", paybill=" + paybill + "]";
}


}
