package com.spring.beans;

import java.sql.Date;

public class Account {

	private int account_id;

	private String account_type;

	private int client_id;
		
	private int balance;

	public Account(int account_id, String account_type, int client_id, int balance) {
		super();
		this.account_id = account_id;
		this.account_type = account_type;
		this.client_id = client_id;
		this.balance = balance;
	}
	
	

	public Account() {
		super();
	}



	public int getAccount_id() {
		return account_id;
	}

	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}

	public String getAccount_type() {
		return account_type;
	}

	public void setAccount_type(String account_type) {
		this.account_type = account_type;
	}

	public int getClient_id() {
		return client_id;
	}

	public void setClient_id(int client_id) {
		this.client_id = client_id;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Account [account_id=" + account_id + ", account_type=" + account_type + ", client_id=" + client_id
				+ ", balance=" + balance + "]";
	}
	
	
}
