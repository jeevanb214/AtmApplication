package com.jeevan.controller;

import java.util.ArrayList;
import java.util.List;

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

}
