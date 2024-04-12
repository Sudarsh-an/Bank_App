package com.spring.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.spring.beans.Acc;
import com.spring.beans.Account;
import com.spring.beans.Client;
import com.spring.beans.Emp;
import com.spring.beans.Login;
import com.spring.beans.Transaction;

public class EmpDao {
	JdbcTemplate template;

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

//	public int save(Account acc) {
//		String sql = "insert into account(account_id,name,contact,email) values(" + acc.getAccount_id() + ",'"
//				+ acc.getName() + "'," + acc.getContact() + ",'" + acc.getEmail() + "')";
//		return template.update(sql);
//	}

	public int update(Emp p) {
		String sql = "update emp99 set name='" + p.getName() + "', salary=" + p.getSalary() + ",designation='"
				+ p.getDesignation() + "' where id=" + p.getId() + "";
		return template.update(sql);
	}

	public int delete(int id) {
		String sql = "delete from emp99 where id=" + id + "";
		return template.update(sql);
	}

	public Emp getEmpById(int id) {
		String sql = "select * from emp99 where id=?";
		System.out.println("Getting record....");
		return template.queryForObject(sql, new Object[] { id }, new BeanPropertyRowMapper<Emp>(Emp.class));
	}

	public List<Emp> getEmployees() {
		return template.query("select * from emp99", new RowMapper<Emp>() {
			public Emp mapRow(ResultSet rs, int row) throws SQLException {
				Emp e = new Emp();
				e.setId(rs.getInt(1));
				e.setName(rs.getString(2));
				e.setSalary(rs.getFloat(3));
				e.setDesignation(rs.getString(4));
				return e;
			}
		});
	}

	public boolean validUser(Client client) {
		// TODO Auto-generated method stub
		try {
		String query = "SELECT COUNT(*) FROM client WHERE email = ? AND password = ?";
		int count = 0;
		count = template.queryForObject(query, Integer.class, client.getEmail(), client.getPassword());
		System.out.println("EmpDao validUser: count : " + count + "client: " + client  );
		return count > 0;
		}
		catch (DataAccessException e) {
	        // Log the exception or handle it as needed
	        e.printStackTrace();
	        return false;
	    }
	}

	public Account getUserById(int account_id) {
		// TODO Auto-generated method stub
		String sql = "select * from account where account_id=?";
		System.out.println("Getting record....");
		return template.queryForObject(sql, new Object[] { account_id },
				new BeanPropertyRowMapper<Account>(Account.class));
	}

	public List<Transaction> getTransactionsById(int id) {
		// TODO Auto-generated method stub
		// String sql="select * from Transaction where from_account_id=?";

		List<Transaction> transactions = new ArrayList<>();
		String sql = "SELECT * FROM Transaction WHERE from_account_id = ?";
		try {
			PreparedStatement ps = template.getDataSource().getConnection().prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int transactionId = rs.getInt("transaction_id");
				int fromAccountId = rs.getInt("from_account_id");
				int toAccountId = rs.getInt("to_account_id");
				int amount = rs.getInt("amount");
				java.sql.Date date = rs.getDate("date");
				Transaction transaction = new Transaction(transactionId, fromAccountId, toAccountId, amount, date);
				transactions.add(transaction);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
			// Handle the exception as per your application's requirement
		}
		return transactions;
	}
	// return null;

	public void registerUser(Acc acc) {
		// TODO Auto-generated method stub
		   try {
		String sql = "insert into client(name,contact,email,password) values('" + acc.getClient().getName() + "','"
				+ acc.getClient().getContact() + "','" + acc.getClient().getEmail() + "','"
				+ acc.getClient().getPassword() + "')";
		template.update(sql);

		sql = "select * from client where email=?";
		System.out.println("Getting record....");
		Client cl = template.queryForObject(sql, new Object[] { acc.getClient().getEmail() },
				new BeanPropertyRowMapper<Client>(Client.class));

		for (String acc_type : acc.getAccount_type()) {
			sql = "insert into account(account_type,client_id) values('" + acc_type + "'," + cl.getClient_id() + ")";
			template.update(sql);

		}
		}
		   catch (DataAccessException e) {
		        // Handle database-related exceptions
		        System.out.println("Error while processing database operation: " + e.getMessage());
		        // You can log the exception or perform any other error handling logic here
		    } catch (Exception e) {
		        // Handle other exceptions
		        System.out.println("Error: " + e.getMessage());
		        // You can log the exception or perform any other error handling logic here
		    }
	}

	public Client getClientByEmailId(String email) {
		// TODO Auto-generated method stub
		try {
		String sql = "select * from client where email=?";
		System.out.println("Getting getClientByEmailId....");
		return template.queryForObject(sql, new Object[] { email}, new BeanPropertyRowMapper<Client>(Client.class));
		}
		catch (DataAccessException e) {
	        // Log the exception or handle it as needed
	        e.printStackTrace();
	        return null;
	    }
	}

	public List<Account> getAccountsOfClient(Client client) {
		// TODO Auto-generated method stub
		
		List<Account> accounts = new ArrayList<Account>();


		try {
			String sql = "SELECT * FROM account WHERE client_id = ?";
			System.out.println("1.getAccountsOfClient ");
			System.out.println("2.getAccountsOfClient ");

			PreparedStatement ps = template.getDataSource().getConnection().prepareStatement(sql);
			ps.setInt(1, client.getClient_id());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println("Inside while of getAccountsOfClient ");
				int accountId = rs.getInt("account_Id");
				System.out.println("account_Id "+ accountId);
				String account_type  = rs.getString("account_type");
				System.out.println("account_type[0] "+ account_type);
				int clientId = rs.getInt("client_id");
				System.out.println("clientId "+ clientId);
				int balance = rs.getInt("balance");
				Account account = new Account(accountId, account_type,client.getClient_id(), balance );
				System.out.println("each Account of client : " + account);
				accounts.add(account);
				
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
			// Handle the exception as per your application's requirement	
		}
		return accounts;
	}

	public void depositMoney(Account acc, int finalBalance) {
		// TODO Auto-generated method stub
		
		try {
		String sql = "Update  account set balance = ? where account_Id = ?";
		PreparedStatement ps = template.getDataSource().getConnection().prepareStatement(sql);
		ps.setInt(1, finalBalance);
		ps.setInt(2, acc.getAccount_id());
		ps.executeUpdate();
        System.out.println("Deposit successful. Updated balance: " + finalBalance);
    } catch (SQLException e) {
        System.err.println("Error depositing money: " + e.getMessage());
    }


		
		}
		
	
}
