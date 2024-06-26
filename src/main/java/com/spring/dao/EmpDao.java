package com.spring.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.spring.beans.Acc;
import com.spring.beans.Account;
import com.spring.beans.Client;
import com.spring.beans.Login;
import com.spring.beans.Transaction;

public class EmpDao {
	JdbcTemplate template;

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
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
	
	public void saveTransaction(Transaction transaction) {
        try {
            String sql = "insert into transaction(from_account_id, to_account_id, amount, account_type) values("+ transaction.getFrom_account_id() + ", " + transaction.getTo_account_id() + ", " + transaction.getAmount() + ", '" + transaction.getAccount_type() + "')";
            template.update(sql);

        } catch (DataAccessException e) {
	        // Handle database-related exceptions
	        System.out.println("Error while processing database operation: " + e.getMessage());
	        // You can log the exception or perform any other error handling logic here
	    } catch (Exception e) {
	        // Handle other exceptions
	        System.out.println("Error: " + e.getMessage());
	        // You can log the exception or perform any other error handling logic here
	    }
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
				String account_type = rs.getString("account_type");
				java.sql.Date date = rs.getDate("date");
				Transaction transaction = new Transaction(transactionId, fromAccountId, toAccountId, amount, account_type, date);
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

	public Client getClientByClientId(int client_id) {
		// TODO Auto-generated method stub
		Client client = new Client();
		try {
		String sql = "select * from client where client_id = ?" ;
		PreparedStatement ps = template.getDataSource().getConnection().prepareStatement(sql);
		ps.setInt(1, client_id);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			System.out.println("Inside while of getClientByUserId ");
			int clientId = rs.getInt("client_id");
			System.out.println("clientId "+ clientId);
			String name  = rs.getString("name");
			System.out.println("name "+ name);
			String contact = rs.getString("contact");
			System.out.println("contact "+ contact);
			String email = rs.getString("email");
			System.out.println("email "+ email);
			String password = rs.getString("password");
			System.out.println("password "+ password);
			client = new Client(clientId, name,contact, email, password );
			System.out.println("Client : " + client);
		}
		rs.close();
		ps.close(); 
		}
		catch (SQLException e) {
			e.printStackTrace();
			// Handle the exception as per your application's requirement	
		}
		return client;
		
	}

	public void withdrawMoney(Account acc, int finalBalance) {
		// TODO Auto-generated method stub
		try {
			String sql = "Update  account set balance = ? where account_Id = ?";
			PreparedStatement ps = template.getDataSource().getConnection().prepareStatement(sql);
			ps.setInt(1, finalBalance);
			ps.setInt(2, acc.getAccount_id());
			ps.executeUpdate();
	        System.out.println("Withdraw successful. Updated balance after withdrawal: " + finalBalance);
	    } catch (SQLException e) {
	        System.err.println("Error withdrawing money: " + e.getMessage());
	    }


	}

	public Account getAccountByEmail(String email) {
		// TODO Auto-generated method stub
		Account account = new Account();
		
		try {
			String sql = "select a.account_Id, a.account_type, a.client_id, a.balance "
					+ "from account a inner join client c on a.client_id = c.client_id  "
					+ "where c.email = ? and a.account_type = ? " ;
			PreparedStatement ps = template.getDataSource().getConnection().prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, "Savings");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int account_id = rs.getInt("account_Id");
				String account_type = rs.getString("account_type");
				int client_id = rs.getInt("client_id");
				int balance = rs.getInt("balance");
				account = new Account(account_id,account_type, client_id, balance );
				System.out.println("Account generatd from getAccountByEmail : " + account);
			}
			rs.close();
			ps.close(); 
			}
			catch (SQLException e) {
				e.printStackTrace();
				// Handle the exception as per your application's requirement	
			}
			return account;			
	}

	public List<String> getAccountTypes(Account acc) {
		// TODO Auto-generated method stub
		List<String> accountTypes = new ArrayList<String>();
		try {
			String sql = "select account_type "
					+ "from account"
					+ "where client_id = ? and account_id != ? " ;
			PreparedStatement ps = template.getDataSource().getConnection().prepareStatement(sql);
			ps.setInt(1, acc.getClient_id());
			ps.setInt(2, acc.getAccount_id());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String account_type = rs.getString("account_type");
				accountTypes.add(account_type);

			}
			rs.close();
			ps.close(); 
			}
		catch (SQLException e) {
			e.printStackTrace();
			// Handle the exception as per your application's requirement	
		}
		return accountTypes;	
	}


public void billSuccess(Account acc, int finalBalance) {
	// TODO Auto-generated method stub
	try {
		String sql = "Update  account set balance = ? where account_Id = ?";
		PreparedStatement ps = template.getDataSource().getConnection().prepareStatement(sql);
		ps.setInt(1, finalBalance);
		ps.setInt(2, acc.getAccount_id());
		ps.executeUpdate();
        System.out.println("Payment successful. Updated balance payment: " + finalBalance);
    } catch (SQLException e) {
        System.err.println("Error withdrawing money: " + e.getMessage());
    }


}

}
	
