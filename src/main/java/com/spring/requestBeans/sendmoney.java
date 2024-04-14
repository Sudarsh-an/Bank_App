package com.spring.requestBeans;

public class sendmoney {
	
	private int amount;
	
	private String email;
	
	private int accountId;

	public sendmoney(int amount, String email, int accountId) {
		super();
		this.amount = amount;
		this.email = email;
		this.accountId = accountId;
	}

	public sendmoney() {
		super();
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	@Override
	public String toString() {
		return "sendmoney [amount=" + amount + ", email=" + email + ", accountId=" + accountId + "]";
	}
	

}
