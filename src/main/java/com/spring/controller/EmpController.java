package com.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.beans.Acc;
import com.spring.beans.Account;
import com.spring.beans.Client;
import com.spring.beans.Emp;
import com.spring.beans.Login;
import com.spring.beans.Transaction;
import com.spring.beans.amount;
import com.spring.dao.EmpDao;

@Controller
public class EmpController {
	@Autowired
	EmpDao dao;// will inject dao from xml file

	/*
	 * It displays a form to input data, here "command" is a reserved request
	 * attribute which is used to display object data into form
	 */
	@RequestMapping("/empform")
	public String showform(Model m) {
		m.addAttribute("command", new Emp()); // addAttributes() method add values in the Model that'll be identified
												// globally.
		return "empform";
	}

	@RequestMapping(value = "/loginSuccess", method = RequestMethod.GET)
	public String loginSuccess() {
		return "loginSuccess";
	}

	@RequestMapping("/loginForm")
	public String showNewform(Model m) {
		m.addAttribute("commandNew", new Login()); // addAttributes() method add values in the Model that'll be											// identified globally.
		return "login";
	}
	
	@RequestMapping("/withdraw/loginForm")
	public String showLoginform(Model m) {
		m.addAttribute("commandNew", new Login()); // addAttributes() method add values in the Model that'll be											// identified globally.
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
	/*
	 * It saves object into database. The @ModelAttribute puts request data into
	 * model object. You need to mention RequestMethod.POST method because default
	 * request is GET
	 */
//	@RequestMapping(value = "/save", method = RequestMethod.POST)
//	public String save(@ModelAttribute("emp") Emp emp) {
//		dao.save(emp);
//		return "redirect:/viewemp";// will redirect to viewemp request mapping
//	}

//	@RequestMapping(value = "/registerUser", method = RequestMethod.POST)
//	public String saveUser(@ModelAttribute("acc") Account acc, Model model) {
//		dao.save(acc);
//		model.addAttribute("message", "User registered successfully!");
//		model.addAttribute("name", acc.getName());
//		return "registrationSuccess";// will redirect to viewemp request mapping
//	}

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

	/* It provides list of employees in model object */
	@RequestMapping("/viewemp")
	public String viewemp(Model m) {
		List<Emp> list = dao.getEmployees();
		m.addAttribute("list", list);
		return "viewemp";
	}

	/*
	 * It displays object data into form for the given id. The @PathVariable puts
	 * URL data into variable.
	 */
	@RequestMapping(value = "/editemp/{id}")
	public String edit(@PathVariable int id, Model m) {
		Emp emp = dao.getEmpById(id);
		m.addAttribute("command", emp);
		return "empeditform";
	}
	//depositSuccess/${account.account_id}
	@RequestMapping(value="/deposit/depositSuccess/{account_id}")
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
			//redirectAttributes.addFlashAttribute("amount", amount);
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
			if(amount.getBalance() > acc.getBalance()) {
				model.addAttribute("error", "Balnce is less than Withdraw amount, please enter an amount lesse than Current balance");
				return "withdrawform";
			}
			else {
				int finalBalance = (int) (acc.getBalance() - amount.getBalance() );
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

	
	// @RequestParam("balance") String balance, @RequestParam("account_id") String
	// account_id, Model model
	// @RequestParam("account_id") String account_id,
//	 @PostMapping("/deposit/depositMoney/{balance}")
//	    public String depositAmount(@RequestParam("balance") String balance,  Model model) {
//	        try {
//	            // Here, 'balance' is now an integer containing the value from the form field named "balance"
//	            // You can perform your deposit logic here, such as updating a database or processing a transaction
//	            // For this example, let's just print the amount to the console
//	            int newBalance = Integer.parseInt(balance);
//	           // int newAccountId = Integer.parseInt(account_id);
//	            int finalBalance = 0;
//	        	//Account acc = dao.getUserById(newAccountId);
//	        	//model.addAttribute("account", acc);	
//	            //finalBalance = acc.getBalance() + newBalance;
//	        	
//	            System.out.println("Deposit amount: " + newBalance);
//	            //dao.depositMoney(acc,finalBalance);
//	            // Add the amount to the model if needed for the view
//	            model.addAttribute("depositAmount", newBalance);
//	            
//	            // Return the name of the view to render after processing the deposit
//	            return "success"; // Assuming you have a success.jsp or success.html page
//	        } catch (NumberFormatException e) {
//	            // Handle the case where the input value is not a valid number
//	            System.err.println("Invalid amount format: " + balance);
//	            // Optionally, you can add an error message to the model and return to the form page
//	            model.addAttribute("errorMessage", "Invalid amount format. Please enter a valid number.");
//	            return "depositform"; // Return to the deposit form with an error message
//	        }
//	    }

	// @PathVariable("balance") String balance, @PathVariable("account_id") String
	// account_id, Model model

	@RequestMapping(value = "sendMoney/{account_id}", method = RequestMethod.GET)
	public String sendMoney(@PathVariable int account_id, Model m) {
		// dao.delete(id);
		Account acc = dao.getUserById(account_id);
		m.addAttribute("account", acc);
		return "sendmoney";
	}

	/* It updates model object. */
	@RequestMapping(value = "/editsave", method = RequestMethod.POST)
	public String editsave(@ModelAttribute("emp") Emp emp) {
		dao.update(emp);
		return "redirect:/viewemp";
	}

	/* It deletes record for the given id in URL and redirects to /viewemp */
	@RequestMapping(value = "/deleteemp/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable int id) {
		dao.delete(id);
		return "redirect:/viewemp";
	}

	@RequestMapping(value = "transactionHistory/{id}")
	public String transactionHistory(@PathVariable int id, Model m) {
		List<Transaction> list = dao.getTransactionsById(id);
		m.addAttribute("list", list);
		return "transactionHistory";
	}
}