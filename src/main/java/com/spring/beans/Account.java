package com.spring.beans;

import java.sql.Date;

public class Account {

	private int account_id;

	private String name;

	private int contact;
	
	private String email;
	
	private int balance;
	
	private Date date;

	public Account(int account_id, String name, int contact, String email, int balance, Date date) {
		super();
		this.account_id = account_id;
		this.name = name;
		this.contact = contact;
		this.email = email;
		this.balance = balance;
		this.date = date;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getContact() {
		return contact;
	}

	public void setContact(int contact) {
		this.contact = contact;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Account [account_id=" + account_id + ", name=" + name + ", contact=" + contact + ", email=" + email
				+ ", balance=" + balance + ", date=" + date + "]";
	}

	
}
