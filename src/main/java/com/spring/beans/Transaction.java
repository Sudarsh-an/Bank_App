package com.spring.beans;

import java.sql.Date;

public class Transaction {

	private int transaction_id;

	private int from_account_id;

	private int to_account_id;

	private int amount;
	
	private String account_type;

	private Date date;

	public Transaction(int transaction_id, int from_account_id, int to_account_id, int amount, String account_type,
			Date date) {
		super();
		this.transaction_id = transaction_id;
		this.from_account_id = from_account_id;
		this.to_account_id = to_account_id;
		this.amount = amount;
		this.account_type = account_type;
		this.date = date;
	}

	public Transaction() {
		// TODO Auto-generated constructor stub
		super();
	}

	public int getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(int transaction_id) {
		this.transaction_id = transaction_id;
	}

	public int getFrom_account_id() {
		return from_account_id;
	}

	public void setFrom_account_id(int from_account_id) {
		this.from_account_id = from_account_id;
	}

	public int getTo_account_id() {
		return to_account_id;
	}

	public void setTo_account_id(int to_account_id) {
		this.to_account_id = to_account_id;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public String getAccount_type() {
		return account_type;
	}

	public void setAccount_type(String account_type) {
		this.account_type = account_type;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Transaction [transaction_id=" + transaction_id + ", from_account_id=" + from_account_id
				+ ", to_account_id=" + to_account_id + ", amount=" + amount + ", account_type=" + account_type + ", date=" + date + "]";
	}

	

}
