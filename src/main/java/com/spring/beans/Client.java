package com.spring.beans;

public class Client {
	private int client_id;
	
	private String name;
	
	private String password;
	
	private String contact;
	
	private String email;

	public Client(int client_id, String name, String password, String contact, String email) {
		super();
		this.client_id = client_id;
		this.name = name;
		this.password = password;
		this.contact = contact;
		this.email = email;
	}

	public Client() {
		super();
	}

	public int getClient_id() {
		return client_id;
	}

	public void setClient_id(int client_id) {
		this.client_id = client_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Client [client_id=" + client_id + ", name=" + name + ", password=" + password + ", contact=" + contact
				+ ", email=" + email + "]";
	}
	
	
	
	
	
}
