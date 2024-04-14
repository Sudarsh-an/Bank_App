package com.spring.beans;

public class Acc {
	
	private int account_id;
	
	private String[] account_type;
	
	private int balance;
	
	private Client client;
	
	
	public Acc(int account_id, String account_type[], int balance, Client client) {
		super();
		this.account_id = account_id;
		this.account_type = account_type;
		this.balance = balance;
		this.client = client;
	}


	public Acc() {
		super();
	}


	public int getAccount_id() {
		return account_id;
	}


	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}


	public String[] getAccount_type() {
		return account_type;
	}


	public void setAccount_type(String account_type[]) {
		this.account_type = account_type;
	}


	public int getBalance() {
		return balance;
	}


	public void setBalance(int balance) {
		this.balance = balance;
	}


	public Client getClient() {
		return client;
	}


	public void setClient(Client client) {
		this.client = client;
	}


	@Override
	public String toString() {
		return "Acc [account_id=" + account_id + ", account_type=" + account_type + ", balance=" + balance + ", client="
				+ client + "]";
	}
	
	



}
