package com.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.beans.Account;
import com.spring.beans.Emp;
import com.spring.beans.Login;
import com.spring.beans.Transaction;
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
		m.addAttribute("command", new Emp()); // addAttributes() method add values in the Model that'll be identified globally.
		return "empform";
	}

	@RequestMapping("/loginForm")
	public String showNewform( Model m) {
		m.addAttribute("commandNew", new Login()); // addAttributes() method add values in the Model that'll be identified globally.
		return "login";
	}
	
	@RequestMapping("/registerForm")
	public String showRegisterform(Model m) {
		m.addAttribute("commandNew", new Account()); // addAttributes() method add values in the Model that'll be identified globally.
		return "register";
	}
	
	@RequestMapping(value = "/validUser", method = RequestMethod.POST)
	public String validUser(@ModelAttribute("login") Login login,Model m) {
//		if(dao.validUser(login)) {
//			m.addAttribute("message", "Login Successful");
//			return "loginSuccess";
//		}
//		else {
//			m.addAttribute("error", "Invalid Login Credentials, please try again");
//			return "redirect:/loginForm";
//		}
		Account acc = dao.getUserById(login.getAccount_id());
		m.addAttribute("account", acc);
		//m.addAttribute("Account", login);
		return "loginSuccess";
		//return "redirect:/viewemp";// will redirect to viewemp request mapping
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
	
	
	@RequestMapping(value = "/registerUser", method = RequestMethod.POST)
	public String saveUser(@ModelAttribute("acc") Account acc, Model model) {
		dao.save(acc);
		model.addAttribute("message", "User registered successfully!");
		model.addAttribute("name", acc.getName());
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