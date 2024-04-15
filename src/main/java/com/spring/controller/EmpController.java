package com.spring.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.beans.Acc;
import com.spring.beans.Account;
import com.spring.beans.Client;
import com.spring.beans.Login;
import com.spring.beans.Transaction;
import com.spring.beans.amount;
import com.spring.dao.EmpDao;
import com.spring.requestBeans.sendmoney;

@Controller
public class EmpController {
	@Autowired
	EmpDao dao;// will inject dao from xml file

	
	
	@RequestMapping(value = "/loginSuccess", method = RequestMethod.GET)
	public String loginSuccess(@RequestParam("clientId") int clientId, Model m) {
		Client cl = dao.getClientByClientId(clientId);
		m.addAttribute("client", cl);
		List<Account> accounts = dao.getAccountsOfClient(cl);
		System.out.println("Account List from Controller validUser: " + accounts);
		m.addAttribute("accountList", accounts);
		return "loginSuccess";
	}

	@RequestMapping("/loginForm")
	public String showNewform(Model m) {
		m.addAttribute("commandNew", new Login()); // addAttributes() method add values in the Model that'll be //
													// identified globally.
		return "login";
	}

	@RequestMapping("/withdraw/loginForm")
	public String showLoginform(Model m) {
		m.addAttribute("commandNew", new Login()); // addAttributes() method add values in the Model that'll be //
													// identified globally.
		return "login";
	}

	@RequestMapping("/registerForm")
	public String showRegisterform(Model m) {
		// m.addAttribute("commandNew", new Account()); // addAttributes() method add
		// values in the Model that'll be identified globally.
		return "register";
	}

	@RequestMapping(value = "/validUser", method = RequestMethod.POST)
	public String validUser(@ModelAttribute("client") Client client, Model m, RedirectAttributes redirectAttributes) {
		try {
			System.out.println("Data from User: " + client);
			Client cl = dao.getClientByEmailId(client.getEmail());
			System.out.println("Client details from validUser: " + cl);
			cl.setPassword(client.getPassword());
			cl.setEmail(client.getEmail());
			m.addAttribute("client", cl);
			List<Account> accounts = dao.getAccountsOfClient(cl);
			System.out.println("Account List from Controller validUser: " + accounts);
			m.addAttribute("accountList", accounts);
			System.out.println("Client details before validation: " + cl);
			if (dao.validUser(cl)) {
				redirectAttributes.addAttribute("clientId", cl.getClient_id());
				redirectAttributes.addFlashAttribute("client", cl);
				redirectAttributes.addFlashAttribute("accountList", accounts);
				return "redirect:/loginSuccess";
			} else {
				m.addAttribute("error", "Invalid Login Credentials, please try again");
				return "login";
				// redirect:/loginForm
			}
		} catch (Exception e) {
			m.addAttribute("error", "Invalid login Credentials. Please try again.");
			return "login";
		}
		// return "redirect:/viewemp";// will redirect to viewemp request mapping
	}
	

	@RequestMapping(value = "/registerUser", method = RequestMethod.POST)
	public String saveUser(@ModelAttribute("acc") Acc acc, Model model) {
		// dao.save(acc);
		System.out.println(acc.getClient()); // Example: Print the client object
		System.out.println(acc.getAccount_type());
		model.addAttribute("message", "User registered successfully!");
		model.addAttribute("model", acc);
		model.addAttribute("name", acc.getClient().getName());
		dao.registerUser(acc);
		return "registrationSuccess";// will redirect to viewemp request mapping
	}

	

	
	// depositSuccess/${account.account_id}
	@RequestMapping(value = "/deposit/depositSuccess/{account_id}")
	public String depositSuccess(@PathVariable int account_id, Model m) {
		Account acc = dao.getUserById(account_id);
		Client client = dao.getClientByClientId(acc.getClient_id());
		List<Account> accounts = dao.getAccountsOfClient(client);
		m.addAttribute("client", client);
		m.addAttribute("accountList", accounts);
		return "loginSuccess";

	}

//Deposit Function
	@RequestMapping(value = "/deposit/{account_id}")
	public String deposit(@PathVariable int account_id, Model m) {
		Account acc = dao.getUserById(account_id);
		m.addAttribute("account", acc);
		return "depositform";
	}

	@RequestMapping(value = "/withdraw/{account_id}")
	public String withdraw(@PathVariable int account_id, Model m) {
		Account acc = dao.getUserById(account_id);
		m.addAttribute("account", acc);
		return "withdrawform";
	}
	
	//sendMonetBnAccounts
	@RequestMapping(value = "/sendMoneyBnAccounts/{account_id}")
	public String transferFunds(@PathVariable int account_id, Model m) {
		Account acc = dao.getUserById(account_id);
		m.addAttribute("account", acc);
		List<String> accountTypes= new ArrayList<String>();
		accountTypes = dao.getAccountTypes(acc);
		System.out.println("AccountTypes");
		m.addAttribute("accountTypes", accountTypes);
		return "transferFund";
	}

//	 @PostMapping("/depositMoney")
//	    public String depositAmount(@RequestParam("amount") String amount, Model model) {
//	        // Here, 'amount' is automatically populated with the value from the form field named "amount"
//	        // You can perform your deposit logic here, such as updating a database or processing a transaction
//	        
//	        // For this example, let's just print the amount to the console
//	        System.out.println("Deposit amount: " + amount);
//	        
//	        // Add the amount to the model if needed for the view
//	        model.addAttribute("depositAmount", amount);
//	        
//	        // Return the name of the view to render after processing the deposit
//	        return "success"; // Assuming you have a success.jsp or success.html page
//	    }

	@RequestMapping(value = "/deposit/depositMoney", method = RequestMethod.POST)
	public String depositMoney(@ModelAttribute amount amount, Model model, RedirectAttributes redirectAttributes) {
		try {
			System.out.println("Deposit Form Balance:  " + amount.getBalance());
			System.out.println("Account Id:  " + amount.getAccountId());

			Account acc = dao.getUserById(amount.getAccountId());
			model.addAttribute("account", acc);
			int finalBalance = (int) (amount.getBalance() + acc.getBalance());
			System.out.println("Deposit amount: " + amount.getBalance());
			System.out.println("Balance after addition: " + finalBalance);

			dao.depositMoney(acc, finalBalance);
			acc.setBalance(finalBalance);
			model.addAttribute("account", acc);
			return "depositSuccess";
//            //dao.depositMoney(acc,finalBalance);
//            // Add the amount to the model if needed for the view
//            model.addAttribute("depositAmount", newBalance);
			// Convert the amount string to a double
//			String amount = account.getBalance();
//			System.out.println("Account balance : " + amount.getBalance());
			// Here, 'amount' is now a double containing the value from the form field named
			// "amount"
			// You can perform your deposit logic here, such as updating a database or
			// processing a transaction

			// For this example, let's just print the amount to the console
			// System.out.println("Deposit amount: " + amount);

			// Add the amount to the model if needed for the view
			// model.addAttribute("depositAmount", amount);

			// Return the name of the view to render after processing the deposit
			// redirectAttributes.addFlashAttribute("amount", amount);
			// Assuming you have a success.jsp or success.html page
		} catch (NumberFormatException e) {
			// Handle the case where the input value is not a valid number
			System.err.println("Invalid amount format: " + amount.getBalance());
			// Optionally, you can add an error message to the model and return to the form
			// page
			model.addAttribute("errorMessage", "Invalid amount format. Please enter a valid number.");
			return "depositform"; // Return to the deposit form with an error message
		}
	}

	@RequestMapping(value = "/withdraw/withdrawMoney", method = RequestMethod.POST)
	public String withdrawMoney(@ModelAttribute amount amount, Model model, RedirectAttributes redirectAttributes) {
		try {
			System.out.println("Withdraw Amount from User:  " + amount.getBalance());
			System.out.println("Account Id:  " + amount.getAccountId());

			Account acc = dao.getUserById(amount.getAccountId());
			model.addAttribute("account", acc);
			if (amount.getBalance() > acc.getBalance()) {
				model.addAttribute("error",
						"Balnce is less than Withdraw amount, please enter an amount lesse than Current balance");
				return "withdrawform";
			} else {
				int finalBalance = (int) (acc.getBalance() - amount.getBalance());
				System.out.println("Amount to withdraw: " + amount.getBalance());
				System.out.println("Balance after withdrawal: " + finalBalance);
				dao.withdrawMoney(acc, finalBalance);
				acc.setBalance(finalBalance);
				model.addAttribute("account", acc);
				return "withdrawsuccess";
			}

		} catch (NumberFormatException e) {
			// Handle the case where the input value is not a valid number
			System.err.println("Invalid amount format: " + amount.getBalance());
			// Optionally, you can add an error message to the model and return to the form
			// page
			model.addAttribute("errorMessage", "Invalid amount format. Please enter a valid number.");
			return "wirthdrawform"; // Return to the deposit form with an error message
		}
	}

	@RequestMapping(value = "/sendMoney/send", method = RequestMethod.POST)
	public String sendMoney(@ModelAttribute sendmoney sendmoney, Model model) {
		try {
			System.out.println("Hello---------------");
			Account to_acc = dao.getAccountByEmail(sendmoney.getEmail());
			System.out.println("to_acc : " + to_acc);
			Account from_Account = dao.getUserById(sendmoney.getAccountId());
			System.out.println("from_acc : " + from_Account);
			
			
			if( from_Account.getBalance() >= sendmoney.getAmount()) {
				System.out.println("to_acc : " + to_acc);
				System.out.println("from_acc : " + from_Account);
				System.out.println("from_acc1111 : " + to_acc.getBalance());
				System.out.println("from_acc : " + sendmoney.getAmount());
				int to_acc_final_balance = to_acc.getBalance() + sendmoney.getAmount();
				int from_acc_final_balance = from_Account.getBalance() - sendmoney.getAmount();
				dao.depositMoney(to_acc, to_acc_final_balance);
				dao.withdrawMoney(from_Account, from_acc_final_balance);
				
				// Log the transaction
	            Transaction transaction = new Transaction();
	            transaction.setFrom_account_id(from_Account.getAccount_id());
	            transaction.setTo_account_id(to_acc.getAccount_id());
	            transaction.setAmount(sendmoney.getAmount());
	            transaction.setAccount_type(from_Account.getAccount_type());
	            
	            System.out.println("transaction-----" + transaction);
	            dao.saveTransaction(transaction);
	            
				System.out.println("Transaction Successful from account_Id: " + from_Account.getAccount_id()
				+ " to Account_id: " + to_acc.getAccount_id() + " Amount : " + sendmoney.getAmount());
				System.out.println("Final balance in reciepient is : " + to_acc.getBalance());
				model.addAttribute("from_Account", from_Account);
				model.addAttribute("to_Account", to_acc);
				return "transactionSuccess";
			}
			else {
				model.addAttribute("error", "Account Balnce is less than Sending Amount, please enter an amount lesser than Current balance");
				return "sendmoney";
			}
		}
		catch (NumberFormatException e) {
			// Handle the case where the input value is not a valid number
			System.err.println("Invalid amount format: " + sendmoney.getAmount());
			// Optionally, you can add an error message to the model and return to the form
			// page
			model.addAttribute("errorMessage", "Invalid amount format. Please enter a valid number.");
			return "wirthdrawform"; // Return to the deposit form with an error message
		}
			
		}

	@RequestMapping(value = "sendMoney/{account_id}", method = RequestMethod.GET)
	public String sendMoney(@PathVariable int account_id, Model m) {
		// dao.delete(id);
		Account acc = dao.getUserById(account_id);
		m.addAttribute("account", acc);
		return "sendmoney";
	}

	@RequestMapping(value = "transactionHistory/{id}")
	public String transactionHistory(@PathVariable int id, Model m) {
		List<Transaction> list = dao.getTransactionsById(id);
		m.addAttribute("list", list);
		return "transactionHistory";
	}

	@RequestMapping(value = "/payBills/{account_id}", method = RequestMethod.GET)
	public String payBills(@PathVariable int account_id, Model m) {
		Account acc = dao.getUserById(account_id);
		m.addAttribute("account", acc);
		return "payBills"; // Return the name of the pay bills form view
	}

	//pay bills
	//payUtility
	
	@RequestMapping(value = "/payBills/payUtility", method = RequestMethod.POST)
	public String billSuccess(@ModelAttribute com.spring.requestBeans.payBill payBill, Model model, RedirectAttributes redirectAttributes) {
		try {
			System.out.println("Withdraw Amount from User:  " + payBill.getAmount());
			System.out.println("Account Id:  " + payBill.getAccount_Id());

			Account acc = dao.getUserById(payBill.getAccount_Id());
			System.out.println("Account details : " + acc);
			model.addAttribute("account", acc);
			if (payBill.getAmount() > acc.getBalance()) {
				model.addAttribute("error",
						"Balnce is less than Withdraw amount, please enter an amount lesse than Current balance");
				return "payBills";
			} else {
				int finalBalance = (int) (acc.getBalance() - payBill.getAmount());
				System.out.println("Amount to withdraw: " + payBill.getAmount());
				System.out.println("Balance after withdrawal: " + finalBalance);
				dao.withdrawMoney(acc, finalBalance);
				acc.setBalance(finalBalance);
				model.addAttribute("payBill", payBill);
				model.addAttribute("account", acc);
				return "billSuccess";
			}

		} catch (NumberFormatException e) {
			// Handle the case where the input value is not a valid number
			System.err.println("Invalid amount format: " + payBill.getAmount());
			// Optionally, you can add an error message to the model and return to the form
			// page
			model.addAttribute("errorMessage", "Invalid amount format. Please enter a valid number.");
			return "payBills"; // Return to the deposit form with an error message
		}
	}
}



