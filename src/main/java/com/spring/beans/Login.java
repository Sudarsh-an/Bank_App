package com.spring.beans;

public class Login {

	private int account_id;

	private String password;

	public Login(int account_id, String password) {
		super();
		this.account_id = account_id;
		this.password = password;
	}

	public Login() {
		// TODO Auto-generated constructor stub
	}

	public int getAccount_id() {
		return account_id;
	}

	public void setAccount_id(int client_id) {
		this.account_id = client_id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Login [client_id=" + account_id + ", password=" + password + "]";
	}

}
