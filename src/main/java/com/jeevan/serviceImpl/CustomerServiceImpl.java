package com.jeevan.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.jeevan.DAO.CustomerDAO;
import com.jeevan.model.Customer1;
import com.jeevan.service.CustomerService;

public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerDAO cd;

	public void addCustomer(Customer1 customer) {
		cd.addCustomer(customer);
	}

	public List<Customer1> getAllCustomers() {
		// TODO Auto-generated method stub
		List<Customer1> cl = new ArrayList<Customer1>();

		cl = cd.getAllCustomers();
		return cl;
	}

	public Customer1 getBalanceByid(Integer id) {

		Customer1 c = cd.getBalanceByid(id);
		return c;
	}
	
	public boolean verifyAccountByPin(Integer pin,Integer accNum)
	{
		
		return cd.verifyAccountByPin(pin,accNum);
	}

	public Integer withdraw(Integer accNum, Integer ammount) {
		
		return cd.withdraw(accNum, ammount);
	}
	
	public void deposit(int acno, int amount) {		
		cd.deposit(acno, amount);
		
	}

}
