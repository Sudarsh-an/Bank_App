package com.spring.dao;  
import java.sql.PreparedStatement;
import java.sql.ResultSet;  
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;  
import org.springframework.jdbc.core.BeanPropertyRowMapper;  
import org.springframework.jdbc.core.JdbcTemplate;  
import org.springframework.jdbc.core.RowMapper;

import com.spring.beans.Account;
import com.spring.beans.Emp;
import com.spring.beans.Login;
import com.spring.beans.Transaction; 

public class EmpDao {  
JdbcTemplate template;  
  
public void setTemplate(JdbcTemplate template) {  
    this.template = template;  
}  
public int save(Account acc){  
    String sql="insert into account(account_id,name,contact,email) values("+acc.getAccount_id()+",'"+acc.getName()+"',"+acc.getContact()+",'"+acc.getEmail()+"')";  
    return template.update(sql);  
}  




public int update(Emp p){  
    String sql="update emp99 set name='"+p.getName()+"', salary="+p.getSalary()+",designation='"+p.getDesignation()+"' where id="+p.getId()+"";  
    return template.update(sql);  
}  
public int delete(int id){  
    String sql="delete from emp99 where id="+id+"";  
    return template.update(sql);  
}  
public Emp getEmpById(int id){  
    String sql="select * from emp99 where id=?";  
    System.out.println("Getting record....");
    return template.queryForObject(sql, new Object[]{id},new BeanPropertyRowMapper<Emp>(Emp.class));  
}  
public List<Emp> getEmployees(){  
    return template.query("select * from emp99",new RowMapper<Emp>(){  
        public Emp mapRow(ResultSet rs, int row) throws SQLException {  
            Emp e=new Emp();  
            e.setId(rs.getInt(1));  
            e.setName(rs.getString(2));  
            e.setSalary(rs.getFloat(3));  
            e.setDesignation(rs.getString(4));  
            return e;  
        }  
    });  
}
public boolean validUser(Login login) {
	// TODO Auto-generated method stub
	
	String query = "SELECT COUNT(*) FROM login WHERE account_id = ? AND password = ?";
    int count = template.queryForObject(query, Integer.class, login.getAccount_id(), login.getPassword());
    return count > 0;
}
public Account getUserById(int account_id) {
	// TODO Auto-generated method stub
    String sql="select * from account where account_id=?";  
    System.out.println("Getting record....");
    return template.queryForObject(sql, new Object[]{account_id},new BeanPropertyRowMapper<Account>(Account.class));
}
public List<Transaction> getTransactionsById(int id) {
	// TODO Auto-generated method stub
    //String sql="select * from Transaction where from_account_id=?";  

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
	//return null;
}
