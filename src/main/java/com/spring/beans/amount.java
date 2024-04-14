package com.spring.beans;

public class amount {
	private int accountId;
	private double balance;

	public amount() {
		super();
	}

	public amount(int accountId, double balance) {
		super();
		this.accountId = accountId;
		this.balance = balance;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

}
