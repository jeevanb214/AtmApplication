package com.jeevan.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.ModelAndView;

import com.jeevan.model.Customer1;
import com.jeevan.service.CustomerService;
import com.jeevan.serviceImpl.CustomerServiceImpl;

@Controller
public class CustomerController {

	@Autowired
	CustomerServiceImpl service;

	@RequestMapping("/CreateAccount")
	public ModelAndView addCustomer() {
		System.out.println("I am in addCustomer controller");
		ModelAndView m = new ModelAndView();
		m.addObject("customer", new Customer1());
		m.setViewName("RegisterCustomer.jsp");

		return m;
	}

	@RequestMapping(value = "/registerSuccess", method = RequestMethod.POST)
	public String registerSuccess(@ModelAttribute("customer") Customer1 customer, Model model) {

		System.out.println("I am in registerSuccess controller");
		service.addCustomer(customer);
		model.addAttribute("customer", customer);
		return "DisplayAcDetails.jsp";
	}

	@RequestMapping("/customerList")
	public String customerDetails(Model model) {
		List<Customer1> cl = new ArrayList<Customer1>();
		cl = service.getAllCustomers();
		model.addAttribute("list", cl);
		return "CustomerDetails.jsp";
	}

	@RequestMapping("/BalanceEnquiry")
	public String balanceByid(HttpServletRequest req, Model m) {
		Integer id = Integer.parseInt(req.getParameter("acno"));

		Customer1 customer = service.getBalanceByid(id);

		System.out.println("Again in balanceByid   :" + customer.getBalance());
		m.addAttribute("customer", customer);

		return "ShowBalance.jsp";
	}

	@RequestMapping("/fundTranser")
	public String fundTranser(HttpServletRequest req, Model m) {
		Integer acno1 = Integer.parseInt(req.getParameter("acno1"));
		Integer pin = Integer.parseInt(req.getParameter("pin"));
		Integer ammount = Integer.parseInt(req.getParameter("amount"));
		Integer acno2 = Integer.parseInt(req.getParameter("acno2"));

		if (service.verifyAccountByPin(pin, acno1)) {
			Integer afterAmmount = service.withdraw(acno1, ammount);
			if (afterAmmount == 0)
				return "errorBal.jsp";
			else {
				m.addAttribute("balance", afterAmmount);
				service.deposit(acno2, ammount);
				return "DisplayBal.jsp";
			}

		} else {
			return "errorpassword.jsp";
		}

	}

	@RequestMapping("/withdraw")
	public String withdraw(HttpServletRequest req, Model m) {
		Integer accNum = Integer.parseInt(req.getParameter("acno"));
		Integer pin = Integer.parseInt(req.getParameter("pin"));
		Integer ammount = Integer.parseInt(req.getParameter("ammount"));

		if (service.verifyAccountByPin(pin, accNum)) {
			Integer afterAmmount = service.withdraw(accNum, ammount);
			if (afterAmmount == 0)
				return "errorBal.jsp";
			else {
				m.addAttribute("balance", afterAmmount);
			}
		}		 
		return "DisplayBal.jsp";
	}
	
	@RequestMapping("/deposit")
	public String deposit(HttpServletRequest req, Model m)
	{
		Integer accNum = Integer.parseInt(req.getParameter("acno"));
		Integer ammount = Integer.parseInt(req.getParameter("ammount"));
		service.deposit(accNum, ammount);
		return "deposit.jsp";
	}
	
}
